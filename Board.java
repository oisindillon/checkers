import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame{

    //private JFrame frame = new JFrame("Checkers");

    public Board(){

        //initialises the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLayout(new GridLayout(8, 8));

    }


    /*public void actionPerformed(ActionEvent e) {
        ActionListener a = new AnActionListener();
        for(int i =0; i<64; i++){
            if(e.getSource()== tileArray[i].getTile()){
                System.out.println(tileArray[i].getX());
                System.out.println(tileArray[i].getY());
            }
            else{
                tileArray[i].getTile().addActionListener(secondClick);
            }
            tileArray[i].getTile().removeActionListener(this);
        }
    }*/


    
}