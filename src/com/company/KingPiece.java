package com.company;

public class KingPiece extends Piece {

    public boolean Move(Board b, int x, int y) {
        boolean retVal = false;
       if(Math.abs(y-this.y_cord)==1 && (Math.abs(x-this.x_cord) == 1)){
            b.removePieceOnSpace(this.x_cord,this.y_cord);
            b.setPieceOnSpace(this,x,y);
            this.x_cord = x;
            this.y_cord=y;
            retVal = true;
        }
       return retVal;
    }

    public KingPiece(String name, String color) {
        super(name, color);

    }
}
