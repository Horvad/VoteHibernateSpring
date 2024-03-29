package by.it_academy.MDK29522.dao.database;

import by.it_academy.MDK29522.dao.api.IGenreDao;
import by.it_academy.MDK29522.dao.entity.Genre;
import by.it_academy.MDK29522.dao.support.api.IManager;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

public class GenreDaoDB implements IGenreDao {
    IManager manager;

    public GenreDaoDB(IManager manager) {
        this.manager = manager;
    }

    @Override
    public void save(Genre genre) {
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(genre);
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
    public List<Genre> getAll() {
        List<Genre> genres = null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
            Root<Genre> genreRoot = query.from(Genre.class);
            query.select(genreRoot);
            genres = entityManager.createQuery(query).getResultList();
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
        return genres;
    }

    @Override
    public Genre getById(long id) {
        EntityManager entityManager = null;
        Genre genre = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
            Root<Genre> genreRoot = query.from(Genre.class);
            query.select(genreRoot).where(builder.equal(genreRoot.get("id"),id));
            genre = entityManager.createQuery(query).getSingleResult();
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
        return genre;
    }

    @Override
    public Genre getByName(String name) {
        Genre genre = null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
            Root<Genre> genreRoot = query.from(Genre.class);
            query.select(genreRoot).where(builder.equal(genreRoot.get("name"),name));
            genre = entityManager.createQuery(query).getSingleResult();
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
        return genre;
    }

    @Override
    public boolean exist(String name) {
        Genre genre = null;
        EntityManager entityManager = null;
        try {
            entityManager = manager.getEntityManager();
            entityManager.getTransaction().begin();
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Genre> query = builder.createQuery(Genre.class);
            Root<Genre> genreRoot = query.from(Genre.class);
            query.select(genreRoot).where(builder.equal(genreRoot.get("name"),name));
            genre = entityManager.createQuery(query).getSingleResult();
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
        if(genre!=null){
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
            CriteriaUpdate<Genre> update = builder.createCriteriaUpdate(Genre.class);
            Root<Genre> genreRoot = update.from(Genre.class);
            update.set("name",name);
            update.where(builder.equal(genreRoot.get("id"),id));
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
            CriteriaDelete<Genre> delete = builder.createCriteriaDelete(Genre.class);
            Root<Genre> genreRoot = delete.from(Genre.class);
            delete.where(builder.equal(genreRoot.get("id"),id));
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
