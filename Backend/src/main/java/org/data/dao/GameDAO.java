package org.data.dao;


import org.data.entities.Game;
import org.data.entities.Game_;
import org.data.entities.Move;
import org.data.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class GameDAO {

    @Inject
    private EntityManager entityManager;

    public Game get(int id) {
        return entityManager.find(Game.class, id);
    }

    public List<Game> getGameWherePlayer(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        Root<Game> rootEntry = cq.from(Game.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(Game_.user1), user));
        TypedQuery<Game> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<Game> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Game> cq = cb.createQuery(Game.class);
        TypedQuery<Game> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public void save(Game game) {
        entityManager.getTransaction().begin();
        entityManager.persist(game);
        entityManager.getTransaction().commit();
    }

    public void writeMove(int id, Move move) {
        entityManager.getTransaction().begin();
        Game g = get(id);
        g.writeMove(move);
        entityManager.merge(g);
        entityManager.getTransaction().commit();
    }
}
