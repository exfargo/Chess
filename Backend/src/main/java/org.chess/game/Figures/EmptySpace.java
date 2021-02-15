package game.Figures;

import utils.Pair;

public class EmptySpace implements IFigure{
    @Override
    public Teams getOwner() {
        return Teams.Empty;
    }

    @Override
    public String getType() {
        return "EmptySpace";
    }

    @Override
    public boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2) {
        //lol
        return false;
    }
}
