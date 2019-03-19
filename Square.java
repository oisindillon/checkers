import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton{

    private int xLoc;       //xLocation
    private int yLoc;       //yLocation
    private JButton tile;   //JButton Variable
    private String piece;   //Variable of what piece is on the square
    private Icon icon;      //Variable for icon of the image

    //public moveTo

    public Square(int y, int x, boolean colour, String p) {
        //Initialises Variables
        piece = p;
        xLoc = x/100;
        yLoc = y/100;

        //Sets icons
        if(colour == true){
            if(piece == "WHITE"){
                icon = new ImageIcon("white.png");
            }
            else if(piece == "RED"){
                icon = new ImageIcon("red.png");
            }
            else{
                icon = new ImageIcon("empty.png");
            }
        }
        else{
            icon = new ImageIcon("empty2.png");
        }
        tile = new JButton(icon);
    }

    public String moveTo(Square nextTile){
        int change;
        String other;
        if(this.getPiece() == "RED"){   //Checks what the tile being selected is
            change = 1;
        }
        else{
            change = -1;
        }
        //change is the direction the piece is going in.
        //if the piece is red then it will travel down the board (increase in y)
        //if the piece is white then it will travel up the board (increase in x)


        if(nextTile.getX() == xLoc-1 ||  nextTile.getX() == xLoc+1){    //Makes sure the move is one space left or right
            if(nextTile.getY() == yLoc + change){                       //Makes sure the move is forward and only one space
                if(nextTile.getPiece() == "NONE"){                      //Makes sure that the piece in front is empty before moving
                    String temp = nextTile.getPiece();                  //Stores the next tile the piece will move to
                    nextTile.editPiece(this.getPiece());                //Updates the next tile to the peice of the previous tile
                    System.out.println("Hello there");
                    return temp;                                        //returns the value of what the next tile used to be
                }
                else{
                    return this.getPiece();
                }
            }
            else{
                return this.getPiece();
            }
        }
        else{
            return this.getPiece();
        }
    }

    //Get Methods
    public int getX(){
        return xLoc;
    }
    public int getY(){
        return yLoc;
    }
    public String getPiece(){
        return piece;
    }
    public JButton getTile(){
        return tile;
    }
    public Icon getIcon(){
        return icon;
    }

    //Mutator
    public void editPiece(String s){    //s is the value fo which it's meant to be updated
        piece = s;

        //Changes instance variable icon according to what piece it was changed to
        if(this.getPiece() == "WHITE"){
            this.icon = new ImageIcon("white.png");
        }
        else if(this.getPiece() == "RED"){
            this.icon = new ImageIcon("red.png");
        }
        else{
            this.icon = new ImageIcon("empty.png");
        }

        //Updates the icon of the button of the object
        this.tile.setIcon(icon);
    }
}