package model.service;

import jakarta.servlet.http.HttpSession;
import model.beans.Basket;
import model.beans.ArticleEntity;
import model.dao.GenericDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketService implements GenericDAO<ArticleEntity> {

    private Basket basket;
    private final HttpSession session;

    public BasketService(HttpSession session){
        this.session = session;
    }


    @Override
    public ArticleEntity findById(int id) {
        load();
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
        load();
        if (basket.getPanier().containsKey(entity)) {
            int existingQuantity = basket.getPanier().get(entity);
            basket.getPanier().put(entity, existingQuantity + 1);
        } else {
            basket.getPanier().put(entity, 1);
        }
        save();
    }

    public void addQuantity(ArticleEntity article, int quantity) {
        load();
        if (basket.getPanier().containsKey(article)) {
            int currentQuantity = basket.getArticleQuantity(article);
            basket.getPanier().put(article, (currentQuantity+quantity));
        } else {
            basket.getPanier().put(article, quantity);
        }
        save();
    }

    //not implemented
    @Override
    public void update(ArticleEntity entity) {
        return;
    }

    //delete entire row
    @Override
    public void delete(ArticleEntity entity) {
        load();
        basket.getPanier().remove(entity);
        save();
    }
    public void deleteQuantity(ArticleEntity article, int quantity){
       load();
        if (basket.getPanier().containsKey(article)) {
            int currentQuantity = basket.getArticleQuantity(article);
            if (currentQuantity > quantity) {
                basket.getPanier().put(article, currentQuantity - quantity);
            } else {
                basket.getPanier().remove(article);
            }
            save();
        }
    }

    @Override
    public List<ArticleEntity> findAll() {
        load();
        return new ArrayList<>(basket.getPanier().keySet());
    }

    public void clearBasketService() {
        load();
        basket.clearBasket();
        save();
    }

    private void load()
    {
        basket = (Basket) session.getAttribute("basket");
        System.out.println(basket == null);
        if (basket == null ){
            basket = new Basket();
        }
    }

    private void save()
    {
        session.setAttribute("basket", basket);
    }

    public Basket getBasket()
    {
        load();
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



}
