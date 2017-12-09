//MVC Design Pattern -  Model class
//Kevin Toh Huat Xiang
public class TreasureChest extends MapItem{
	
    int numOfLocks;
	
	//Set how many number of locks that a treasure chest has          //Ang John Syin
    public void setNumOfLocks(int numOfLocks){
        this.numOfLocks = numOfLocks;
    }
	
	//Get the number of locks that a treasure chest has              //Ang John Syin
    public int getNumOfLocks(){
        return numOfLocks;
    }

		
	
}
