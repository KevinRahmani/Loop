package model.service;

import jakarta.persistence.Persistence;
import jakarta.servlet.http.HttpSession;
import model.beans.ArticleEntity;
import model.beans.Basket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.lang.model.element.QualifiedNameable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketServiceTest {

    private HttpSession session;
    private BasketService basketService;
    private Basket basket;
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

        basket = new Basket();
    }

    @BeforeEach
    public void setUp() {
        session = Mockito.mock(HttpSession.class);
        this.basketService = new BasketService(session);
    }

    @AfterEach
    public void tearDown() {
        if (session != null) {
            session.invalidate();
        }
    }
    @Test
    void findById() {
        //GIVEN
        initialize();
        //WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.add(stockEntity);
        //THEN
        ArticleEntity foundArticle = basketService.findById(stockEntity.getId());
        assertEquals(stockEntity, foundArticle);
    }

    @Test
    void add() {
        // GIVEN
        initialize();
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.add(stockEntity);
        // THEN
        ArticleEntity addedArticle = basket.getPanier().keySet().iterator().next();
        int quantity = basket.getPanier().get(addedArticle);
        assertEquals(stockEntity, addedArticle);
        assertEquals(1, quantity);
    }


    @Test
    void addQuantity() {
        // GIVEN
        initialize();
        int quantity = 5;
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.addQuantity(stockEntity,quantity);
        // THEN
        ArticleEntity addedArticle = basket.getPanier().keySet().iterator().next();
        int actualQuantity = basket.getPanier().get(addedArticle);
        assertEquals(stockEntity, addedArticle);
        assertEquals(quantity, actualQuantity);
    }

    @Test
    void delete() {
        // GIVEN
        initialize();
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.add(stockEntity);
        basketService.delete(stockEntity);
        // THEN
        assertTrue(basket.getPanier().isEmpty());
    }

    @Test
    void deleteQuantity() {
        // GIVEN
        initialize();
        int initialQuantity = 5;
        int quantityToRemove = 3;
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.addQuantity(stockEntity, initialQuantity);
        basketService.deleteQuantity(stockEntity, quantityToRemove);
        // THEN
        int expectedQuantity = initialQuantity - quantityToRemove;
        int quantity = basket.getPanier().get(stockEntity);
        assertEquals(expectedQuantity, quantity);
    }

    @Test
    void findAll() {
        // GIVEN
        initialize();
        ArticleEntity secondStockEntity = new ArticleEntity();
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.addQuantity(stockEntity,2);
        basketService.addQuantity(secondStockEntity,2);
        List<ArticleEntity> articleList = basketService.findAll();
        // THEN
        assertEquals(2, articleList.size());
        assertTrue(articleList.contains(stockEntity));
        assertTrue(articleList.contains(secondStockEntity));
    }

    @Test
    void testGetTotalPrice() {
        // GIVEN
        initialize();
        ArticleEntity firstArticle = new ArticleEntity();
        firstArticle.setPrix(10);
        ArticleEntity secondArticle = new ArticleEntity();
        secondArticle.setPrix(20);
        // WHEN
        Mockito.when(session.getAttribute("basket")).thenReturn(basket);
        basketService.addQuantity(firstArticle, 2);
        basketService.addQuantity(secondArticle, 3);
        // THEN
        double expectedTotalPrice = (10 * 2) + (20 * 3);
        double actualTotalPrice = basketService.getTotalPrice();
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

}