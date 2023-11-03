package Controler;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.Popular;
import model.service.ArticleService;

@WebServlet(name = "popularProductServlet", value = "/popularProduct-servlet")
public class PopularProductServlet extends HttpServlet{
    private ArticleService<ArticleEntity> articleService;
    private EntityManager entityManager;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (entityManager == null || !entityManager.isOpen()) {
            init();
        }
        
        //Parameters to send
        List<Popular> popularList = new ArrayList<>(); //Article is the article with the best sales and String is just a plain citation
        
        try {
            //auto
            popularList.add(new Popular("Automobile" , "Roulez dans nos meilleures routières", articleService.findByCategorieAndMaxSales("automobile")));

            //Bja
            popularList.add(new Popular("Bricolage, jardin et Maison" , "Bricolage et jardin ? Amusez-vous bien !", articleService.findByCategorieAndMaxSales("bja")));

            //cm
            popularList.add(new Popular("Cuisine et Maison" , "Pour des femmes heureuses !", articleService.findByCategorieAndMaxSales("cm")));

            //ht
            popularList.add(new Popular("High-Tech" , "Pour des Hommes heureux", articleService.findByCategorieAndMaxSales("ht")));

            //livre
            popularList.add(new Popular("livre" , "Une envie de voyager ?", articleService.findByCategorieAndMaxSales("livre")));

            //mdb
            popularList.add(new Popular("Musique, DVD et Blu-ray" , "Un plaisir pour les yeux et les oreilles !", articleService.findByCategorieAndMaxSales("mdb")));

            //sports
            popularList.add(new Popular("Sports" ,  "Devenez la meilleure version de vous-même !", articleService.findByCategorieAndMaxSales("sports")));

            //vetements
            popularList.add(new Popular("Vetements" , "Des beaux habits pour une belle journée", articleService.findByCategorieAndMaxSales("vetements")));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        request.setAttribute("popularList", popularList);
        request.getRequestDispatcher("WEB-INF/popularProduct.jsp").forward(request,response);
    }
}
