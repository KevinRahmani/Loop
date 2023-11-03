package Controler;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.ClientEntity;
import model.service.ArticleService;
import model.service.UserService;
import utils.CalculationUtils;
import utils.VerifyData;

@WebServlet(name = "searchBarControllerServlet", value = "/searchBarController-servlet")
public class SearchBarControllerServlet extends HttpServlet{

    private ArticleService<ArticleEntity> articleService;
    private EntityManager entityManager;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            if (entityManager == null || !entityManager.isOpen()) {
                init();
            }

            //Parameters
            String valueResearch = request.getParameter("task");
            String result = "";
            String status = "fail";

            if(valueResearch != null && !valueResearch.isEmpty()){
                //downcast the research to lower case for better efficiency
                valueResearch = valueResearch.toLowerCase();
                //search in the hashmap of categories
                result = searchCategories(valueResearch);

                //search for product if result is empty ie no categories
                if(result.isEmpty()){
                  result = searchProducts(valueResearch);
                }

                //if result not empty status = ok => found something
                if(!result.isEmpty()){
                    status = "ok";
                }
            } else {
                result = "Veuillez rentrer une recherche";
            }

            //Parameters to send for ajax response
            Gson gson = new Gson();
            JsonObject jsonResponse = new JsonObject();

            jsonResponse.addProperty("status", status);
            jsonResponse.addProperty("result", result);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonResponse));

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    private String searchCategories(String valueResearch){
        String result = "";
        //Hashmap with the name of categories and it's value
        HashMap<String, String> categories = getCategoriesHashMap();

        //search for categories
        for (Map.Entry<String,String> entry : categories.entrySet()) {
            double similarity = CalculationUtils.calculateSimilarity(entry.getKey(), valueResearch);
            if (similarity > 75.0) {
                result = "redirection-servlet?requestedPage=categorie&categorie="+entry.getValue();
            }
        }

        return result;
    }

    private String searchProducts(String valueResearch){
        String result = "";
        List<ArticleEntity> articleList = articleService.findAll();
        for (ArticleEntity article : articleList){
            double similarity = CalculationUtils.calculateSimilarity(article.getNom().toLowerCase(), valueResearch);
            if (similarity > 75.0) {
                result = "redirection-servlet?requestedPage=product&product="+article.getId();
            }
        }
        return result;
    }

    private static HashMap<String, String> getCategoriesHashMap() {
        HashMap<String,String> categories= new HashMap<>();
        categories.put("automobile", "automobile");
        categories.put("bricolage, jardin et animalerie", "bja");
        categories.put("cuisine et maison", "cm");
        categories.put("high-tech", "ht");
        categories.put("livre", "livre");
        categories.put("musique, dvd et blu-ray", "mdb");
        categories.put("sports", "sports");
        categories.put("vetements", "vetements");
        return categories;
    }
}
