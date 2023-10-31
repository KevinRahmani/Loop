package Controler;

import java.io.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.service.ArticleService;

@WebServlet(name = "verifyStockServlet", value = "/verifyStock-servlet")
public class VerifyStockServlet extends HttpServlet {

    private ArticleService<ArticleEntity> articleService;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productId = request.getParameter("id");

        ArticleEntity product = articleService.findById(Integer.parseInt(productId));
        int stock = product.getStock();
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(stock));
    }

    public void destroy() {
    }
}
