//MVC Design Pattern -  Model class
//Ang John Syin
public class KeyDisk extends Key{
	
	//Constructor
	public KeyDisk(){
        name = "KeyDisk";
    }
	
    public boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate){
        int rowDistance = Math.abs(fromCoordinate.getRow() - toCoordinate.getRow());
        int colDistance = Math.abs(fromCoordinate.getCol() - toCoordinate.getCol());
        return (((rowDistance == 0) || (colDistance == 0)) && (rowDistance <= 3) && (colDistance <= 3));
        //Can move up to 3 squares horizontally or vertically, but cannot move diagonally.
    }
	
	//Get what is the type of the key
	public String getType(){
		return "KeyDisk";
	}

}
