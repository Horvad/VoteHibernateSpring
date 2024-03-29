package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IArtistDao;
import by.it_academy.MDK29522.core.dto.ArtistDTO;
import by.it_academy.MDK29522.dao.entity.Artist;
import by.it_academy.MDK29522.dao.support.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ArtistDaoDB implements IArtistDao {

    private final IManager manager;

    public ArtistDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(Artist artist) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(artist);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e){
            if(entityManager != null)
                entityManager.getTransaction().rollback();
        } finally {
            if(entityManager!=null)
                entityManager.close();
        }
    }

    @Override
    public List<Artist> getAll() {
        EntityManager entityManager = null;
        List<Artist> artists = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Artist> query = builder.createQuery(Artist.class);
            Root<Artist> artistRoot = query.from(Artist.class);
            query.select(artistRoot);
            artists = entityManager.createQuery(query).getResultList();
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
        return artists;
    }

    @Override
    public Artist getById(long id) {
        EntityManager entityManager = null;
        Artist artist = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Artist> query = builder.createQuery(Artist.class);
            Root<Artist> artistRoot = query.from(Artist.class);
            query.select(artistRoot).where(builder.equal(artistRoot.get("id"),id));
            artist = entityManager.createQuery(query).getSingleResult();
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
        return artist;
    }

    @Override
    public Artist getByName(String name) {
        Artist artist =  null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Artist> query = builder.createQuery(Artist.class);
            Root<Artist> artistRoot = query.from(Artist.class);
            query.select(artistRoot).where(builder.equal(artistRoot.get("name"), name));
            artist = entityManager.createQuery(query).getSingleResult();
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
        return artist;
    }

    @Override
    public boolean exist(String name) {
        Artist artist =  null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Artist> query = builder.createQuery(Artist.class);
            Root<Artist> artistRoot = query.from(Artist.class);
            query.select(artistRoot).where(builder.equal(artistRoot.get("name"), name));
            artist = entityManager.createQuery(query).getSingleResult();
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
        if(artist!=null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void edit(long id, String name) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Artist> update = builder.createCriteriaUpdate(Artist.class);
            Root<Artist> artistRoot = update.from(Artist.class);
            update.set("name",name);
            update.where(builder.equal(artistRoot.get("id"),id));
            entityManager.createQuery(update).executeUpdate();
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
    public void delete(long id) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaDelete<Artist> delete = builder.createCriteriaDelete(Artist.class);
            Root<Artist> artistRoot = delete.from(Artist.class);
            delete.where(builder.equal(artistRoot.get("id"),id));
            entityManager.createQuery(delete).executeUpdate();
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
}
