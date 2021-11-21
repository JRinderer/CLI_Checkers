package com.company;

import java.util.ArrayList;

public class Game2ElectricBoogaLoo {
    private Player red_player = null;
    private Player white_player = null;
    private Player current_player = null;
    private Board gameBoard = null;
    private boolean gameOver = false;
    private String stringBoard = "";

    public boolean isColorAvailable(String col){
        if(!this.red_player.color.equals("") && col.equals("R")){
            setColors(col);
            return true;
        }else if(!this.white_player.color.equals("") && col.equals("W")){
            setColors(col);
            return true;
        }else{
            return false;
        }


    }

    public String serializeGameBoard(){
        return this.stringBoard = this.gameBoard.serializeBoard();
    }

    public String processPlayerInput(String input){
        int x = 0;
        int y = 0;
        boolean validMove = false;
        boolean over = false;
        String returnString = "";
        String clientColor = "";
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.red_player);
        players.add(this.white_player);

        if(!this.gameOver) {
            //stores the piece a user wants to move
            String pieceName = "";
            Piece pieceHolder = new RegularPiece("", "");

            String[] inputArray = input.split(",");

            pieceName = inputArray[0];
            x = Integer.parseInt(inputArray[1]);
            y = Integer.parseInt(inputArray[2]);
            clientColor = inputArray[3];
            this.current_player.setColor(clientColor);


            try {
                pieceHolder = this.gameBoard.findPiece(pieceName, this.current_player);
            } catch (Exception ex) {
                returnString = "Error in finding piece! Piece doesn't exist or incorrect piece color selected";
                return returnString;
            }

            //attempt to move piece
            validMove = pieceHolder.Move(this.gameBoard, x, y);

            if (!validMove) {
                returnString = "Invalid move! Try again";
            }else{
                returnString= "move successful!";
                Player.flipTurn(players);
            }
        }else{
            returnString = "game over!";
        }
        returnString = returnString;

        return returnString;

    }

    public void Start(String player1_color, String player2_color){
        if(player1_color.equals("R") && !player2_color.equals("")){
            this.red_player = new Player(player1_color);
            this.white_player = new Player(player2_color);
        }else if(player2_color.equals("R") && !player1_color.equals("")){
            this.red_player = new Player(player2_color);
            this.white_player = new Player(player1_color);
        }else if(player1_color.equals("") && player2_color.equals("")){
            this.red_player = new Player("");
            this.white_player = new Player("");
        }
        //sets our default starting turns
        this.red_player.setTurn(true);
        this.white_player.setTurn(false);
        //fills in our current player is the red player
        this.current_player = this.red_player;
        //setups the gameBoard;
        this.gameBoard = new Board();
    }

    public void setColors(String color){
        if(color.equals("R")){
            this.red_player = new Player("R");
            this.red_player.turn=true;
        }else if(color.equals("W")){
            this.white_player = new Player("W");
            this.red_player.turn=true;
        }
    }

    public Game2ElectricBoogaLoo(){

    }


}
