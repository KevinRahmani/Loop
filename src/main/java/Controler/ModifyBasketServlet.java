package Controler;

import com.google.gson.Gson;
import jakarta.servlet.annotation.*;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import model.beans.ArticleEntity;
import model.service.ArticleService;
import model.service.BasketService;

import java.io.IOException;

@WebServlet(name = "modifyBasketServlet", value = "/modifyBasket-servlet")
public class ModifyBasketServlet extends HttpServlet {
    private ArticleService<ArticleEntity> articleService;

    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BasketService basketService = new BasketService(request.getSession());
        Gson gson = new Gson();
        JsonObject jsonResponse = new JsonObject();

        int id = RedirectionServlet.getParameterAsInt(request, "id");
        int quantity = RedirectionServlet.getParameterAsInt(request, "quantity");

        ArticleEntity article = articleService.findById(id);
        ArticleEntity articleInBasket = basketService.findById(id);

        String status = "ok";
        int stock = 0;

        if (article == null || quantity > article.getStock()) {
            stock = (article != null) ? article.getStock() : 0;
            status = "not-enough";
        } else {
            if (articleInBasket != null) {
                int currentQuantity = basketService.getBasket().getArticleQuantity(articleInBasket);
                if (currentQuantity + quantity <= article.getStock()) {
                    basketService.addQuantity(articleInBasket,quantity);
                } else {
                    stock = article.getStock() - currentQuantity;
                    status = "not-enough";
                }
            } else {
                basketService.addQuantity(article, quantity);
            }
        }

        jsonResponse.addProperty("status", status);
        jsonResponse.addProperty("stock", stock);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    public void destroy() {
    }


}
