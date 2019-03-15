import javax.swing.*;
import java.awt.*;

public class Board{
    public static void main(String[] args){
        genBoard();

    }

    private static void genBoard(){
        JFrame frame = new JFrame();
        frame.setTitle("Checkers");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean colour = true;
        frame.setSize(800,800);
        Square[] tileArray = new Square[8*8];
        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++){
                if(colour == true){
                    colour = false;
                }
                else{
                    colour = true;
                }
                tileArray[i*8 + j] = new Square(i*100,j*100,colour);
            }
        }
    }
}