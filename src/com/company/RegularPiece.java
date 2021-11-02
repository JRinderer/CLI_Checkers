package com.company;

public class RegularPiece extends Piece{

    int forward;

    public int getForward() {
        return forward;
    }

    public void setForward(int forward) {
        this.forward = forward;
    }

    public boolean Move(Board b, int x, int y){
        //get forward movement type
        boolean retVal = false;
        if(this.piece_color.equals("R")){
            this.forward=1;
        }else{
            this.forward=-1;
        }
        //validate the move makes sense
        if(Math.abs(y-this.y_cord)==1 && (x - this.x_cord == this.forward) && b.isSquareBlank(x,y)){
            b.removePieceOnSpace(this.x_cord,this.y_cord);
            b.setPieceOnSpace(this,x,y);
            this.x_cord = x;
            this.y_cord=y;
            retVal=true;
        }
        return retVal;
    }

    public RegularPiece(String name, String color){
        super(name,color);

    }

}
