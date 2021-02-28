package org.managers;

import org.data.dao.GameDAO;
import org.data.entities.Game;
import org.data.entities.Move;
import org.data.entities.User;
import org.game.GameController;
import org.game.IGameController;
import org.utils.Pair;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class GameManager {

    @Inject
    GameDAO gameDAO;

    IGameController gameController;

    public boolean createGame(User u1, User u2) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Game getGame(int id) {
        return gameDAO.get(id);
    }

    public void writeMove(int id, Move move) {
        gameDAO.writeMove(id, move);
    }

    public boolean isYourTurn() {
        //TODO implement getMove()
        return true;
        //return gameController.getMove();
    }
}
