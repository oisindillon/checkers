import javax.swing.*;
import java.awt.*;

public class Square{

    private int xLoc;
    private int yLoc;
    private JButton tile;

    public Square(int y, int x, boolean colour) {
        xLoc = x;
        yLoc = y;
        Icon icon;
        if(colour == true){
            icon = new ImageIcon("empty.png");
        }
        else{
            icon = new ImageIcon("empty2.png");
        }
        tile = new JButton(icon);
    }
    public int getX(){
        return xLoc;
    }
    public int getY(){
        return yLoc;
    }
    public JButton getTile(){
        return tile;
    }



}