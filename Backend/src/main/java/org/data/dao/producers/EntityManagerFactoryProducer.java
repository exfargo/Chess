package org.data.dao.producers;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProducer {

    private EntityManagerFactory instance = null;

    @Produces
    public EntityManagerFactory getEntityManagerFactory() {
        if (instance == null) {
            instance =  Persistence.createEntityManagerFactory("TestPersistence");
        }
        return instance;
    }

    @PreDestroy
    public void preDestroy() {
        instance.close();
    }
}
