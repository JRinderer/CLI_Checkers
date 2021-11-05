package com.company;

public class RegularPiece extends Piece{


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
            this.forward_jump=2;
        }else{
            this.forward=-1;
            this.forward_jump=-2;
        }

        //returns the jumped Y coordinate
        if(this.y_cord < y){
            jumpY = Math.abs(y-1);
        }else{
            jumpY = Math.abs(y+1);
        }

        //get potential jump pieces
        Piece jumpPiece = b.getPieceByCoord((this.x_cord+(this.forward)),(jumpY));
        String jumpPieceColor = jumpPiece.getPiece_color();

        //validate the move makes sense
        if(Math.abs(y-this.y_cord)==1 && (x - this.x_cord == this.forward) && b.isSquareBlank(x,y)){
            b.removePieceOnSpace(this.x_cord,this.y_cord);
            b.setPieceOnSpace(this,x,y);
            this.x_cord = x;
            this.y_cord=y;
            retVal=true;
            //is the current x coordinate a king row?
            if(this.x_cord == this.king_row){
                upgradeToKing(b);
            }
            //check if jump
        }else if(Math.abs(y-this.y_cord)==2 && (x - this.x_cord == this.forward_jump)&& !jumpPieceColor.equals(this.piece_color)){
            System.out.println("jump boy");
            //remove jumped piece
            b.removePieceOnSpace((this.x_cord + (this.forward)),jumpY);
            //remove the piece we moved
            b.removePieceOnSpace(this.x_cord,this.y_cord);
            //set the piece down we moved
            b.setPieceOnSpace(this,x,y);
            this.x_cord = x;
            this.y_cord=y;
            if(this.x_cord == this.king_row){
                upgradeToKing(b);
            }
            //is the current x coordinate a king row?
            retVal=true;
        }



        return retVal;
    }

    private void upgradeToKing(Board b){
        //remove the regular piece
        b.removePieceOnSpace(this.x_cord,this.y_cord);
        Piece king_piece = new KingPiece("_K" + this.piece_color + this.x_cord + "-" + this.y_cord + "_", this.piece_color);
        king_piece.setFriendly_name("K" + this.piece_color + this.x_cord + "-" + this.y_cord);
        b.setPieceOnSpace(king_piece,this.x_cord,this.y_cord);
        //king_piece.forward = 1;
    }

    public RegularPiece(String name, String color){
        super(name,color);
        if(color.equals("R")){
            this.king_row=7;
        }else if(color.equals("W")){
            this.king_row=0;
        }

    }

}
