package org.managers;

import org.data.dao.ChallengeDAO;
import org.data.dao.GameDAO;
import org.data.entities.Challenge;
import org.data.entities.Game;
import org.data.entities.Move;
import org.data.entities.User;
import org.game.GameController;
import org.utils.Pair;
import org.utils.Utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class GameManager {

    @Inject
    GameDAO gameDAO;
    @Inject
    ChallengeDAO challengeDAO;

    @Inject
    GameControllerPool gameControllerPool;

    public long createGame(User u1, User u2) {
        Game g = new Game(new Pair<>(u1, u2));
        gameDAO.save(g);
        gameControllerPool.addController(g.getId(), new GameController());
        return g.getId();
    }

    public Game getGame(long id) {
        return gameDAO.get(id);
    }

    public void makeMove(long id, Move move) {
        gameControllerPool.retrieveController(id).makeMove(new Pair<>(move.getxSource(), move.getySource()), new Pair<>(move.getxTarget(), move.getyTarget()));
        gameDAO.writeMove(id, move);
    }

    public List<Long> getForUser(User user) {
        return Utils.extractIds(gameDAO.getGameWherePlayer(user));
    }

    public void matchChallenges() {
        List<Challenge> toDelete = new LinkedList<>();
        for (Challenge c : challengeDAO.getAll()) {
            if (c.isAccepted()) {
                toDelete.add(c);
                this.createGame(c.getChallenger(), c.getChallenged());
            }
        }
        for (Challenge c : toDelete) {
            challengeDAO.clearChallenge(c);
        }
    }
}
