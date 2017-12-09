import java.util.*;
//Yap Yung Seng
//MVC Design Pattern -  Model class
public class Player extends MapItem{
	
	private String name;
    private LinkedList<Key> keysObtained=new LinkedList<Key>();
	
	//Get the player name;         
	public String getName(){
		return name;
	}
	
	//Set the player name;         
	public void setName(String name){
		this.name = name;
	}
	
	//Get how many number of key a player has for now         
    public int getNumKey(){
        return keysObtained.size();
    }
	
	//Get the last key 
    public Key getLastKey(){			
        return keysObtained.getLast();
    }
	
	//Get key by                      
	public Key getKey(int index){
		return keysObtained.get(index);
	}
	
	//Add new key to the list		 
	public void addKey(Key key){
		keysObtained.add(key);
	}
	
	//Collect new key and add it to the list
    public void collectKey(Key keystepped){
        for (int i = 0; i < keysObtained.size(); i++){
            if (keysObtained.get(i).getName() == keystepped.getName()){
                keysObtained.remove(i);
            }
        }
        keysObtained.add(keystepped);
    }
	
	//Default move
	public boolean defaultMove(Coordinate fromCoor, Coordinate toCoor){
		int rowDiff = Math.abs(fromCoor.getRow() - toCoor.getRow());
		int colDiff = Math.abs(fromCoor.getCol() - toCoor.getCol());
		return ((rowDiff <= 2) && (colDiff <= 2));
	}
}
