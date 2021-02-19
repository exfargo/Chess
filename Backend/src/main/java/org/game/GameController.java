package org.game;

import org.game.Figures.Teams;
import org.utils.Coordinates;
import org.utils.Pair;

public class GameController implements  IGameController{

    private final Board board = new Board();


    @Override
    public boolean makeMove(Pair<Integer, Integer> source, Pair<Integer, Integer> target, byte player)
    {
        return board.movePiece(source,target);
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
    public IGameController initializeGame() {
        if (false) System.out.println("xd");
        return null;
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
        System.out.println("pass : " + board.getPassX() + ", " +board.getPassY() + ", " + board.getPassTeam());
        System.out.println("White check : "+ board.getWhiteCheckMate());
        System.out.println("Black check : "+ board.getBlackCheckMate());
        System.out.println("Winner : " + board.getWinner().toString());

    }
}
