import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton{

    //attributes
    private int xLoc;       //xLocation
    private int yLoc;       //yLocation
    private JButton tile;   //JButton Variable
    private String piece;   //Variable of what piece is on the square
    private Icon icon;      //Variable for icon of the image

    //constructor
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

    //methods
    public String moveTo(Square nextTile){

        String temp = nextTile.getPiece();                  //Stores the next tile the piece will move to
        nextTile.editPiece(this.getPiece());                //Updates the next tile to the peice of the previous tile
        return temp;                                        //returns the value of what the next tile used to be
        
    }

    public int isWhite(){
        if(this.getPiece() == "WHITE"){ //Checks what the tile being selected is
            return -1;
        }
        else if(this.getPiece() == "RED"){
            return 1;
        }
        else{
            return 0;
        }
        //change is the direction the piece is going in.
        //if the piece is red then it will travel down the board (increase in y)
        //if the piece is white then it will travel up the board (increase in x)
    }

    public Boolean canMoveTo(Square nextTile, Boolean playerWhite){
        int change = isWhite();

        //playerWhite boolean tells you if it is the white or the red persons turn to go
        if(playerWhite==true){
            //If its the white persons go then a red piece cannot be selected
            if(this.getPiece() != "WHITE"){
                return false;
            }
        }
        else{
            if(this.getPiece() != "RED"){
                return false;
            }
        }

        
        if(this.getPiece() != "NONE" || this.getPiece() != "MAYBE"){//Makes it so only tiles with pieces on it will move
            /*
             *
             *
             *  Set of Rules for small jump
             *
             *
             */
            if(nextTile.getX() == xLoc-1 ||  nextTile.getX() == xLoc+1){    //Makes sure the move is one space left or right
                if(nextTile.getY() == yLoc + change){                       //Makes sure the move is forward and only one space
                    if(nextTile.getPiece() == "NONE"){                      //Makes sure that the piece in front is empty before moving
                        return true;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    public Boolean canJumpTo(Square nextTile, Boolean playerWhite, Square middle){
        int change = isWhite();

        //playerWhite boolean tells you if it is the white or the red persons turn to go
        if(playerWhite==true){
            //If its the white persons go then a red piece cannot be selected
            if(this.getPiece() != "WHITE"){
                return false;
            }
        }
        else{
            if(this.getPiece() != "RED"){
                return false;
            }
        }
        
        if(this.getPiece() != "NONE" || this.getPiece() != "MAYBE"){        //Makes it so only tiles with pieces on it will move
            if(nextTile.getX() - this.getX() == 2 || nextTile.getX() - this.getX() == -2){      //makes sure the destination tile is two X coordinates away
                if(middle.getX() != this.getX()){                                               //makes sure the middle X square Coordinate isnt the same as the source x Coor
                    if(nextTile.getY()== this.getY()+change*2){                                 //makes sure the destination tile is in the correct direction the peices should move
                        if(nextTile.getPiece() == "NONE" || nextTile.getPiece() == "MAYBE"){    //makes sure the destination tile is empty
                            if(playerWhite==true && middle.getPiece() == "RED"){                
                                return true;
                            }
                            else if(playerWhite==false && middle.getPiece() == "WHITE"){        //makes sure that the piece jumped over is of opposite suit.
                                return true;
                            }
                            else
                                return false;
                        }
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }

    //Accessors
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

    //Mutators
    public void editPiece(String s){    //s is the value fo which it's meant to be updated
        piece = s;
        //Changes instance variable icon according to what piece it was changed to
        this.setTheIcon(s);

    }
    public void setTheIcon(String s){

        if(s == "WHITE"){
            this.icon = new ImageIcon("white.png");
        }
        else if(s == "RED"){
            this.icon = new ImageIcon("red.png");
        }
        else if(s == "MAYBE"){
            this.icon = new ImageIcon("selected.png");
        }
        else{
            this.icon = new ImageIcon("empty.png");
        }

        //Updates the icon of the button of the object
        this.tile.setIcon(icon);
    }
}