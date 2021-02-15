package game;

import utils.Pair;

/*
IGameController is an interface for calling and referencing the game logic
*/
public interface IGameController {
    /**
     * makes move for specified player taking original and target tile location,
     * returns false if move is not possible
     *
     * @param source <x,y> of original tile
     * @param target <x,y> of target tile
     * @param player 0 is null, 1 is white, 2 is black
     * @return true if move was successful else false
     */
    boolean makeMove(Pair<Integer, Integer> source, Pair<Integer, Integer> target, byte player);

    /**
     * checks if both kings are alive, if not, returns winning player
     *
     * @return 0 = null (no one has won yet), 1 = white (has won), 2 = black (has won)
     */
    byte isWinner();

    /**
     * requests the current board
     *
     * @return Board entity
     */
    IBoard getBoard();

    /**
     * constructor invoker,
     * creates a new instance of the game
     *
     * @return itself
     */
    IGameController initializeGame();

}
