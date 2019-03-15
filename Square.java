import javax.swing.*;
import java.awt.*;

public class Square{

    private int xLoc;
    private int yLoc;

    public Square(int x, int y, boolean colour){
        xLoc = x;
        yLoc = y;
        if(colour == true){
            ImageIcon icon = new ImageIcon("C:\\Users\\oapdi\\OneDrive\\Desktop\\uni\\scc110\\Checkers Project\\scc110checkers\\empty.png");
        }
        else{
            ImageIcon icon = new ImageIcon("C:\\Users\\oapdi\\OneDrive\\Desktop\\uni\\scc110\\Checkers Project\\scc110checkers\\empty2.png");
        }
        JPanel tile = new JPanel(icon);
        tile.setLocation(xLoc, yLoc);
    }



}