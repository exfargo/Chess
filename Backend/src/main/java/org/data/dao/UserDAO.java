package org.data.dao;

import org.data.entities.User;
import org.data.entities.User_;

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
    }

    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    public void changeScore(int id, Long amount) {
        entityManager.getTransaction().begin();
        User u = get(id);
        u.changePoints(amount);
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

    public void changePassword(int id, String password) {
        entityManager.getTransaction().begin();
        User u = get(id);
        u.changePassword(password);
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

    public void changeUserName(int id, String username) {
        entityManager.getTransaction().begin();
        User u = get(id);
        u.changeName(username);
        entityManager.merge(u);
        entityManager.getTransaction().commit();
    }

    public List<User> getTop(int top) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.orderBy(cb.desc(rootEntry.get(User_.POINTS)));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        List<User> r = typedQuery.getResultList();
        if (top < r.size()) {
            return r.subList(0, top);
        } else {
            return r;
        }
    }

    public List<User> getPleb(int bot) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        cq.orderBy(cb.asc(rootEntry.get(User_.POINTS)));
        TypedQuery<User> typedQuery = entityManager.createQuery(cq);
        List<User> r = typedQuery.getResultList();
        if (bot < r.size()) {
            return r.subList(0, bot);
        } else {
            return r;
        }
    }
}
