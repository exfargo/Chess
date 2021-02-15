package org.data.dao.producers;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager = null;

    @Produces
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
