//MVC Design Pattern -  Model class
//Yap Yung Seng
public class Donkey extends Key{
	
	//Constructor
	public Donkey(){
        name = "Donkey";
    }
	
    public boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate){
        int rowDistance = Math.abs(fromCoordinate.getRow() - toCoordinate.getRow());
        int colDistance = Math.abs(fromCoordinate.getCol() - toCoordinate.getCol());
        return ((rowDistance == colDistance) && (colDistance <= 3));
		//Can move up to 3 squares diagonally, but cannot move horizontally or vertically.
    }
	
	//Get what is the type of the key
	public String getType(){
		return "Donkey";
	}
	
}
