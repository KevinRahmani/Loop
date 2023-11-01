package utils;
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
import model.service.UserService;

import java.io.IOException;

import java.util.List;

import static utils.CalculationUtils.calculRevenue;

@WebServlet(name = "processTypeUser", value = "/processTypeUser-servlet")
public class ProcessTypeUserServlet extends HttpServlet {

    private ArticleService<ArticleEntity> articleService;
    EntityManager entityManagerStock;
    EntityManager entityManagerUser;
    UserService<VendeurEntity> userService;
    String typeUser;

    public void initStock() {
        EntityManagerFactory entityManagerFactoryStock = Persistence.createEntityManagerFactory("stockPersistence");

        entityManagerStock = entityManagerFactoryStock.createEntityManager();
        entityManagerStock.getTransaction().begin();

        articleService = new ArticleService<>(ArticleEntity.class, entityManagerStock);
    }

    public void initUser(){
        EntityManagerFactory entityManagerFactoryUser = Persistence.createEntityManagerFactory("userPersistence");

        entityManagerUser = entityManagerFactoryUser.createEntityManager();
        entityManagerUser.getTransaction().begin();

        userService = new UserService<>(VendeurEntity.class, entityManagerUser);
    }
    public void processUserType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        typeUser = (String) request.getSession().getAttribute("type");
        if (entityManagerStock == null || !entityManagerStock.isOpen()) {
            initStock();
        }

        switch (typeUser) {
            case "admin": {

                if (entityManagerUser == null || !entityManagerUser.isOpen()) {
                    initUser();
                }
                AdminEntity admin = (AdminEntity) request.getSession().getAttribute("user");

                //List of all product to modify, list of all vendeur to send, list of all Loop'sproduct
                List<VendeurEntity> listVendeur = userService.findAll();
                List<ArticleEntity> listArticle = articleService.findAll();
                List<ArticleEntity> listArticleLoop = articleService.findAllByVendeur(admin.getNom());
                double revenue = calculRevenue(listArticleLoop);

                //setAttribute
                request.setAttribute("revenue", revenue);
                request.setAttribute("listVendeur", listVendeur);
                request.setAttribute("listArticle", listArticle);
                request.setAttribute("listArticleLoop", listArticleLoop);

                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                break;
            }
            case "vendeur": {

                VendeurEntity vendeur = (VendeurEntity) request.getSession().getAttribute("user");

                //data to transfer for product of seller
                List<ArticleEntity> listVendeur = articleService.findAllByVendeur(vendeur.getNom());
                double revenue = calculRevenue(listVendeur);

                //setAttribute
                request.setAttribute("revenue", revenue);
                request.setAttribute("listVendeur", listVendeur);

                request.getRequestDispatcher("WEB-INF/vendeur.jsp").forward(request, response);
                break;
            }
            case ("client"):
                request.getRequestDispatcher("WEB-INF/client.jsp").forward(request, response);
                break;
        }
    }

}
