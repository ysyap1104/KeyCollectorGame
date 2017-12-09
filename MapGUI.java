import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.border.*;
import java.awt.Color;

// MVC Design Pattern -  View class
//This class is responsible for creating the graphic user interface
public class MapGUI extends JFrame{
	
	private JLabel PlayerTurn;
    private JButton tile[][];
	private JButton saveBtn;
	private JButton loadBtn;
	private JLabel imageLbl[][];
    private int sizeRow;
    private int sizeCol;
	
	//Constructor 					//Ang John Syin
	public MapGUI(int sizeRow, int sizeCol){
        super("Key Collector");
        this.sizeRow = sizeRow;
        this.sizeCol = sizeCol;
        setLayout(new BorderLayout());
        add(createGameBoard(), BorderLayout.CENTER);
		add(createRightPanel(),BorderLayout.EAST);
        setSize(800,450);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	//Create right part of the GUI which containing of menu , player turn and key that has been collected by player        //Yap Yung Seng
	private JPanel createRightPanel(){
		PlayerTurn = new JLabel(" ",SwingConstants.CENTER);
		PlayerTurn.setFont(new Font("Serif", Font.PLAIN, 20));
		Border border = BorderFactory.createTitledBorder("Player Turn");
		PlayerTurn.setBorder(border);
		JPanel RightPanel = new JPanel(new BorderLayout());
		RightPanel.add(createMenuBoard(), BorderLayout.NORTH);
		RightPanel.add(PlayerTurn, BorderLayout.CENTER);
		RightPanel.add(createViewBoard(), BorderLayout.SOUTH);
		return RightPanel;
		
	}
	
	//Create menu board				//Yap Yung Seng
	private JPanel createMenuBoard(){
		JPanel MenuBoard=new JPanel(new FlowLayout());
		Border border = BorderFactory.createTitledBorder("Menu");
		MenuBoard.setBorder(border);
		saveBtn= new JButton("Save");
		loadBtn=new JButton("Load");
		MenuBoard.add(saveBtn);
		MenuBoard.add(loadBtn);
		return MenuBoard;
	}
	
	//Create game board				//Jeffrey Tan Kah Jun
    private JPanel createGameBoard(){
		JPanel gameBoard = new JPanel(new GridLayout(sizeRow,sizeCol));
        tile = new JButton[sizeRow][sizeCol];
        for (int row=0;row<sizeRow;row++){      //creating button and store coordinate
            for (int col=0;col<sizeCol;col++){
                tile[row][col] = new JButton();
                gameBoard.add(tile[row][col]);
            }
        }
        return gameBoard;
    }
	
	//Create key collected view to show keys that have been collected by player      //Yap Yung Seng
	private JPanel createViewBoard(){
		
		JPanel viewBoard = new JPanel(new GridLayout(4,6));
		
		JLabel viewLbl = new JLabel("View Collected Keys");
		imageLbl = new JLabel[4][6];
		for(int row=0; row<4; row++){
			for(int col=0; col<6; col++){
				imageLbl[row][col]=new JLabel();
				viewBoard.add(imageLbl[row][col]);
				
			}
		}
        return viewBoard;
    }

	//Add controller to the buttons		//Jeffrey Tan Kah Jun
	public void addController(ActionListener controller){
		for(int row = 0; row < sizeRow; row++){
			for(int col = 0; col < sizeCol; col++){
				tile[row][col].addActionListener(controller);
			}
		}
		saveBtn.addActionListener(controller);
		loadBtn.addActionListener(controller);
	}
	
	//Return button				//Ng Wee Fon
	public JButton getButton(int row, int col){
		return tile[row][col];
    }
	
	//Load image to game board			//Ang John Syin
    public void showItem(Coordinate coordinate, String imagePath){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        tile[row][col].setIcon(new ImageIcon(this.getClass().getResource(imagePath)));
    }
	
	//Unload image from game board		//Ang John Syin
    public void unshowItem(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        tile[row][col].setIcon(null);
    }
	
	//Load image to key collected view board      //Kevin Toh Huat Xiang
	public void updateView(int row,int col, String imagePath){
        imageLbl[row][col].setIcon(new ImageIcon(this.getClass().getResource(imagePath)));
	}
	
	//Unload image from key collected view board   //Yap Yung Seng
	public void unShowView(Coordinate coordinate){
		int row = coordinate.getRow();
        int col = coordinate.getCol();
		imageLbl[row][col].setIcon(null);	
    }
	
	//Change the label text when player's turn        //Yap Yung Seng
	public void changePlayerTurnText(int currentTurn,String name){
		PlayerTurn.setText("Player "+ currentTurn +": " +name);
	}
	
	//Change the final collected key color          //Yap Yung Seng
	public void changeFinalCollectedKeyColor(int row, int col){
		imageLbl[row][col].setBackground(Color.cyan);
		imageLbl[row][col].setOpaque(true);
		imageLbl[row][col-1].setOpaque(false);
	}
	
	//Clear the collected key color               //Yap Yung Seng
	public void unShowColor(){
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 6; col++){
				imageLbl[row][col].setOpaque(false);
			}
		}
	}
	
	//Show dialog box                        //Ng Wee Fon
	public void showMsgBox(String message, String title){
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
}
