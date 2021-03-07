package org.managers;

import org.data.dao.ChallengeDAO;
import org.data.dao.GameDAO;
import org.data.entities.Challenge;
import org.data.entities.Game;
import org.data.entities.Move;
import org.data.entities.User;
import org.game.Board;
import org.game.Figures.IFigure;
import org.game.Figures.Teams;
import org.game.GameController;
import org.game.IBoard;
import org.utils.Pair;
import org.utils.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class GameManager {

    @Inject
    GameDAO gameDAO;
    @Inject
    ChallengeDAO challengeDAO;
    @Inject
    GameControllerPool gameControllerPool;

    public void createGame(User u1, User u2) {
        Game g = new Game(new Pair<>(u1, u2));
        gameDAO.save(g);
        gameControllerPool.addController(g.getId(), new GameController());
    }

    public Game getGame(long id) {
        return gameDAO.get(id);
    }

    public boolean makeMove(long id, Move move) {
        if (gameControllerPool.retrieveController(id).makeMove(new Pair<>(move.getxSource(), move.getySource()), new Pair<>(move.getxTarget(), move.getyTarget()))) {
            gameDAO.writeMove(id, move);
            return true;
        } else return false;
    }

    public List<Long> getForUser(User user) {
        return Utils.extractIds(gameDAO.getGameWherePlayer(user));
    }

    public void matchChallenge(long id) {
        Challenge c = challengeDAO.get(id);
        this.createGame(c.getChallenger(), c.getChallenged());
        challengeDAO.clearChallenge(c);
    }

    public Teams getTurn(long id) {
        return gameControllerPool.retrieveController(id).getPlayersTurn();
    }

    public IFigure[][] getCurrentBoardById(long id) {
        return gameControllerPool.retrieveController(id).getBoard().getBoard();
    }

    public boolean playerInGameById(User loggedUser, long id) {
        return gameDAO.get(id).getUser1().getId() == loggedUser.getId() || gameDAO.get(id).getUser2().getId() == loggedUser.getId();
    }
}
