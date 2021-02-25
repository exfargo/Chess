package org.data.dao;

import org.data.entities.*;
import org.data.entities.Challenge_;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class ChallengeDAO {

    @Inject
    private EntityManager entityManager;

    public Challenge get(long id) {
        return entityManager.find(Challenge.class, id);
    }

    public List<Challenge> getPendingChallengesForUser(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(Challenge_.CHALLENGED), user));
        TypedQuery<Challenge> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<Challenge> getPendingChallengesByUser(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(Challenge_.CHALLENGER), user));
        TypedQuery<Challenge> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public void acceptChallenge(long id) {
        entityManager.getTransaction().begin();
        get(id).setAccepted(true);
        entityManager.getTransaction().commit();
    }

    public void save(Challenge Challenge) {
        entityManager.getTransaction().begin();
        entityManager.persist(Challenge);
        entityManager.getTransaction().commit();
    }
}
