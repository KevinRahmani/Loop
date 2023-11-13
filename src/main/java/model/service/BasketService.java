package model.service;

import model.beans.Basket;
import model.beans.ArticleEntity;
import model.dao.GenericDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BasketService implements GenericDAO<ArticleEntity> {

    private Basket basket;

    public BasketService(Basket basket){
        this.basket = basket;
    }

    @Override
    public ArticleEntity findById(int id) {
        for(ArticleEntity article : basket.getPanier().keySet()){
            if(article.getId() == id){
                return article;
            }
        }
        return null;
    }

    //add one article to basket
    @Override
    public void add(ArticleEntity entity) {
        if (basket.getPanier().containsKey(entity)) {
            int existingQuantity = basket.getPanier().get(entity);
            basket.getPanier().put(entity, existingQuantity + 1);
        } else {
            basket.getPanier().put(entity, 1);
        }
    }

    public void addQuantity(ArticleEntity article, int quantity) {
        if (basket.getPanier().containsKey(article)) {
            int currentQuantity = basket.getArticleQuantity(article);
            basket.getPanier().put(article, (currentQuantity+quantity));
        } else {
            basket.getPanier().put(article, quantity);
        }
    }

    //not implemented
    @Override
    public void update(ArticleEntity entity) {
        return;
    }

    //delete entire row
    @Override
    public void delete(ArticleEntity entity) {
        basket.getPanier().remove(entity);
    }
    public void deleteQuantity(ArticleEntity article, int quantity){
        if (basket.getPanier().containsKey(article)) {
            int currentQuantity = basket.getArticleQuantity(article);
            if (currentQuantity > quantity) {
                basket.getPanier().put(article, currentQuantity - quantity);
            } else {
                basket.getPanier().remove(article);
            }
        }
    }

    @Override
    public List<ArticleEntity> findAll() {
        return new ArrayList<>(basket.getPanier().keySet());
    }

    public void clearBasketService() {
        basket.clearBasket();
    }

    public Basket getBasket()
    {
        return basket;
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (HashMap.Entry<ArticleEntity, Integer> entry : basket.getPanier().entrySet()) {
            ArticleEntity article = entry.getKey();
            int quantity = entry.getValue();
            double unitPrice = article.getPrix();
            double articleTotalPrice = unitPrice * quantity;
            totalPrice += articleTotalPrice;
        }

        return totalPrice;
    }

    public double getTotalPriceHT() {
        double totalPrice = 0;

        for (HashMap.Entry<ArticleEntity, Integer> entry : basket.getPanier().entrySet()) {
            ArticleEntity article = entry.getKey();
            int quantity = entry.getValue();
            double unitPrice = article.getPrix() * 0.8;
            double articleTotalPrice = unitPrice * quantity;
            totalPrice += articleTotalPrice;
        }

        return totalPrice;
    }
}
