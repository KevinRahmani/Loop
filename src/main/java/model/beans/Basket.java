package model.beans;


import java.util.HashMap;

public class Basket {
    public HashMap<ArticleEntity, Integer> basket;

    public Basket() {
        basket = new HashMap<>();
    }

    public HashMap<ArticleEntity, Integer> getPanier() {
        return basket;
    }

    public void setPanier(HashMap<ArticleEntity, Integer> basket) {
        this.basket = basket;
    }

    public int getArticleQuantity(ArticleEntity articleEntity) {
        return basket.getOrDefault(articleEntity, 0);
    }

    public void clearBasket() {
        basket.clear();
    }
}
