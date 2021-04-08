package org.game.Figures;

import org.utils.Pair;

public class Queen implements IFigure, Cloneable{
    private final Teams owner;
    private final String name = "Queen";

    public Queen(Teams owner){
        this.owner = owner;
    }

    @Override
    public Teams getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return name;
    }

    @Override
    public boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2) {
        return cords1.second() == cords2.second() || cords1.first() == cords2.first() || cords1.first() + cords1.second() == cords2.first() + cords2.second() || cords1.first() - cords1.second() == cords2.first() - cords2.second();
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
