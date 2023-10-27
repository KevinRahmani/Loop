package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.ArrayList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class DAO<T> implements GenericDAO<T> {


    private final Class<T> entityClass;
    private final EntityManager entityManager;

    public DAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<T> findByFilters(ProductSearchDTO filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getNom() != null) {
            predicates.add(cb.equal(root.get("nom"), filters.getNom()));
        }
        if (filters.getMarque() != null) {
            predicates.add(cb.equal(root.get("marque"), filters.getMarque()));
        }
        if (filters.getPrix() != null) {
            predicates.add(cb.equal(root.get("prix"), filters.getPrix()));
        }
        if (filters.getVendeur() != null) {
            predicates.add(cb.equal(root.get("vendeur"), filters.getVendeur()));
        }
        if (filters.getStock() != null) {
            predicates.add(cb.equal(root.get("stock"), filters.getStock()));
        }
        if (filters.getType() != null) {
            predicates.add(cb.equal(root.get("type"), filters.getType()));
        }
        if (filters.getCouleur() != null) {
            predicates.add(cb.equal(root.get("couleur"), filters.getCouleur()));
        }
        if (filters.getDescription() != null) {
            predicates.add(cb.equal(root.get("description"), filters.getDescription()));
        }
        if (filters.getSales() != null) {
            predicates.add(cb.equal(root.get("sales"), filters.getSales()));
        }
        if (filters.getImage() != null) {
            predicates.add(cb.equal(root.get("image"), filters.getImage()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }


}
