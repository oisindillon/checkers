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

    public void showOption(int change, boolean add){    //Shows the yellow panels where moves are possible
        if(add == true){
            if(change != 0){
                //checks to make sure the square in front is empty and that there is no overlapping from the array on the board
                if(check.getArray()[previous+1+change*8].getPiece() == "NONE" && check.getArray()[previous+1+change*8].getY() == check.getArray()[previous].getY()+change){
                    //sets the tile to yellow
                    check.getArray()[previous+1+change*8].editPiece("MAYBE");
                }
                if(check.getArray()[previous-1+change*8].getPiece() == "NONE" && check.getArray()[previous-1+change*8].getY() == check.getArray()[previous].getY()+change){
                    check.getArray()[previous-1+change*8].editPiece("MAYBE");
                }
                
            }
        }
        else{   //removes the yellow option tiles after the checker has not been moved
            //checks to see if the piece is yellow and then changes it to white
            //does not need to check for overlap as the overlapped tiles aren't yellow
            if(check.getArray()[previous+1+change*8].getPiece() == "MAYBE"){

                check.getArray()[previous+1+change*8].editPiece("NONE");
            }
            if(check.getArray()[previous-1+change*8].getPiece() == "MAYBE"){
                check.getArray()[previous-1+change*8].editPiece("NONE");
            }
        }
        
    }

    

    //ActionEvent for clicks
    public void actionPerformed(ActionEvent e) {
        int change;
        //Identifies first click
        if(firstClick==true){
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){
                    previous = i;
                }
            }
            firstClick = false;
            change = check.getArray()[previous].isWhite();//works out diretion pieces are meant to move in
            this.showOption(change, true);
            
        }
        else{   //Identifies second click
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){
                    if(check.getArray()[previous].getPiece() != "NONE"){//Makes it so only tiles with pieces on it will move
                        //Moves the piece
                        change = check.getArray()[previous].isWhite();//works out diretion pieces are meant to move in
                        this.showOption(change, false);

                        if(check.getArray()[previous].canMoveTo(check.getArray()[i]) == true){
                            check.getArray()[previous].editPiece(check.getArray()[previous].moveTo(check.getArray()[i]));
                        }
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