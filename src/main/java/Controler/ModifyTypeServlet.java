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
import model.beans.ArticleEntity;
import model.service.ArticleService;

@WebServlet(name = "modifyTypeServlet", value = "/modifyType-servlet")
public class ModifyTypeServlet extends HttpServlet{
    private ArticleService<ArticleEntity> articleService;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String option = request.getParameter("option");
        String categorie = request.getParameter("categorie");
        List<ArticleEntity> data = null;

        switch(option){
            case "croissant":
                data = articleService.findAllByCategorieAndPriceAsc(categorie);
                break;
            case "decroissant" :
                data = articleService.findAllByCategorieAndPriceDesc(categorie);
                break;
            case "marque" :
                data = articleService.findAllByCategoryByBrand(categorie);
                break;
            case "default":
                data = articleService.findAllByCategorie(categorie);
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
