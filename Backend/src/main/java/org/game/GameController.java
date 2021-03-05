package org.game;

import org.game.Figures.Teams;
import org.utils.Pair;

public class GameController implements  IGameController{

    private Board board = new Board();
    private Teams playersTurn = Teams.White;


    @Override
    public boolean makeMove(Pair<Integer, Integer> source, Pair<Integer, Integer> target)
    {
        if (board.movePiece(source,target,playersTurn)){
            //switch player turn
            if (playersTurn == Teams.White){
                playersTurn = Teams.Black;
            } else {
                playersTurn = Teams.White;
            }

            return true;
        }else {
            return false;
        }
    }

    @Override
    public byte isWinner() {
        return 0;
    }

    @Override
    public IBoard getBoard() {
        return this.board;
    }

    @Override
    public Teams getPlayersTurn() {
        return playersTurn;
    }

    //pouze pro testy

    /**
     * @deprecated nesahat
     */
    public void printBoard() {
        //vim ze to vypada garbage ale to me nezajima
        System.out.println("Board :");
        System.out.println("--------------");

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(board.getBoard()[x][y].getType().charAt(0) + "|");
            }
            System.out.println();
        }
        System.out.println("--------------");
        System.out.println();

        System.out.println("Teams :");
        System.out.println("--------------");
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                System.out.print(board.getBoard()[x][y].getOwner().toString().charAt(0) + "|");
            }
            System.out.println();
        }
        System.out.println("--------------");
        System.out.println("Current player : " +  (board.getCheatMode() ? "yes" : playersTurn.toString()));
        System.out.println("pass : " + board.getPassX() + ", " +board.getPassY() + ", " + board.getPassTeam());
        System.out.println("White check : "+ board.getWhiteCheck());
        System.out.println("Black check : "+ board.getBlackCheck());
        System.out.println("Winner : " + board.getWinner().toString());

    }

    public void setPlayersTurn(Teams team){
        this.playersTurn = team;
    }
    public void setBoard(Board board){
        this.board = board;
    }
}
