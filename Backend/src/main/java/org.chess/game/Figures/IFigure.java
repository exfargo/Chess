package game.Figures;

import utils.Pair;

/*
    IFigure is an all general interface for figures
*/
public interface IFigure {
    /**
     * gets owner
     *
     * @return 0 = null, 1 = white, 2 = black
     */
    Teams getOwner();

    /**
     * returns figure implementation object
     *
     * @return figure implementation object
     */
    String getType();

    /**
     * @param cords1 source
     * @param cords2 target
     * @return muzu se tam pohnout ?
     *
     */
    boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2);


}
