package model.service;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import model.dao.GenericDAO;

import java.util.List;

public class ArticleDAO<T> implements GenericDAO<T> {
    private final Class<T> entityClass;
    private final EntityManager entityManager;

    public ArticleDAO(Class<T> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
    }

    //TO DO : verifier si les champs de l'entité ne soient pas nuls avant de persist
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
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        return entityManager.createQuery(cq).getResultList();
    }

    //Specific functions to stock
    public List<T> findAllByFilters(ArticleDTO filters) {
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
        if (filters.getCategorie() != null) {
            predicates.add(cb.equal(root.get("categorie"), filters.getCategorie()));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findAllByCategorie(String categorie){
        ArticleDTO dto = new ArticleDTO();
        dto.setCategorie(categorie);
        return findAllByFilters(dto);
    }

    public List<T> findAllByName(String name) {
        ArticleDTO dto = new ArticleDTO();
        dto.setNom(name);
        return findAllByFilters(dto);
    }


    public List<T> findAllByMarque(String marque) {
        ArticleDTO dto = new ArticleDTO();
        dto.setMarque(marque);
        return findAllByFilters(dto);
    }

    public List<T> findAllByVendeur(String vendeur) {
        ArticleDTO dto = new ArticleDTO();
        dto.setVendeur(vendeur);
        return findAllByFilters(dto);
    }

    public List<T> findAllByType(String type) {
        ArticleDTO dto = new ArticleDTO();
        dto.setType(type);
        return findAllByFilters(dto);
    }

    public List<T> findAllByColor(String color) {
        ArticleDTO dto = new ArticleDTO();
        dto.setCouleur(color);
        return findAllByFilters(dto);
    }

    public List<T> findAllByCategorieAndPriceAsc(String categorie) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get("categorie"), categorie));
        cq.orderBy(cb.asc(root.get("prix")));

        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findAllByCategorieAndPriceDesc(String categorie) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get("categorie"), categorie));
        cq.orderBy(cb.desc(root.get("prix")));

        return entityManager.createQuery(cq).getResultList();
    }

    public List<T> findAllByCategoryByBrand(String category) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);

        Predicate categoryPredicate = cb.equal(root.get("categorie"), category);
        cq.where(categoryPredicate);
        cq.orderBy(cb.asc(root.get("marque")));

        return entityManager.createQuery(cq).getResultList();
    }

    //à tester
    public T findByCategoryMaxSales(String categorie) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Root<T> root = cq.from(entityClass);

        //on cherche le sales max
        cq.select(cb.max(root.get("sales")));
        cq.where(cb.equal(root.get("categorie"), categorie));
        int maxSales = entityManager.createQuery(cq).getSingleResult();

        //DTO avec le sales max
        ArticleDTO dto = new ArticleDTO();
        dto.setSales(maxSales);
        dto.setCategorie(categorie);
        return findAllByFilters(dto).get(0);
    }


}
