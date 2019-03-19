import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkers{

    public Square[] tileArray = new Square[8*8];

    public static void main(String[] args){
        Checkers check = new Checkers();
        JFrame board = new Board(check);
    }

    public Square[] getArray(){
        return tileArray;
    }

}