package Controler;

import java.io.*;
import java.util.List;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.StockEntity;
import model.service.ArticleDAO;

@WebServlet(name = "modifTypeServlet", value = "/modifType-servlet")
public class ModifType extends HttpServlet{
    private ArticleDAO<StockEntity> articleDAO;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleDAO = new ArticleDAO<>(StockEntity.class, entityManager);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String option = request.getParameter("option");
        String categorie = request.getParameter("categorie");
        List<StockEntity> data = null;

        switch(option){
            case "croissant":
                data = articleDAO.findAllByCategorieAndPriceAsc(categorie);
                break;
            case "decroissant" :
                data = articleDAO.findAllByCategorieAndPriceDesc(categorie);
                break;
            case "marque" :
                data = articleDAO.findAllByCategoryByBrand(categorie);
                break;
            case "default":
                data = articleDAO.findAllByCategorie(categorie);
                break;
        }

        String jsonData = new Gson().toJson(data);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonData);
    }

    public void destroy() {
    }

}
