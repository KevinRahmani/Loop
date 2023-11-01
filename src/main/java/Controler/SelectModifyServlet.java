package Controler;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.service.ArticleService;
import utils.VerifyData;

import java.io.IOException;

import java.security.InvalidParameterException;

@WebServlet(name = "selectModifyServlet", value = "/selectModify-servlet")
public class SelectModifyServlet extends HttpServlet{

    private ArticleService<ArticleEntity> articleService;
    private ArticleEntity article;
    EntityManager entityManager;

    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = VerifyData.getParameterAsInt(request, "id");
        article = articleService.findById(id);

        if(article == null){
            throw new InvalidParameterException();
        }

        Gson gson = new Gson();
        JsonObject jsonResponse = getJsonObject();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResponse));
    }

    private JsonObject getJsonObject() {
        JsonObject jsonResponse = new JsonObject();

        jsonResponse.addProperty("nom", article.getNom());
        jsonResponse.addProperty("prix", article.getPrix());
        jsonResponse.addProperty("stock", article.getStock());
        jsonResponse.addProperty("couleur", article.getCouleur());
        jsonResponse.addProperty("type", article.getType());
        jsonResponse.addProperty("categorie", article.getCategorie());
        jsonResponse.addProperty("description", article.getDescription());
        return jsonResponse;
    }
}
