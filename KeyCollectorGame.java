import java.util.*;
import java.lang.*;

public class KeyCollectorGame{
	
    final static String[] PLAYERIMAGEPATH = {"BanKei.gif", "ArkImides.gif", "CanSer.gif", "DozCiztem.gif"};
	final static String[] PLAYERNAME={"BanKei","ArkImides","CanSer","DozCiztem"};
    final static String[] KEYIMAGEPATH = {"Pinkey.gif", "Donkey.gif", "KeyDisk.gif", "KeyNote.gif", "Monkey.gif"};
	final static String TREASUREIMAGEPATH = "treasurechest.gif";
    final static Key[] KEYTOSPAWN = {(new Pinkey()), (new Donkey()), (new KeyDisk()), (new KeyNote()), (new Monkey())};
    final static int MAPSIZEROW = 9;
    final static int MAPSIZECOL = 9;
    final static int KEYTOUNLOCK = 5;
    final static int KEYONMAP = 5;
	final static int NUMPLAYER = 4;
    
	
	private static LinkedList<MapItem> allMapItem = new LinkedList<MapItem>();
	private static LinkedList<Player> allPlayers = new LinkedList<Player>();
	private static LinkedList<Key> keysOnMap = new LinkedList<Key>();
	private static TreasureChest treasureChest = new TreasureChest();
	private static int currentTurn = 0;

	
    public static void main(String[] args){
		
		KeyCollectorGame keyCollectorGame = new KeyCollectorGame();
		MapGUI mapGUI = new MapGUI(MAPSIZEROW, MAPSIZECOL);        //View
		setupPlayer();			   				   				//Model
		setupTreasure();
		setupKey();
		GameController gameController = new GameController(allMapItem,allPlayers,keysOnMap,treasureChest, MAPSIZEROW, MAPSIZECOL, currentTurn, mapGUI);  //Controller
		mapGUI.addController(gameController);
    }
	
	//Set up all players details				//Wrote by Ang John Syin
	private static void setupPlayer(){
        for (int i = 0; i < NUMPLAYER; i++){
            int row = 0;
            int col = 0;
            if ((i+1) % 2 == 0){
                col = MAPSIZECOL-1;
            }
            if (i >= 2){
                row = MAPSIZEROW-1;
            }
            Coordinate tempCoordinate = new Coordinate(row,col);
            Player tempPlayer = new Player();
            tempPlayer.setCoordinate(tempCoordinate);
            tempPlayer.setImagePath(PLAYERIMAGEPATH[i]);
			tempPlayer.setName(PLAYERNAME[i]);
            allPlayers.add(tempPlayer);
            allMapItem.add(tempPlayer);
        }
    }
	
	//Set up all key details					//Wrote by Ang John Syin
	private static void setupKey(){
        for (int i = 0; i < KEYONMAP; i++){
            Key tempKey = KEYTOSPAWN[i];
            Coordinate tempCoordinate = new Coordinate((MAPSIZEROW-1),(MAPSIZECOL-1),allMapItem);
            tempKey.setCoordinate(tempCoordinate);
            tempKey.setImagePath(KEYIMAGEPATH[i]);
			keysOnMap.add(tempKey);
			allMapItem.add(tempKey);
        }
    }
	
	//Set up the treasure details				//Wrote by Ang John Syin
	private static void setupTreasure(){
        int midRow = (MAPSIZEROW + 1) / 2 - 1;
        int midCol = (MAPSIZECOL + 1) / 2 - 1;
        Coordinate tempCoordinate = new Coordinate(midRow,midCol);
        treasureChest.setCoordinate(tempCoordinate);
        treasureChest.setNumOfLocks(KEYTOUNLOCK);
        treasureChest.setImagePath(TREASUREIMAGEPATH);
		allMapItem.add(treasureChest);
    }
	
	
}
