import java.util.Random;
import java.util.*;

//Jeffrey Tan Kah Jun & Yap Yung Seng
public class Coordinate {

    private int row;
    private int col;

	//Constructor
	public Coordinate(){
		this.row = 0;
		this.col = 0;
	}
	
	//Overloaded constructor 
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }
	
    //Random coordinate
    public Coordinate(int maxRow, int maxCol, LinkedList<MapItem> allMapItem){
        boolean same;
        do{
            Random rand = new Random();
            this.row = rand.nextInt(maxRow);
            this.col = rand.nextInt(maxCol);

            same = false;
            for (int i = 0; i < allMapItem.size(); i++){
                if (this.isSame(allMapItem.get(i).getCoordinate())){
                    same = true;
                }
            }
        } while (same == true);
    }
	
	//Set row
    public void setRow(int row) {
        this.row = row;
    }
	
	//Set col
    public void setCol(int col) {
        this.col = col;
    }
	
	//Get row
    public int getRow() {
        return row;
    }
	
	//Get col
    public int getCol() {
        return col;
    }
	
	//Check whether the two coordinates are same or not
    public boolean isSame(Coordinate fromCoordinate)
    {
        return (row == fromCoordinate.getRow()) && (col == fromCoordinate.getCol());
    }

}
