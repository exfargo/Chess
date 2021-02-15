package org.game.Figures;

import org.utils.Pair;

public class King implements IFigure, IHradable {
    private final Teams owner;
    private boolean hradovaniAble = true;

    public King(Teams owner){
        this.owner = owner;
    }

    @Override
    public Teams getOwner() {
        return owner;
    }

    @Override
    public String getType() {
        return "King";
    }

    @Override
    public boolean checkMoveValidity(Pair<Integer, Integer> cords1, Pair<Integer, Integer> cords2) {
        if (Math.abs(cords1.first() - cords2.first()) == 1 || Math.abs(cords1.second() - cords2.second()) == 1){

            return true;
        }else if(hradovaniAble && Math.abs(cords1.first() - cords2.first()) == 2 && cords1.second() == cords2.second()){

            return true;
        }
        return false;
    }

    @Override
    public void setHradovniAble(boolean value){
        hradovaniAble = value;
    }
    @Override
    public boolean isHradAble(){
        return hradovaniAble;
    }
}
