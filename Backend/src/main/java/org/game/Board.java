package org.game;

import org.game.Figures.*;
import org.game.Figures.*;
import org.utils.Coordinates;
import org.utils.Pair;

public class Board implements IBoard{

    private final IFigure[][] board = new IFigure[8][8];
    private int passX = -1;
    private int passY = -1;
    private Teams passTeam = Teams.Empty;


    public Board(){
        //Init Board
        for (int x = 0;x < 8;x++){
            for (int y = 0;y < 8;y++){
                board[y][x] = new EmptySpace();
            }
        }


        //place pieces
        for (int repeat = 2;repeat > 0;repeat--){
            for(int x = 0;x < 8;x++){
                for (int y = 0;y < 2;y++){
                    if (repeat == 1 && y == 1 || repeat == 2 && y == 0){
                        //other pieces

                        switch (x){


                            case 7 :
                            case 0 :
                                //rooks
                                board[y + (repeat%2)*6 ][x] = new Rook(repeat == 2 ? Teams.Black : Teams.White);
                                break;

                            case 1:
                            case 6:
                                //knights
                                board[y + (repeat%2)*6 ][x] = new Knight(repeat == 2 ? Teams.Black : Teams.White);
                                break;

                            case 2:
                            case 5:
                                //priests xd
                                board[y + (repeat%2)*6 ][x] = new Bishop(repeat == 2 ? Teams.Black : Teams.White);
                                break;

                            case 3:
                                //queen
                                board[y + (repeat%2)*6 ][x] = new Queen(repeat == 2 ? Teams.Black : Teams.White);
                                break;
                            case 4:
                                //king
                                board[y + (repeat%2)*6 ][x] = new King(repeat == 2 ? Teams.Black : Teams.White);
                                break;
                            default:
                                System.out.println("Je tu nějaký strážník, pane probléme.");
                                break;
                        }

                    }else {
                        //pawns
                        board[y + (repeat%2)*6 ][x] = new Pawn(repeat == 2 ? Teams.Black : Teams.White);
                    }
                }
            }
        }
    }

    @Override
    public IFigure[][] getBoard() {
        return board;
    }

    @Override
    public IFigure getTile(Pair<Integer, Integer> location) {
        return board[location.second()][location.first()];
    }

    public boolean movePiece(Pair<Integer, Integer> source, Pair<Integer, Integer> target){
        /*
          nekdy mít promenou navíc pomáhá čitelnosti kódu
          možná mě to melo napadnout dřív
          anyway...
         */
        IFigure selectedPiece = board[source.second()][source.first()];


        //hledí na : move validity ,los a friendly fire
        if (board[source.second()][source.first()].checkMoveValidity(source,target)
        && (board[source.second()][source.first()].getType().equals("Knight") || checkLos(source,target))
        && board[source.second()][source.first()].getOwner() != board[target.second()][target.first()].getOwner()){

            //pawn shenanigans
            if (board[source.second()][source.first()].getType().equals("Pawn")) {
                if (source.first().equals(target.first())) {
                    //aby se nemohl vyhazovat veci pred sebou
                    if (board[target.second()][target.first()].getType().equals("EmptySpace")) {
                        teleportPiece(source, target);
                        //en passant


                        if (Math.abs(source.second() - target.second()) == 2) {
                            passX = target.first();
                            passTeam = board[target.second()][target.first()].getOwner();
                            if (board[target.second()][target.first()].getOwner().equals(Teams.Black)) {
                                passY = target.second() - 1;
                            } else {
                                passY = target.second() + 1;
                            }
                            //promotion
                        }
                        if (board[target.second()][target.first()].getOwner().equals(Teams.Black) && target.second() == 7) {
                            board[target.second()][target.first()] = new Queen(Teams.Black);
                        } else if (board[target.second()][target.first()].getOwner().equals(Teams.White) && target.second() == 0) {
                            board[target.second()][target.first()] = new Queen(Teams.White);
                        }
                        return true;
                    }
                    return false;
                    //vyhazovani
                } else {
                    if (!board[target.second()][target.first()].getType().equals("EmptySpace")) {
                        teleportPiece(source, target);
                        return true;
                        //en passant
                    } else if (target.first() == passX && target.second() == passY && !board[source.second()][source.first()].getOwner().equals(passTeam)) {
                        //delet the bad guy
                        if (passTeam.equals(Teams.Black)) {
                            board[target.second() + 1][target.first()] = new EmptySpace();
                        }
                        {
                            board[target.second() - 1][target.first()] = new EmptySpace();
                        }

                        teleportPiece(source, target);
                        return true;
                    }
                }
                return false;
            //hradovani & kral mvment
            }else if (selectedPiece.getType().equals("King")){

                if (((IHradable)(selectedPiece)).isHradAble() && isHradingDirectionValid( source, target,selectedPiece.getOwner()) && Math.abs((target.first() - source.first())) == 2) {

                }else {
                    teleportPiece(source,target);
                    ((IHradable)(selectedPiece)).setHradovniAble(false);
                    return true;
                }

                return false;

            }else{
                //not hradable
                if(selectedPiece.getType().equals("Rook")) ((IHradable)(selectedPiece)).setHradovniAble(false);
                teleportPiece(source,target);
                return true;

            }

        }else{
            return false;
        }

    }
    //aby jednotky nenoclipovaly skrz sebe
    private boolean checkLos(Pair<Integer,Integer> source, Pair<Integer,Integer> target){
        int tempX = source.first();
        int tempY = source.second();
        while (Math.abs(tempX - target.first()) > 1 || Math.abs(tempY - target.second()) > 1){
            //move
            tempX -= Math.signum(tempX - target.first());
            tempY -= Math.signum(tempY - target.second());
            //check for collision
            if (!board[tempY][tempX].getType().equals("EmptySpace")){ return false; }
        }
        return true;
    }

    private void teleportPiece(Pair<Integer, Integer> source, Pair<Integer, Integer> target){
        board[target.second()][target.first()] = board[source.second()][source.first()];
        board[source.second()][source.first()] = new EmptySpace();
        passX = -1;
        passY = -1;
        passTeam = Teams.Empty;
    }

    public int getPassX(){
        return passX;
    }
    public int getPassY(){
        return passY;
    }
    public String getPassTeam(){
        return passTeam.toString();
    }


    /*
        xTreme lídle might rewrite later
        return boolean if position is threatened by the enemy
     */
    public boolean checkCheck(Pair<Integer,Integer> position,Teams team){
        for(int x = 0;x < 8;x++) {
            for (int y = 0; y < 8; y++) {
                if (!board[y][x].getOwner().equals(team) && !board[y][x].getOwner().equals(Teams.Empty)){
                    //nahorsi casy
                    //hors casy uz nastaly
                    if (board[y][x].checkMoveValidity(new Coordinates(x,y),position) && (checkLos( new Coordinates(x,y),position)|| (board[y][x].getType().equals("Knight")))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //returns null if figure not found
    public Coordinates findFigure(String type, Teams owner){
        for(int x = 0;x < 8;x++) {
            for (int y = 0; y < 8; y++) {
                if(board[y][x].getOwner() == owner && board[y][x].getType().equals(type)){
                    return new Coordinates(x,y);
                }
            }
        }
        return null;
    }
    private IFigure hradingRook;
    private boolean isHradingDirectionValid(Pair<Integer,Integer> source,Pair<Integer,Integer> target,Teams team){

        int tempDir = target.first() - source.first();

        if (tempDir == 1 && board[source.second()][7].getType().equals("Rook") && ((IHradable)(board[source.second()][7])).isHradAble() && checkLos(source,new Coordinates(7,source.second()))){
            hradingRook = board[source.second()][7];
            System.out.println("jis");
            return false;
        }else if(tempDir == -1 && board[source.second()][0].getType().equals("Rook") && ((IHradable)(board[source.second()][0])).isHradAble() && checkLos(source,new Coordinates(0,source.second()))){
            hradingRook = board[source.second()][0];
            System.out.println("jis");
            return true;
        }
        return false;

    }
}
