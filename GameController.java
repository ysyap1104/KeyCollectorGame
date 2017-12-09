import java.util.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.io.FileNotFoundException;

// MVC Design Pattern -  Controller class
//This class is responsible to make the GUI to function
//This class control the flow into data model object and updates the view whenever the data change, keeps view and model seperate
public class GameController implements ActionListener{
	
    private  MapGUI mapGUI;                    //View
	private  LinkedList<MapItem> allMapItem;   //Model
	private  LinkedList<Player> allPlayers;
    private  LinkedList<Key> keysOnMap;
	private  TreasureChest treasureChest;
    private  int currentTurn;
    private  int sizeRow;
    private  int sizeCol;
	private  Player curPlayer;
	
	//Constructor      
    GameController(LinkedList<MapItem> allMapItem, LinkedList<Player> allPlayers, LinkedList<Key> keysOnMap, TreasureChest treasureChest,int mapSizeRow, int mapSizeCol,int currentTurn,  MapGUI mapGUI){
        this.mapGUI = mapGUI;
		this.allMapItem = allMapItem;
		this.allPlayers = allPlayers;
		this.keysOnMap = keysOnMap;
		this.treasureChest = treasureChest;
		this.currentTurn = currentTurn;
		this.sizeRow = mapSizeRow;
		this.sizeCol = mapSizeCol;
		createGUI();
		createView();
    }
    
	//Action to be performed by the button            //Jeffrey Tan Kah Jun
	public void actionPerformed(ActionEvent event){
		
		String btn = event.getActionCommand();
		if(btn.equals("Save")){
			saveFile();
		}else if(btn.equals("Load")){
			loadFile();
		}else{
			move(event);
		}
		
	}
	
	//Move                                            //Yap Yung Seng
	private void move(ActionEvent event){
		
		Coordinate clickCoordinate = new Coordinate();                   		//The coordinate that player has clicked
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (mapGUI.getButton(row,col) == event.getSource()){		 
					clickCoordinate.setRow(row);
					clickCoordinate.setCol(col);
				break;
				}
			}
		}
		
		curPlayer = allPlayers.get(currentTurn);								//Current player
		Coordinate curPlayerCoordinate = curPlayer.getCoordinate();				//Current player's coordinate
		boolean moved = moveAttempt(curPlayerCoordinate, clickCoordinate);		
		if(moved){																//If player has succesfully moved then check whether the coordinate that player move has key or not and the treasure
			checkKey(curPlayer.getCoordinate());
			if(checkUnlock(curPlayer.getCoordinate()) == false){
				currentTurn = (currentTurn + 1) % 4;
				mapGUI.changePlayerTurnText(currentTurn+1,allPlayers.get(currentTurn).getName());
			}else{
				mapGUI.showMsgBox("Player " + (currentTurn + 1) + " Win ", "End Game");   //Shows that player who has unlock the treasure succesfully
				System.exit(1);													//End game
			}
		}
	}
	
	//Player try to move from a coordinate to another coordinate       //Ang John Syin
	private boolean moveAttempt(Coordinate fromCoor, Coordinate toCoor){

        if (validMove(fromCoor,toCoor)){								// If player is able to move then set the player's coordinate to the destination coordinate
            curPlayer.setCoordinate(toCoor);
            String playerImagePath = curPlayer.getImagePath();
            mapGUI.showItem(toCoor, playerImagePath);
			mapGUI.unshowItem(fromCoor);
            return true;
        }
        else{															//else display a message box that states the player move failed
            mapGUI.showMsgBox("Invalid move.","Move Failed");
            return false;
        }
    }
    
	//Check whether the coordinate that player wants to go is valid for him or not     //Ng Wee Fon
	private boolean validMove(Coordinate fromCoor, Coordinate toCoor){
		int numKey = curPlayer.getNumKey();						//Get the number of key of the current player
		for(int i=0; i< allPlayers.size(); i++){					
			if(toCoor.isSame(allPlayers.get(i).getCoordinate())){		//If player move to a coordinate that has another player then return false
				return false;
			}
			if (toCoor.isSame(treasureChest.getCoordinate()) && numKey != treasureChest.getNumOfLocks()){     //If player does not have enought key to unlock the treasure chest and move to it then return false
				return false;
			}
		}
		
		if (numKey != 0){							//If player has at least one key then get his last key of the player and move based on the key restrictment
			Key lastKey = curPlayer.getLastKey();
			return lastKey.canMove(fromCoor,toCoor);
		}else{										//else player default move
			return curPlayer.defaultMove(fromCoor,toCoor);
		}
    }
	
	//Check whether the coordinate has key or not         //Ang John Syin
	private void checkKey(Coordinate atCoor){
        for (int i = 0; i < keysOnMap.size(); i++){
            Key curKey = keysOnMap.get(i);
            if (atCoor.isSame(curKey.getCoordinate())){    //If the player move to a coordinate that has key then collect it and add it to the players' keyObtained list
                curPlayer.collectKey(curKey);
                changeKeyLocation(curKey);
				createView();		
            }
        }
    }
	
	//Change the key location                             //Ang John Syin
    private void changeKeyLocation(Key curKey){

        Coordinate fromCoor = curKey.getCoordinate();
        Coordinate toCoor = new Coordinate(sizeRow-1, sizeCol-1, allMapItem);
        curKey.setCoordinate(toCoor);
        String keyImagePath = curKey.getImagePath();
        mapGUI.showItem(toCoor, keyImagePath);
        mapGUI.unshowItem(fromCoor);
		mapGUI.showItem(fromCoor, curPlayer.getImagePath());
		
    }
	
	//Check whether the player can unlock the treasure chest      //Ang John Syin
	private boolean checkUnlock(Coordinate toCoor){
        int numKey = curPlayer.getNumKey();
        if ((treasureChest.checkSamePlace(toCoor)) && (numKey == treasureChest.getNumOfLocks())){       //If player has enough key to unlock the treasure then return true
            return true;
        }else{
			return false;
		}
    }
	
	//Player want to save file       //Jeffrey Tan Kah Jun
	private void saveFile(){
		File file = new File("KeyCollectorGameFile.txt");
		try{

			PrintWriter fout = new PrintWriter(file);					//Write data to file
			fout.println(allPlayers.size());
			for(int i = 0; i < allPlayers.size(); i++){
				fout.println(allPlayers.get(i).getCoordinate().getRow());
				fout.println(allPlayers.get(i).getCoordinate().getCol());
				fout.println(allPlayers.get(i).getImagePath());
				fout.println(allPlayers.get(i).getName());
				fout.println(allPlayers.get(i).getNumKey());
				for(int j = 0; j < allPlayers.get(i).getNumKey(); j++){
					fout.println(allPlayers.get(i).getKey(j).getName());
					fout.println(allPlayers.get(i).getKey(j).getImagePath());
				}
			}
			fout.println(keysOnMap.size());
			for(int i = 0; i < keysOnMap.size(); i++){
				fout.println(keysOnMap.get(i).getName());
				fout.println(keysOnMap.get(i).getCoordinate().getRow());
				fout.println(keysOnMap.get(i).getCoordinate().getCol());
				fout.println(keysOnMap.get(i).getImagePath());
			}
			fout.println(treasureChest.getCoordinate().getRow());
			fout.println(treasureChest.getCoordinate().getCol());
			fout.println(treasureChest.getImagePath());
			fout.println(treasureChest.getNumOfLocks());
			fout.println(currentTurn);
			
			fout.close();

		}catch(FileNotFoundException e){
		e.printStackTrace();}
	}
	
	//Player want to load file       //Jeffrey Tan Kah Jun
	private void loadFile(){
		
		KeyFactory keyFactory = new KeyFactory();        //Create a key factory object
		File file = new File("KeyCollectorGameFile.txt");
		Scanner fin;
		
		try{
			fin = new Scanner(file);
			if(fin.hasNextLine()){
				
				clearGUI();						//Clear the gameboard and the view panel and all the map items
				clearView();
				allMapItem.clear();				//Clear the linkedlist
				allPlayers.clear();
				keysOnMap.clear();
					
				int numPlayers = fin.nextInt();						//Read data from file
				for(int i = 0; i < numPlayers; i++){
					allPlayers.add(new Player());
					allPlayers.get(i).setCoordinate(new Coordinate(fin.nextInt(), fin.nextInt()));
					allPlayers.get(i).setImagePath(fin.next());
					allPlayers.get(i).setName(fin.next());
					int numKey = fin.nextInt();
					for(int j = 0; j < numKey; j++){
						allPlayers.get(i).addKey(keyFactory.makeKey(fin.next()));
						allPlayers.get(i).getKey(j).setImagePath(fin.next());
					}
					allMapItem.add(allPlayers.get(i));
				}
				
				int numKeysOnMap = fin.nextInt();
				for(int i = 0; i < numKeysOnMap; i++){
					keysOnMap.add(keyFactory.makeKey(fin.next()));
					keysOnMap.get(i).setCoordinate(new Coordinate(fin.nextInt(), fin.nextInt()));
					keysOnMap.get(i).setImagePath(fin.next());
					allMapItem.add(keysOnMap.get(i));
				}
				treasureChest.setCoordinate(new Coordinate(fin.nextInt(), fin.nextInt()));
				treasureChest.setImagePath(fin.next());
				treasureChest.setNumOfLocks(fin.nextInt());
				allMapItem.add(treasureChest);
				currentTurn = fin.nextInt();
				
				fin.close();			//Close the file reader
				createGUI();			//Update the GUI
				createView();			//Update the view panel
				
			}else{
				mapGUI.showMsgBox("No data is in the file.", "Load Failed");
			}
			
        } catch(FileNotFoundException e){
		e.printStackTrace();
		}
			
		
	}
	
	//Clear the GUI when player load game data from textfile       //Yap Yung Seng
	private void clearGUI(){
		
		for (int i = 0; i < allMapItem.size(); i++){
            MapItem tempMapItem = allMapItem.get(i);
            Coordinate curCoordinate = tempMapItem.getCoordinate();
            mapGUI.unshowItem(curCoordinate);
        }
	}
	
	//Create GUI                                                   //Yap Yung Seng
    private void createGUI(){
        for (int i = 0; i < allMapItem.size(); i++){
            MapItem tempMapItem = allMapItem.get(i);							
            Coordinate curCoordinate = tempMapItem.getCoordinate();
            String imagePath = tempMapItem.getImagePath();
            mapGUI.showItem(curCoordinate,imagePath);	//Load image to the GUI
        }
		mapGUI.changePlayerTurnText(currentTurn+1, allPlayers.get(currentTurn).getName());    //Update the player turn label in gui to show who is the next player to play
    }
	
	//Clear the key collected view panel			//Kevin Toh Huat Xiang
	private void clearView(){
		
		for(int row = 0; row < allPlayers.size(); row++){
			mapGUI.unShowView(new Coordinate(row,0));
		}
		
		for(int row = 0; row < allPlayers.size(); row++){
			for(int col = 1; col <= keysOnMap.size(); col++){
				mapGUI.unShowView(new Coordinate(row,col));
			}
		}
		
		mapGUI.unShowColor();
	}
	
	//Create key collected view panel               //Ng Wee Fon
	private void createView(){

		LinkedList<String> playerAllImagePath = new LinkedList<String>();
		for(int i = 0; i < allPlayers.size(); i++){
			playerAllImagePath.add(allPlayers.get(i).getImagePath());
			for(int j = 0; j < allPlayers.get(i).getNumKey(); j++){
				playerAllImagePath.add(allPlayers.get(i).getKey(j).getImagePath());
				mapGUI.changeFinalCollectedKeyColor(i,allPlayers.get(i).getNumKey());     //Highlight the last key which has been collected by the player in cyan color
			}
			for(int col = 0; col < playerAllImagePath.size(); col++){
				mapGUI.updateView(i, col, playerAllImagePath.get(col));     //Update the view panel of the GUI
			}
			playerAllImagePath.clear();
		}
	}
}
	

