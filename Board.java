import javax.swing.*;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;

import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener{

    //private JFrame frame = new JFrame("Checkers");
    private Checkers check;
    private Boolean firstClick = true;
    private int previous;

    public Board(Checkers c){
        check = c;
        //initialises the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLayout(new GridLayout(8, 8));

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
                    if(i>=0 && i<=2){
                        type = "RED";
                    }
                }
                
                //generates tile
                check.getArray()[i*8 + j] = new Square(i*100,j*100,colour,type);
                check.getArray()[i*8 + j].getTile().addActionListener(this);

                //Adds the tile to the board
                this.add(check.getArray()[i*8 + j].getTile());

            }

        }
        this.setVisible(true);

    }

    //ActionEvent for clicks
    public void actionPerformed(ActionEvent e) {
        //Identifies first click
        if(firstClick==true){
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){
                    previous = i;
                }
            }
            System.out.println("whats goin on");
            firstClick = false;        
        }
        else{   //Identifies second click
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){
                    if(check.getArray()[previous].getPiece() == "WHITE" || check.getArray()[previous].getPiece() == "RED"){
                        //Moves the piece
                        check.getArray()[previous].editPiece(check.getArray()[previous].moveTo(check.getArray()[i]));
                        //Also updates the piece of the first clicked item




                        /*System.out.println("firstTile:");
                        System.out.println(check.getArray()[previous].getPiece());
                        System.out.println(check.getArray()[previous].getIcon());
                        System.out.println("secondTile:");
                        System.out.println(check.getArray()[i].getPiece());
                        System.out.println(check.getArray()[i].getIcon());*/
                    }
                }
            }
            firstClick = true;
        }
    }
}