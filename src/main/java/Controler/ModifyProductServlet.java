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


@WebServlet(name = "modifyProductServlet", value = "/modifyProduct-servlet")
public class ModifyProductServlet extends HttpServlet{

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
            int id = VerifyData.getParameterAsInt(request, "list_produit_modif");
            article = articleService.findById(id);

            if(article == null){
                throw new ServletException();
            }

            //if all parameters are good
            String nom = request.getParameter("nom");
            int prix = VerifyData.getParameterAsInt(request, "prix");
            int stock = VerifyData.getParameterAsInt(request, "stock");
            String type = request.getParameter("type");
            String couleur = request.getParameter("couleur");
            String description = request.getParameter("description");
            String categorie = request.getParameter("categorie-modif");

            if (VerifyData.verifyParameters(nom, "testSake", type, couleur, description, categorie) && article != null) {

                article.setNom(nom);article.setPrix(prix);article.setStock(stock);article.setType(type);article.setCouleur(couleur);
                article.setDescription(description);article.setCategorie(categorie);

                articleService.update(article);
                entityManager.getTransaction().commit();
                request.getSession().removeAttribute("errModify");
                response.sendRedirect("modifyProduct-servlet");
            } else {
                request.getSession().setAttribute("errModify", "Veuillez correctement valider les champs");
                response.sendRedirect("modifyProduct-servlet");
            }
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
