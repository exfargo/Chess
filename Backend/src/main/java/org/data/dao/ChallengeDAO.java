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

    public List<Challenge> getChallengesForUser(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(Challenge_.CHALLENGED), user));
        TypedQuery<Challenge> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<Challenge> getChallengesByUser(User user) {
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

    public void save(Challenge challenge) {
        entityManager.getTransaction().begin();
        entityManager.persist(challenge);
        entityManager.getTransaction().commit();
    }

    public List<Challenge> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(Challenge_.id), rootEntry.get(Challenge_.id)));
        TypedQuery<Challenge> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public void clearChallenge(Challenge challenge) {
        entityManager.getTransaction().begin();
        entityManager.remove(challenge);
        entityManager.getTransaction().commit();
    }
}
