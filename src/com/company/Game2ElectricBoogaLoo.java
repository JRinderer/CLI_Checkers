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

    public String processPlayerInput(String p_name, String str_x, String str_y, String str_col){
        int x = Integer.parseInt(str_x);
        int y = Integer.parseInt(str_y);
        boolean validMove = false;
        boolean over = false;
        String returnString = "";
        String clientColor = str_col;
        ArrayList<Player> players = new ArrayList<>();
        players.add(this.red_player);
        players.add(this.white_player);

        if(!this.gameOver) {
            //stores the piece a user wants to move
            String pieceName = p_name;
            Piece pieceHolder = new RegularPiece("", "");

            this.current_player = Player.get_player_turn(this.red_player,this.white_player);

            this.current_player.setColor(clientColor);

            System.out.println(red_player.isTurn());
            System.out.println(white_player.isTurn());

            try {
                pieceHolder = this.gameBoard.findPiece(pieceName, this.current_player);
            } catch (Exception ex) {
                returnString = "Error:Piece not found";
                return returnString;
            }

            //attempt to move piece
            validMove = pieceHolder.Move(this.gameBoard, x, y);

            if (!validMove) {
                returnString = "Error:Invalid Move";
            }else{
                returnString= "Success:Piece Moved!";
                Player.flipTurn(players);
            }
        }else{
            returnString = "Success:Game Over";
        }
        returnString = this.gameBoard.serializeBoard() + "," + returnString;

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
