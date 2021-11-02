package com.company;

import java.io.Serializable;

public abstract class Piece implements Serializable {
    String piece_name;
    String friendly_name;
    String piece_color;
    int x_cord;
    int y_cord;


    public String getFriendly_name() {
        return friendly_name;
    }

    public void setFriendly_name(String friendly_name) {
        this.friendly_name = friendly_name;
    }

    public String getPiece_name() {
        return piece_name;
    }

    public void setPiece_name(String piece_name) {
        this.piece_name = piece_name;
    }

    public int getX_cord() {
        return x_cord;
    }

    public void setX_cord(int x_cord) {
        this.x_cord = x_cord;
    }

    public int getY_cord() {
        return y_cord;
    }

    public void setY_cord(int y_cord) {
        this.y_cord = y_cord;
    }

    public String getPiece_color() {
        return piece_color;
    }

    public void setPiece_color(String piece_color) {
        this.piece_color = piece_color;
    }

    public Piece(String piece_name, String piece_color) {
        this.piece_name = piece_name;
        this.piece_color=piece_color;
    }

    public abstract boolean Move(Board b, int x, int y);


}
