package org.data.dao;



import org.data.entities.User;
import org.chess.data.entities.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class UserDAO {

    @Inject
    private EntityManager entityManager;

    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> getUserByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.select(rootEntry).where(cb.like(rootEntry.get(User_.username), name));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<User> getUserWhereScoreGreaterThan(int value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.select(rootEntry).where(cb.gt(rootEntry.get(User_.points), value));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<User> getUserWhereScoreLowerThan(int value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.select(rootEntry).where(cb.lt(rootEntry.get(User_.points), value));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<User> getUserWhereScoreEquals(int value) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.select(rootEntry).where(cb.equal(rootEntry.get(User_.points), value));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public List<User> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        return typedQuery.getResultList();
    }

    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(int id) {
        entityManager.remove(get(id));
    }

    public void changeScore(int id, Long amount) {
        User u = get(id);
        u.changePoints(amount);
        entityManager.merge(u);
    }

    public void changePassword(int id, String password) {
        User u = get(id);
        u.changePassword(password);
        entityManager.merge(u);
    }
}
