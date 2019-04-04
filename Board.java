import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JFrame implements ActionListener{

    //attributes
    private Checkers check;
    private Boolean firstClick = true;
    private int previous;
    private Boolean whiteTurn = true;

    //constructor
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

    //methods
    public void showOption(int change, boolean add, Square source){    //Shows the yellow panels where moves are possible
        
        if(add == true){
            if(change != 0){
                //checks to make sure the square in front is empty and that there is no overlapping from the array on the board
                int jumps =0;
                for(int j=0; j<64; j++){
                    int middle = check.getArray()[j].getX() - source.getX();
                    middle = middle/2;
                    middle = previous+change*8+middle;
                    Square mid = check.getArray()[middle];  //Sets a temporary "middle" value (value inbetween target and source)
                    if(source.canJumpTo(check.getArray()[j], whiteTurn, mid)==true){
                        check.getArray()[j].editPiece("MAYBE");
                        jumps++;
                    }
                }
                //only if there are no jumps available then it will show the moves available
                if(jumps == 0){
                    for(int j=0; j<64; j++){
                    if(source.canMoveTo(check.getArray()[j], whiteTurn)==true){
                        check.getArray()[j].editPiece("MAYBE");
                    }
                    }
                }
                
                
            }
        }
        else{   //removes the yellow option tiles after the checker has not been moved
                //checks to see if the piece is yellow and then changes it to white
                //does not need to check for overlap as the overlapped tiles aren't yellow
            for(int j=0; j<64; j++){
                if(check.getArray()[j].getPiece()=="MAYBE"){
                    check.getArray()[j].editPiece("NONE");
                }
            }
        }
    }

    public boolean checkPossibleJump(int change){
        int jumps =0;
        for(int i=0; i<64; i++){
            for(int j=0; j<64; j++){
                int middle = check.getArray()[j].getX() - check.getArray()[i].getX();
                middle = middle/2;
                middle = previous+change*8+middle;
                Square mid = check.getArray()[middle];  //Sets a temporary "middle" value (value inbetween target and source)
                if(check.getArray()[i].canJumpTo(check.getArray()[j], whiteTurn, mid)==true){
                    jumps++;
                }
            }
        }
        if(jumps >0){
            System.out.println(jumps);
            return true;
        }
        else{
            return false;
        }
        
    }

    //ActionEvent for clicks
    public void actionPerformed(ActionEvent e){
        int change;
        //Identifies first click
        if(firstClick==true){
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){
                    previous = i;
                }
            }
            firstClick = false;
            change = check.getArray()[previous].isWhite();              //works out diretion pieces are meant to move in
            this.showOption(change, true, check.getArray()[previous]);  //displays possible moves by highlighting them
        }
        else{   //Identifies second click
            for(int i =0; i<64; i++){
                if(e.getSource() == check.getArray()[i].getTile()){//Identifies which tile is selected
                    
                    change = check.getArray()[previous].isWhite();//works out diretion pieces are meant to move in

                    //works out what the middle tile bewtween the previous and selected value
                    int middle = check.getArray()[i].getX() - check.getArray()[previous].getX();
                    middle = middle/2;
                    middle = previous+change*8+middle;


                    //Moves the piece
                    if(check.getArray()[i].getPiece() == "MAYBE"){  //only lets the peice move if its appears as yellow (a possible move)
                        
                        if(check.getArray()[previous].canJumpTo(check.getArray()[i], whiteTurn, check.getArray()[middle]) == true){
                            check.getArray()[middle].editPiece("NONE");
                        }   //if the jump was big then it will change the middle piece so there is nothing there (the piece is eaten)

                        check.getArray()[previous].editPiece(check.getArray()[previous].moveTo(check.getArray()[i]));   //performs move to function
                        
                        //Changes turns of the player
                        if(whiteTurn==true)
                            whiteTurn=false;
                        else
                            whiteTurn=true;
                        
                    }
                    this.showOption(change, false, check.getArray()[previous]);//removes the highlighted options
                    change = check.getArray()[previous].isWhite();//works out diretion pieces are meant to move in
                    if(checkPossibleJump(change)==true){
                        System.out.println("It works");
                    }
                }
            }
            firstClick = true;
        }
    }
}