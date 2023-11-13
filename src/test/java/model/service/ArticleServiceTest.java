package model.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.beans.ArticleEntity;
import model.dto.ArticleDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class   ArticleServiceTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ArticleService<ArticleEntity> articleService;
    private ArticleEntity stockEntity;

    void initialize(){
        stockEntity = new ArticleEntity();
        stockEntity.setCouleur("default");
        stockEntity.setDescription("default");
        stockEntity.setNom("default");
        stockEntity.setMarque("default");
        stockEntity.setImage("default");
        stockEntity.setPrix(0);
        stockEntity.setSales(0);
        stockEntity.setStock(0);
        stockEntity.setType("default");
        stockEntity.setVendeur("default");
        stockEntity.setCategorie("default");
    }

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Should findById")
    void findById() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        ArticleEntity foundEntity = articleService.findById(stockEntity.getId());
        //THEN
        assertNotNull(foundEntity);
        assertEquals(stockEntity, foundEntity);
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should add")
    void add() {
        // GIVEN
        initialize();
        // WHEN
        articleService.add(stockEntity);
        // THEN
        ArticleEntity savedEntity = articleService.findById(stockEntity.getId());
        assertNotNull(savedEntity);
        assertEquals(savedEntity, stockEntity);
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("Should update")
    void update() {
        // GIVEN
        initialize();
        articleService.add(stockEntity);
        stockEntity.setNom("Updated Name");
        stockEntity.setPrix(5000);
        // WHEN
        articleService.update(stockEntity);
        ArticleEntity updatedEntity = articleService.findById(stockEntity.getId());
        // THEN
        assertNotNull(updatedEntity);
        assertNotEquals("default", updatedEntity.getNom());
        assertNotEquals(0,updatedEntity.getPrix());
        assertEquals("Updated Name", updatedEntity.getNom());
        assertEquals(5000, updatedEntity.getPrix());
        articleService.delete(stockEntity);
    }
    @Test
    @DisplayName("Should delete")
    void delete() {
        // GIVEN
        initialize();
        articleService.add(stockEntity);
        // WHEN
        int entityId = stockEntity.getId();
        articleService.delete(stockEntity);
        // THEN
        assertNull(articleService.findById(entityId));
    }

    @Test
    @DisplayName("should findAll")
    void findAll(){
        //GIVEN
        //WHEN
        List<ArticleEntity> result = articleService.findAll();
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() > 400);
    }

    @Test
    @DisplayName("should findByAllFilters")
    void findAllByFilters() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        ArticleDTO dto = new ArticleDTO();
        dto.setNom(stockEntity.getNom());
        dto.setMarque(stockEntity.getMarque());
        //WHEN
        List<ArticleEntity> result = articleService.findAllByFilters(dto);
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByName")
    void findAllByName() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByName(stockEntity.getNom());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByMarque")
    void findAllByMarque() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByMarque(stockEntity.getMarque());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByVendeur")
    void findAllByVendeur() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByVendeur(stockEntity.getVendeur());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByType")
    void findAllByType() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByType(stockEntity.getType());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByColor")
    void findAllByColor() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByColor(stockEntity.getCouleur());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("Should findAllByCategorie")
    void findAllByCategorie() {
        //GIVEN
        initialize();
        articleService.add(stockEntity);
        //WHEN
        List<ArticleEntity> result = articleService.findAllByCategorie(stockEntity.getCategorie());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleService.delete(stockEntity);
    }

    @Test
    @DisplayName("Should findByCategorieAndMaxSales")
    void findByCategorieAndMaxSales() {
        //GIVEN
        initialize();
        stockEntity.setSales(10000); //suppose it's the highest sale
        articleService.add(stockEntity);
        //WHEN
        ArticleEntity result = articleService.findByCategorieAndMaxSales(stockEntity.getCategorie());
        //THEN
        assertNotNull(result);
        assertEquals(result,stockEntity);
        articleService.delete(stockEntity);
    }
}