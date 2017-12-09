//MVC Design Pattern -  Model class
//Ng Wee Fon
abstract class Key extends MapItem{
	
    protected String name;
	
    public abstract boolean canMove(Coordinate fromCoordinate, Coordinate toCoordinate);     
	
	//Get what is the type of the key
	public abstract String getType();       //Jeffrey Tan Kah Jun
    
	//Get what is the name of the key
	public String getName(){				//Ang John Syin
        return name;
    }
}
