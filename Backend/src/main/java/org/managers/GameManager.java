package org.managers;

import org.data.entities.Game;
import org.data.entities.Move;
import org.data.entities.User;
import org.game.GameController;
import org.utils.Pair;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class GameManager {

    private Game game = null;
    private GameController gameController = null;

    public boolean createGame(User u1, User u2) {
        try {
            game = new Game(new Pair<>(u1, u2));
            gameController = new GameController();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
