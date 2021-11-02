package com.company;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Board implements Serializable {
    //Fixing git
    ArrayList<Piece> redPieces = new ArrayList<Piece>();
    ArrayList<Piece> whitePieces = new ArrayList<Piece>();

    Square[][] squares = new Square[8][8];

    public Board() {
        setBoard();
    }

    private void setBoard(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares[x][y] = new Square();
            }
            switch (x){
                case 0:
                    setOddCols(x,"R");
                    break;
                case 1:
                    setEvenCols(x,"R");
                    break;
                case 2:
                    setOddCols(x,"R");
                    break;
                case 3:
                    setAllBlank(x);
                    break;
                case 4:
                    setAllBlank(x);
                    break;
                case 5:
                    setEvenCols(x,"W");
                    break;
                case 6:
                    setOddCols(x,"W");
                    break;
                case 7:
                    setEvenCols(x,"W");
                    break;
            }
        }
    }

    private void setEvenCols(int row, String color){
        for(int y = 0;y<8;y++){
            if(y%2==0) {
                Piece thisPiece = new RegularPiece("__" + color + row + "-" + y + "_", color);
                thisPiece.setFriendly_name(color+row+"-"+y);
                setPieceOnSpace(thisPiece, row, y);
                thisPiece.setX_cord(row);
                thisPiece.setY_cord(y);
            }
            else{
                Piece emptyPiece = new RegularPiece("__" + row + "-" + y + "__","");
                emptyPiece.setFriendly_name("empty");
                setPieceOnSpace(emptyPiece,row,y);
            }
        }
    }

    private void setOddCols(int row, String color){
        for(int y = 0;y<8;y++){
            if(y%2!=0) {
                Piece thisPiece = new RegularPiece("__" + color + row + "-" + y + "_", color);
                thisPiece.setFriendly_name(color+row+"-"+y);
                setPieceOnSpace(thisPiece, row, y);
                thisPiece.setX_cord(row);
                thisPiece.setY_cord(y);
            }
            else{
                Piece emptyPiece = new RegularPiece("__" + row + "-" + y + "__","");
                emptyPiece.setFriendly_name("empty");
                setPieceOnSpace(emptyPiece,row,y);
            }
        }
    }

    public void setAllBlank(int row){
        for (int y=0;y<8;y++){
            Piece emptyPiece = new RegularPiece("","");
            setPieceOnSpace(emptyPiece,row,y);
            emptyPiece.setPiece_name("__" + row + "-" + y + "__");
            emptyPiece.setFriendly_name("empty");
            //emptyPiece.setFullName("empty");
        }
    }


    public ArrayList<Piece> showBoard() {
        System.out.println("======================================================");
        //print first row
        ArrayList<Piece> tempList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(squares[x][y].getPieceName());
                tempList.add(squares[x][y].getPiece());
            }
            System.out.println();
        }
        return tempList;
    }
    //

    public void setPieceOnSpace(Piece piece, int x, int y ) {

        squares[x][y].setPiece(piece);
        piece.setY_cord(y);
        piece.setX_cord(x);
        System.out.println(squares[x][y].getPiece().getFriendly_name());
    }

    public void removePieceOnSpace(int x, int y) {
        Piece emptyPiece = new RegularPiece("","");
        setPieceOnSpace(emptyPiece,x,y);
        emptyPiece.setFriendly_name("empty");
        emptyPiece.setPiece_name("__" + x + "-" + y + "__");
    }

    public boolean isSquareBlank(int x, int y){
        if(squares[x][y].getPiece().getFriendly_name().equals("empty")){
            return true;
        }else{
            return false;
        }
    }

    public Piece findPiece(String pieceName, Player player)throws InterruptedException{
        ArrayList<Piece> listOPieces = getPieces();
        Piece returnPiece = new RegularPiece("","");
        returnPiece = null;
        for(Piece p : listOPieces){
            if(Objects.isNull(p.getFriendly_name())){
                returnPiece = null;
                System.out.println("The piece doesn't exist");
                TimeUnit.SECONDS.sleep(1);
            }
            if(p.getFriendly_name().equals(pieceName) && player.getColor().equals(p.getPiece_color())){
                returnPiece = p;
                return returnPiece;
            }
        }
        return returnPiece;

    }


    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> tempList = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tempList.add(squares[x][y].getPiece());
            }
        }
        return tempList;
    }

    public void save_board(){
        try {
            FileOutputStream st = new FileOutputStream("board.txt");
            ObjectOutputStream ot = new ObjectOutputStream(st);
            ot.writeObject(this);
            ot.close();
        }catch (Exception ex){
            System.out.println("Error occured");
        }
    }

    public static Board load(){
        Board myboard = new Board();
        try{
            FileInputStream st = new FileInputStream("board.txt");
            ObjectInputStream ot = new ObjectInputStream(st);
            myboard = (Board) ot.readObject();
        }catch(Exception ex){
            System.out.println("Error");
        }
        return myboard;
    }

}