package org.data.dao.producers;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RequestScoped
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager = null;

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    @PreDestroy
    public void preDestroy() {
        entityManager.close();
    }
}
