package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IVoteDao;
import by.it_academy.MDK29522.core.dto.VoteDTO;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.dao.entity.Vote;
import by.it_academy.MDK29522.dao.support.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class VoteDaoDB implements IVoteDao {
    private final IManager manager;

    public VoteDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(Vote vote) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(vote);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            if(entityManager!=null){
                entityManager.getTransaction().rollback();
            }
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
    }

    @Override
    public List<Vote> get() {
        List<Vote> votes = null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Vote> query = builder.createQuery(Vote.class);
            Root<Vote> voteRoot = query.from(Vote.class);
            query.select(voteRoot);
            votes = entityManager.createQuery(query).getResultList();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            if(entityManager!=null){
                entityManager.getTransaction().rollback();
            }
        } finally {
            if(entityManager!=null){
                entityManager.close();
            }
        }
        return votes;
    }
}
