package game.Figures;

import utils.Pair;

public class Queen implements IFigure{
    private final Teams owner;

    public Queen(Teams owner){
        this.owner = owner;
    }

    @Override
    public Teams getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return "Queen";
    }

    @Override
    public boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2) {
        return cords1.second() == cords2.second() || cords1.first() == cords2.first() || cords1.first() + cords1.second() == cords2.first() + cords2.second() || cords1.first() - cords1.second() == cords2.first() - cords2.second();
    }
}
