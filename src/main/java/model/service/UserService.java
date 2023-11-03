package model.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.dao.UserDAO;
import model.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserService<T> implements UserDAO<T> {

    private final Class<T> entityClass;
    private final EntityManager entityManager;

    public UserService(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }
    @Override
    public T findById(int id) {return entityManager.find(entityClass, id);}

    @Override
    public void add(T entity) {
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
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findAllByFilters(UserDTO filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.getName() != null) {
            predicates.add(cb.equal(root.get("nom"), filters.getName()));
        }
        if (filters.getPassword() != null) {
            predicates.add(cb.equal(root.get("password"), filters.getPassword()));
        }
        if (filters.getMail() != null) {
            predicates.add(cb.equal(root.get("mail"), filters.getMail()));
        }
        if (filters.getAddress() != null) {
            predicates.add(cb.equal(root.get("adresse"), filters.getAddress()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public T connect(String mail, String password) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        Predicate emailPredicate = cb.equal(root.get("mail"), mail);
        Predicate passwordPredicate = cb.equal(root.get("password"), password);

        cq.where(cb.and(emailPredicate, passwordPredicate));

        List<T> results = entityManager.createQuery(cq).getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    public static boolean isVendeurEmail(String email) {
        String domain = "@loop.com";
        return email.endsWith(domain);
    }

    public static boolean isAdminEmail(String email){
        String domain = "@adminloop.com";
        return email.endsWith(domain);
    }

}
