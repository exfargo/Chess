package org.game.Figures;

import org.utils.Pair;

public class Knight implements IFigure,Cloneable{
    private final Teams owner;

    public Knight(Teams owner){
        this.owner = owner;
    }

    @Override
    public Teams getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return "Knight";
    }

    @Override
    public boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2) {
        return Math.abs(cords2.first() - cords1.first()) == 1 && Math.abs(cords1.second() - cords2.second()) == 2 || Math.abs(cords2.first() - cords1.first()) == 2 && Math.abs(cords1.second() - cords2.second()) == 1;
    }
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
