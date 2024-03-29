package by.it_academy.MDK29522.dao.support;

import by.it_academy.MDK29522.dao.support.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager implements IManager {

    private final EntityManagerFactory entityManagerFactory;

    public Manager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("tutorial");
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
