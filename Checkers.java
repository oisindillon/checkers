import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkers{

    private JFrame board = new Board();
    private Square[] tileArray = new Square[8*8];

    public static void main(String[] args){
        Checkers check = new Checkers();
        genBoard(check);
    }

    private static void genBoard(Checkers c){
        //initialising variables
        boolean colour = false;
        String type;

        for(int i=0; i<8; i++){
            //Changes starting colour of each row
            if(colour == true)
                colour = false;
            else
                colour = true;
            
            for(int j=0; j<8; j++){
                type = "NONE";
                //Makes the colours alternate
                if(colour == true){
                    colour = false;
                }
                else{
                    colour = true;
                    if(i <=8 && i >=5){
                        type = "WHITE";
                    }
                }
                
                //generates tile
                c.getArray()[i*8 + j] = new Square(i*100,j*100,colour,type);
                //tileArray[i*8 + j].getTile().addActionListener(this);

                //Adds the tile to the board
                c.getBoard().add(c.getArray()[i*8 + j].getTile());

            }

        }
        c.getBoard().setVisible(true);
    }

    public JFrame getBoard(){
        return board;
    }

    public Square[] getArray(){
        return tileArray;
    }

}