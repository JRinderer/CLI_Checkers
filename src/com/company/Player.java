package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    public String color;
    public boolean turn;

    public boolean isTurn() {
        return turn;
    }

    public static void flipTurn(ArrayList<Player> players){
        for(Player plyr : players){
            plyr.setTurn(!plyr.isTurn());
        }
    }

    public static void setTotalTurn(Player p1, Player p2){
        p1.setTurn(true);
        p2.setTurn(false);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Player (String color){
        this.color=color;
    }

    public Player(){

    }

}
