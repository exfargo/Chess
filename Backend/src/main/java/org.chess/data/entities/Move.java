package org.chess.data.entities;


import org.chess.utils.Pair;

import javax.persistence.Embeddable;

@Embeddable
public class Move {
    private int xSource;
    private int ySource;
    private int xTarget;
    private int yTarget;

    public Move(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        this.xSource = source.first();
        this.ySource = source.second();
        this.xTarget = target.first();
        this.yTarget = target.second();
    }

    public Move() {
    }

    public int getxSource() {
        return xSource;
    }

    public void setxSource(int xSource) {
        this.xSource = xSource;
    }

    public int getySource() {
        return ySource;
    }

    public void setySource(int ySource) {
        this.ySource = ySource;
    }

    public int getxTarget() {
        return xTarget;
    }

    public void setxTarget(int xTarget) {
        this.xTarget = xTarget;
    }

    public int getyTarget() {
        return yTarget;
    }

    public void setyTarget(int yTarget) {
        this.yTarget = yTarget;
    }
}
