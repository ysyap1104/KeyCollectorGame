//MVC Design Pattern -  Model class
//Kevin Toh Huat Xiang
public class KeyNote extends Key{
	
	//Constructor
	public KeyNote(){
        name = "KeyNote";
    }
	
    public boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate){
        int rowDistance = Math.abs(fromCoordinate.getRow() - toCoordinate.getRow());
        int colDistance = Math.abs(fromCoordinate.getCol() - toCoordinate.getCol());
        return ((rowDistance <= 2) && (colDistance <= 2)) && ((rowDistance == 2) || (colDistance == 2));
        //Must move 2 squares in any direction (i.e. skip 1 square.)
    }
	
	//Get what is the type of the key
	public String getType(){
		return "KeyNote";
	}

}
