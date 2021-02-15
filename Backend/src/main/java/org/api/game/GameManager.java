package org.api.game;

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

    public boolean makeMove(Move m) {
        try {
            //if (gameController.makeMove())
            game.writeMove(m);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean endGame() {
        try {



            this.game = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
