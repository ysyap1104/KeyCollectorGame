//MVC Design Pattern -  Model class
//Jeffrey Tan Kah Jun
public class Pinkey extends Key{
	
	//Constructor
	public Pinkey(){
        name = "Pinkey";
    }
	
    public boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate){
        int rowDistance = Math.abs(fromCoordinate.getRow() - toCoordinate.getRow());
        int colDistance = Math.abs(fromCoordinate.getCol() - toCoordinate.getCol());
        return ((rowDistance <= 1) && (colDistance <= 1));
        //Can only move 1 square in any direction.
    }
	
	//Get what is the type of the key
	public String getType(){
		return "Pinkey";
	}
	
	
}
