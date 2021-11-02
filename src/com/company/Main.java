package com.company;

public class Main {

    public static void main(String[] args) {

        Game myGame = new Game();
        myGame.Play();

        myGame.save_game();

        Game newGame = Game.load_game();
        newGame.show();

        newGame.turn();



        // write your code here
       /* KingPiece red_k_1 = new KingPiece("_KR0-2_","R");
        RegularPiece red_1 = new RegularPiece("__R0-0_","R");
        Board myBoard = new Board();

        Piece myPiece = new KingPiece("_KR0-3_","R");


        myBoard.setPieceOnSpace(myPiece,0,3);

        myBoard.setPieceOnSpace(red_1,0,0);
        myBoard.setPieceOnSpace(red_k_1,0,2);
        myBoard.showBoard();

        myPiece.Move(myBoard,1,4);
        myBoard.showBoard();

        RegularPiece red_2 = new RegularPiece("__R0-2_","R");
        myBoard.setPieceOnSpace(red_2,1,3);
        myBoard.showBoard();

        //valid movement
        red_1.Move(myBoard,1,1);
        myBoard.showBoard();

        //invalid movement
        red_1.Move(myBoard,2,1);
        myBoard.showBoard();

        //invalid, this is reverse
        red_1.Move(myBoard,0,0);
        myBoard.showBoard();

        //valid movement
        red_1.Move(myBoard,2,2);
        myBoard.showBoard();

        //move the king piece forward
        red_k_1.Move(myBoard,1,1);
        myBoard.showBoard();

        //move the king forward again
        red_k_1.Move(myBoard,2,0);
        myBoard.showBoard();

        //move the king back
        red_k_1.Move(myBoard,1,1);
        myBoard.showBoard();

        KingPiece white_k_1 = new KingPiece("_WRK-2_","W");
        RegularPiece white_1 = new RegularPiece("__W0-0_","W");

        myBoard.showBoard();

        myBoard.setPieceOnSpace(white_1,5,2);
        myBoard.showBoard();

        white_1.Move(myBoard,4,1);
        myBoard.showBoard();

        myBoard.setPieceOnSpace(white_k_1,7,1);
        myBoard.showBoard();

        white_k_1.Move(myBoard,6,0);
        myBoard.showBoard();

        white_k_1.Move(myBoard,7,1);
        myBoard.showBoard();

        white_1.Move(myBoard,5,2);
        myBoard.showBoard();

        Board newBoard = new Board();
        newBoard.showBoard();

        myBoard.save_board();
        newBoard = Board.load();

        newBoard.showBoard();

        white_1.Move(newBoard,3,0);
        newBoard.showBoard();

        white_1.Move(newBoard,4,1);
        newBoard.showBoard();

        white_k_1.Move(newBoard,6,2);
        newBoard.showBoard();

        white_k_1.Move(newBoard,7,1);
        newBoard.showBoard();
*/
    }
}
