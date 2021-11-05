package com.company;

public class KingPiece extends Piece {

    public boolean Move(Board b, int x, int y) {

        Piece jumpPiece = b.getPieceByCoord((this.x_cord+(this.forward)),(jumpY));
        String jumpPieceColor = jumpPiece.getPiece_color();

        int jump_x=getJumpDirectX(x);

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
           b.removePieceOnSpace(jump_x, jumpY);
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

    //king can jump in multiple directions and thus we need to determine which direction based on start and end
    public int getJumpDirectX(int end_x_posit){
        int jmpX = 0;
        if(this.x_cord < end_x_posit){
            jmpX = end_x_posit - 1;
        }else{
            jmpX = end_x_posit + 1;
        }
        return jmpX;
    }

    public KingPiece(String name, String color) {
        super(name, color);
        //this.forward=1;

    }
}
