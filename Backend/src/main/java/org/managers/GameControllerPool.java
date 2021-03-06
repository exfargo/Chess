package org.managers;

import org.game.IGameController;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedHashMap;

@ApplicationScoped
public class GameControllerPool {

    private final LinkedHashMap<Long, IGameController> pool = new LinkedHashMap<>();

    public IGameController retrieveController(long id) {
        return this.pool.get(id);
    }

    public void addController(long id, IGameController iGameController) {
        this.pool.put(id, iGameController);
    }
}
