import javax.swing.*;

import sun.tools.jar.resources.jar_es;

import java.awt.*;
import java.text.*;

public class Board{

    public static void main(String[] args){
        genBoard();

    }

    private static void genBoard(){
        //initialises the frame
        JFrame frame = new JFrame("Checkers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean colour = false;
        frame.setSize(800,800);
        Square[] tileArray = new Square[8*8];
        frame.setLayout(new GridLayout(8, 8));
        for(int i=0; i<8; i++){
            //Changes starting colour of each row
            if(colour == true)
                colour = false;
            else
                colour = true;
            for(int j=0; j<8; j++){
                //Makes the colours alternate
                if(colour == true)
                    colour = false;
                else
                    colour = true;

                tileArray[i*8 + j] = new Square(i*100,j*100,colour);
                //Adds the tile to the board
                frame.add(tileArray[i*8 + j].getTile());
            }
        }
        frame.setVisible(true);
    }
}