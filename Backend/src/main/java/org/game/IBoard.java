package org.game;

import org.game.Figures.IFigure;
import org.utils.Pair;

/*
Board holding class
White is on top and black on the bottom
player1 is white, player2 is black
*/
public interface IBoard {
    /**
     * returns private 2d array
     *
     * @return IFigure[][]
     */
    IFigure[][] getBoard();

    /**
     * returns tile at specified location
     *
     * @param location <x,y> location
     * @return IFigure
     */
    IFigure getTile(Pair<Integer, Integer> location);
}
