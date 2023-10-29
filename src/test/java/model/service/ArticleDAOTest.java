package model.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.beans.StockEntity;
import model.service.ArticleDAO;
import model.service.ArticleDTO;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDAOTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ArticleDAO<StockEntity> articleDao;
    private StockEntity stockEntity;

    void initialize(){
        stockEntity = new StockEntity();
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
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        articleDao = new ArticleDAO<>(StockEntity.class, entityManager);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("Should create")
    void create() {
        //GIVEN
        initialize();
        //WHEN
        StockEntity createdEntity = articleDao.create(stockEntity);
        //THEN
        assertNotNull(articleDao.findById(stockEntity.getId()));
        assertNotNull(createdEntity);
        articleDao.delete(stockEntity);
    }
    @Test
    @DisplayName("Should findById")
    void findById() {
        //GIVEN
        initialize();
        StockEntity createdEntity = articleDao.create(stockEntity);
        //WHEN
        StockEntity foundEntity = articleDao.findById(createdEntity.getId());
        //THEN
        assertNotNull(foundEntity);
        assertEquals(createdEntity, foundEntity);
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should save")
    void save() {
        // GIVEN
        initialize();
        // WHEN
        articleDao.save(stockEntity);
        // THEN
        StockEntity savedEntity = articleDao.findById(stockEntity.getId());
        assertNotNull(savedEntity);
        assertEquals(savedEntity, stockEntity);
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("Should update")
    void update() {
        // GIVEN
        initialize();
        articleDao.save(stockEntity);
        stockEntity.setNom("Updated Name");
        stockEntity.setPrix(5000);
        // WHEN
        articleDao.update(stockEntity);
        StockEntity updatedEntity = articleDao.findById(stockEntity.getId());
        // THEN
        assertNotNull(updatedEntity);
        assertNotEquals("default", updatedEntity.getNom());
        assertNotEquals(0,updatedEntity.getPrix());
        assertEquals("Updated Name", updatedEntity.getNom());
        assertEquals(5000, updatedEntity.getPrix());
        articleDao.delete(stockEntity);
    }
    @Test
    @DisplayName("Should delete")
    void delete() {
        // GIVEN
        initialize();
        articleDao.save(stockEntity);
        // WHEN
        int entityId = stockEntity.getId();
        articleDao.delete(stockEntity);
        // THEN
        assertNull(articleDao.findById(entityId));
    }

    @Test
    @DisplayName("should findAll")
    void findAll(){
        //GIVEN
        //WHEN
        List<StockEntity> result = articleDao.findAll();
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
        articleDao.save(stockEntity);
        ArticleDTO dto = new ArticleDTO();
        dto.setNom(stockEntity.getNom());
        dto.setMarque(stockEntity.getMarque());
        //WHEN
        List<StockEntity> result = articleDao.findAllByFilters(dto);
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByName")
    void findAllByName() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByName(stockEntity.getNom());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByMarque")
    void findAllByMarque() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByMarque(stockEntity.getMarque());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByVendeur")
    void findAllByVendeur() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByVendeur(stockEntity.getVendeur());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByType")
    void findAllByType() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByType(stockEntity.getType());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("should findAllByColor")
    void findAllByColor() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByColor(stockEntity.getCouleur());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }

    @Test
    @DisplayName("Should findAllByCategorie")
    void findAllByCategorie() {
        //GIVEN
        initialize();
        articleDao.save(stockEntity);
        //WHEN
        List<StockEntity> result = articleDao.findAllByCategorie(stockEntity.getCategorie());
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        articleDao.delete(stockEntity);
    }
}
