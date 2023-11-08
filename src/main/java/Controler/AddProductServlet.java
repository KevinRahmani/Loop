package Controler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.AdminEntity;
import model.beans.ArticleEntity;
import model.beans.VendeurEntity;
import model.service.ArticleService;
import utils.ProcessTypeUserServlet;
import utils.VerifyData;

import java.io.IOException;

@WebServlet(name = "addProductServlet", value = "/addProduct-servlet")
public class AddProductServlet extends HttpServlet {
    private ArticleService<ArticleEntity> articleService;
    private ArticleEntity article;
    EntityManager entityManager;
    String typeUser;
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

            typeUser = (String) request.getSession().getAttribute("type");
            //Parameters
            String nom = request.getParameter("nom");
            String marque = request.getParameter("marque");
            int prix = VerifyData.getParameterAsInt(request, "prix");
            int stock = VerifyData.getParameterAsInt(request, "stock");
            String type = request.getParameter("type");
            String couleur = request.getParameter("couleur");
            String description = request.getParameter("description");
            String categorie = request.getParameter("categorie-add");

            if(VerifyData.verifyParameters(nom,marque,type,couleur,description,categorie)) {
                article = new ArticleEntity();
                String pathImg = "img/";
                if(typeUser.equals("vendeur")){
                    VendeurEntity vendeur = (VendeurEntity) request.getSession().getAttribute("user");
                    article.setUp(nom, marque, prix, vendeur.getNom(), stock, type, couleur, description, 0, pathImg,categorie);
                } else if(typeUser.equals("admin")){
                    AdminEntity admin = (AdminEntity) request.getSession().getAttribute("user");
                    article.setUp(nom, marque, prix, admin.getNom(), stock, type, couleur, description, 0, pathImg,categorie);
                }
                articleService.add(article);
                entityManager.getTransaction().commit();
                request.getSession().removeAttribute("errModify");
                response.sendRedirect("addProduct-servlet");
            } else {
                request.setAttribute("errAdd", "Veuillez remplir correctement les champs");
                doGet(request,response);
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
