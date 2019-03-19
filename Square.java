import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton{

    private int xLoc;
    private int yLoc;
    private JButton tile;
    private String piece;

    //public moveTo

    public Square(int y, int x, boolean colour, String p) {
        //Initialises Variables
        piece = p;
        xLoc = x/100;
        yLoc = y/100;
        Icon icon;

        

        //Sets icons
        if(colour == true){
            if(piece == "WHITE"){
                icon = new ImageIcon("white.png");
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

    //Get Methods
    public int getX(){
        return xLoc;
    }
    public int getY(){
        return yLoc;
    }
    public JButton getTile(){
        return tile;
    }

    //ActionListener Method

    



}