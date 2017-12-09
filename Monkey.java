//MVC Design Pattern -  Model class
//Ang John Syin
public class Monkey extends Key{
	
	//Constructor
	public Monkey(){
        name = "Monkey";
    }
	
    public boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate){
        int rowDistance = Math.abs(fromCoordinate.getRow() - toCoordinate.getRow());
        int colDistance = Math.abs(fromCoordinate.getCol() - toCoordinate.getCol());
        return ((rowDistance <= 3) && (colDistance <= 3));
        //Can move up to 3 squares in any direction.
    }
	
	//Get what is the type of the key/
	public String getType(){
		return "Monkey";
	}
}

