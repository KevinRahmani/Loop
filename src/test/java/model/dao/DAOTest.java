package model.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.entity.AutomobileEntity;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {
    //Automobile entity test
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private DAO<AutomobileEntity> dao;
    private AutomobileEntity autoEntity;

    void initialize(){
        autoEntity = new AutomobileEntity();
        autoEntity.setCouleur("default");
        autoEntity.setDescription("default");
        autoEntity.setNom("default");
        autoEntity.setMarque("default");
        autoEntity.setImage("default");
        autoEntity.setPrix(0);
        autoEntity.setSales(0);
        autoEntity.setStock(0);
        autoEntity.setType("default");
        autoEntity.setVendeur("default");
    }

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        dao = new DAO<>(AutomobileEntity.class, entityManager);
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
        AutomobileEntity createdEntity = dao.create(autoEntity);
        //THEN
        assertNotNull(dao.findById(autoEntity.getId()));
        assertNotNull(createdEntity);
    }
    @Test
    @DisplayName("Should findById")
    void findById() {
        //GIVEN
        initialize();
        AutomobileEntity createdEntity = dao.create(autoEntity);
        //WHEN
        AutomobileEntity foundEntity = dao.findById(createdEntity.getId());
        //THEN
        assertNotNull(foundEntity);
        assertEquals(createdEntity, foundEntity);
    }

    @Test
    @DisplayName("should save")
    void save() {
        // GIVEN
        initialize();
        // WHEN
        dao.save(autoEntity);
        // THEN
        AutomobileEntity savedEntity = dao.findById(autoEntity.getId());
        assertNotNull(savedEntity);
        assertEquals(savedEntity, autoEntity);
    }

    @Test
    @DisplayName("Should update")
    void update() {
        // GIVEN
        initialize();
        dao.save(autoEntity);
        autoEntity.setNom("Updated Name");
        autoEntity.setPrix(5000);
        // WHEN
        dao.update(autoEntity);
        AutomobileEntity updatedEntity = dao.findById(autoEntity.getId());
        // THEN
        assertNotNull(updatedEntity);
        assertNotEquals("default", updatedEntity.getNom());
        assertNotEquals(0,updatedEntity.getPrix());
        assertEquals("Updated Name", updatedEntity.getNom());
        assertEquals(5000, updatedEntity.getPrix());
    }


    @Test
    @DisplayName("Should delete")
    void delete() {
        // GIVEN
        initialize();
        dao.save(autoEntity);
        // WHEN
        int entityId = autoEntity.getId();
        dao.delete(autoEntity);
        // THEN
        assertNull(dao.findById(entityId));
    }


    @Test
    @DisplayName("should findByFilters")
    void findByFilters() {
        //GIVEN
        AutomobileEntity P208 = dao.findById(1);
        ProductSearchDTO dto = new ProductSearchDTO();
        dto.setNom("208");
        dto.setMarque("Peugeot");
        //WHEN
        List<AutomobileEntity> result = dao.findByFilters(dto);
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.get(0), P208);
        assertEquals(2, result.size());
    }
}
