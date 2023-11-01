package Controler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.service.ArticleService;
import utils.ProcessTypeUserServlet;
import utils.VerifyData;

import java.io.IOException;


@WebServlet(name = "deleteProductServlet", value = "/deleteProduct-servlet")
public class DeleteProductServlet extends HttpServlet {
    private ArticleService<ArticleEntity> articleService;
    private ArticleEntity article;
    EntityManager entityManager;
    private ProcessTypeUserServlet userTypeProcessor;

    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
        userTypeProcessor = new ProcessTypeUserServlet();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userTypeProcessor.processUserType(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (entityManager == null || !entityManager.isOpen()) {
                init();
            }
            //find Article to update
            int id = VerifyData.getParameterAsInt(request, "list_produit_supp");
            article = articleService.findById(id);

            if(article == null){
                throw new ServletException();
            }

            articleService.delete(article);
            entityManager.getTransaction().commit();
            response.sendRedirect("deleteProduct-servlet");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }


}
