package model.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserService<ClientEntity> userService;
    private ClientEntity clientEntity;

    void initialize(){
        clientEntity = new ClientEntity();
        clientEntity.setPassword("default");
        clientEntity.setMail("default");
        clientEntity.setNom("default");
        clientEntity.setDatesignup(new Timestamp(System.currentTimeMillis()));
        clientEntity.setAdresse("default");
        clientEntity.setNbproduct(0);
        clientEntity.setHistocommand("default");
    }

    @BeforeEach
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("userPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        userService = new UserService<>(ClientEntity.class, entityManager);
        entityManager.getTransaction().begin();
    }

    @AfterEach
    public void tearDown() {
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("should findById")
    void findById() {
        //GIVEN
        initialize();
        userService.add(clientEntity);
        //WHEN
        ClientEntity clientFound = userService.findById(clientEntity.getId());
        //THEN
        assertNotNull(clientFound);
        assertEquals(clientEntity, clientFound);
        userService.delete(clientEntity);
    }

    @Test
    @DisplayName("should add")
    void add() {
        // GIVEN
        initialize();
        // WHEN
        userService.add(clientEntity);
        // THEN
        ClientEntity savedEntity = userService.findById(clientEntity.getId());
        assertNotNull(savedEntity);
        assertEquals(savedEntity, clientEntity);
        userService.delete(clientEntity);
    }

    @Test
    @DisplayName("should update")
    void update() {
        // GIVEN
        initialize();
        userService.add(clientEntity);
        clientEntity.setNom("Updated Name");
        clientEntity.setMail("default@gmail.com");
        // WHEN
        userService.update(clientEntity);
        ClientEntity updatedEntity = userService.findById(clientEntity.getId());
        // THEN
        assertNotNull(updatedEntity);
        assertNotEquals("default", updatedEntity.getNom());
        assertNotEquals("default",updatedEntity.getMail());
        assertEquals("Updated Name", updatedEntity.getNom());
        assertEquals("default@gmail.com", updatedEntity.getMail());
        userService.delete(clientEntity);
    }

    @Test
    @DisplayName("should delete")
    void delete() {
        // GIVEN
        initialize();
        userService.add(clientEntity);
        // WHEN
        int entityId = clientEntity.getId();
        userService.delete(clientEntity);
        // THEN
        assertNull(userService.findById(entityId));
    }

    @Test
    @DisplayName("should findAll")
    void findAll() {
        //GIVEN
        //WHEN
        List<ClientEntity> result = userService.findAll();
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    @DisplayName("should findAllByFilters")
    void findAllByFilters() {
        //GIVEN
        initialize();
        userService.add(clientEntity);
        UserDTO dto = new UserDTO();
        dto.setName(clientEntity.getNom());
        dto.setMail(clientEntity.getMail());
        //WHEN
        List<ClientEntity> result = userService.findAllByFilters(dto);
        //THEN
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        userService.delete(clientEntity);
    }

    @Test
    @DisplayName("should connect")
    void connect() {
        //GIVEN
        initialize();
        userService.add(clientEntity);
        //WHEN
        ClientEntity result = userService.connect("default", "default");
        //THEN
        assertNotNull(result);
        assertEquals(clientEntity, result);
        userService.delete(clientEntity);
    }
}