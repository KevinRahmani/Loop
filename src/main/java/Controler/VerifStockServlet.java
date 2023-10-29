package Controler;

import java.io.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.StockEntity;
import model.service.ArticleDAO;

@WebServlet(name = "verifStockServlet", value = "/verifStock-servlet")
public class VerifStockServlet extends HttpServlet {

    private ArticleDAO<StockEntity> articleDAO;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleDAO = new ArticleDAO<>(StockEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productId = request.getParameter("id");

        StockEntity product = articleDAO.findById(Integer.parseInt(productId));
        int stock = product.getStock();
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(stock));
    }

    public void destroy() {
    }
}
