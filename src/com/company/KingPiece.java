package com.company;

public class KingPiece extends Piece {

    public boolean Move(Board b, int x, int y) {

        Piece jumpPiece = b.getPieceByCoord((this.x_cord+(this.forward)),(jumpY));
        String jumpPieceColor = jumpPiece.getPiece_color();

        if(this.y_cord < y){
            jumpY = Math.abs(y-1);
        }else{
            jumpY = Math.abs(y+1);
        }

        boolean retVal = false;
       if(Math.abs(y-this.y_cord)==1 && (Math.abs(x-this.x_cord) == 1)){
            b.removePieceOnSpace(this.x_cord,this.y_cord);
            b.setPieceOnSpace(this,x,y);
            this.x_cord = x;
            this.y_cord=y;
            retVal = true;
        }else if(Math.abs(y-this.y_cord)==2 && (Math.abs(x - this.x_cord) == 2)&& !jumpPieceColor.equals(this.piece_color)) {
           System.out.println("jump boy");
           //remove jumped piece
           b.removePieceOnSpace((this.x_cord + (this.forward)), jumpY);
           //remove the piece we moved
           b.removePieceOnSpace(this.x_cord, this.y_cord);
           //set the piece down we moved
           b.setPieceOnSpace(this, x, y);
           this.x_cord = x;
           this.y_cord = y;
           retVal = true;
       }
       return retVal;
    }

    public KingPiece(String name, String color) {
        super(name, color);
        //this.forward=1;

    }
}
