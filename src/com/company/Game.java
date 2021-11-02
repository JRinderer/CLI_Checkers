package com.company;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Game implements Serializable {
    Player red_player = new Player();
    Player white_player = new Player();
    Board myBoard = new Board();


    public Player getRed_player() {
        return red_player;
    }

    public void setRed_player(Player red_player) {
        this.red_player = red_player;
    }

    public Player getWhite_player() {
        return white_player;
    }

    public void setWhite_player(Player white_player) {
        this.white_player = white_player;
    }

    public Board getMyBoard() {
        return myBoard;
    }

    public void setMyBoard(Board myBoard) {
        this.myBoard = myBoard;
    }

    public void show() {
        this.myBoard.showBoard();
    }

    public void Play() {
        int x = 0;
        int y = 0;
        boolean validMove = false;
        boolean over = false;
        //stores the piece a user wants to move
        String pieceName = "";
        //show the board
        this.myBoard.showBoard();


        while (!over) {
            System.out.println("Enter a piece name: ");
            Scanner myScanner = new Scanner(System.in);
            pieceName = myScanner.nextLine();
            Piece pieceHolder = new RegularPiece("", "");

            try {
                pieceHolder = this.myBoard.findPiece(pieceName,red_player);
            } catch (Exception ex) {
                System.out.println("Error message in finding piece");
            }
            while (Objects.isNull(pieceHolder)) {
                System.out.println("Type a piece name: ");
                pieceName = myScanner.nextLine();
                try {
                    pieceHolder = this.myBoard.findPiece(pieceName,red_player);
                } catch (Exception ex) {
                    System.out.println("That piece doesn't exist");
                }
            }

            try {
                System.out.println("Enter an X coordinate to move the piece to: ");
                x = myScanner.nextInt();
                System.out.println("Enter an Y coordinate to move the piece to: ");
                y = myScanner.nextInt();
                myScanner.nextLine();
                validMove = pieceHolder.Move(this.myBoard, x, y);
            } catch (Exception ex) {
                System.out.println("That's invalid input");
            }
            this.myBoard.showBoard();
        }


    }

    public void save_game() {
        try {
            FileOutputStream st = new FileOutputStream("game.txt");
            ObjectOutputStream ot = new ObjectOutputStream(st);
            ot.writeObject(this);
            ot.close();
        } catch (Exception ex) {
            System.out.println("Error occured");
        }
    }

    public void turn() {
        System.out.println("Is red's turn? " + this.red_player.isTurn());
        System.out.println("Is white's turn? " + this.white_player.isTurn());
    }


    public static Game load_game() {
        Game myGame = new Game();
        try {
            FileInputStream st = new FileInputStream("game.txt");
            ObjectInputStream ot = new ObjectInputStream(st);
            myGame = (Game) ot.readObject();
        } catch (Exception ex) {
            System.out.println("Error");
        }
        return myGame;
    }

    public Game() {
        this.myBoard = new Board();
        this.red_player = new Player("R");
        this.white_player = new Player("W");
        //set turns
        this.red_player.setTurn(true);
        this.white_player.setTurn(false);

    }
}
