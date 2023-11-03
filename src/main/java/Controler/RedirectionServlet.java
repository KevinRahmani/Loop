package Controler;

import java.io.*;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.Basket;
import model.beans.ClientEntity;
import model.service.ArticleService;
import model.dto.ArticleDTO;
import model.service.BasketService;
import utils.ProcessBasketServlet;

@WebServlet(name = "redirectionServlet", value = "/redirection-servlet")
public class RedirectionServlet extends HttpServlet {
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

            String requestedPage = request.getParameter("requestedPage");
            if (requestedPage == null || requestedPage.isEmpty()) {
                requestedPage = "index";
            }

            switch (requestedPage){

                case "index" :
                    getIndex(request, response);
                    break;

                case "categorie" :
                    getCategorie(request,response);
                    break;

                case "product" :
                    getProduct(request, response);
                    break;

                case "basket" :
                    getBasket(request, response);
                    break;

                case "invoice":
                    getInvoice(request,response);
                    break;

                case "about_us":
                    request.getRequestDispatcher("WEB-INF/about_us.jsp").forward(request, response);
                    break;

                case "cgv":
                    request.getRequestDispatcher("WEB-INF/cgv.jsp").forward(request, response);
                    break;

                case "collaborator":
                    request.getRequestDispatcher("WEB-INF/collaborator.jsp").forward(request, response);
                    break;
            }

        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void getIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        //Smartphone Carousel List
        ArticleDTO dto = new ArticleDTO();
        dto.setCategorie("ht");
        dto.setMarque("Apple");
        dto.setType("Téléphones");
        List<ArticleEntity> smartphoneList = articleService.findAllByFilters(dto);

        //Watch Carousel List
        List<ArticleEntity> watchList = articleService.findAllByType("Montres connectées");

        //Set Attribute
        request.setAttribute("smartphoneList", smartphoneList);
        request.setAttribute("watchList", watchList);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public void getCategorie(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String requestedCategorie = request.getParameter("categorie");

        List<ArticleEntity> listCategorie= articleService.findAllByCategorie(requestedCategorie);

        //Set Attribute
        request.setAttribute("listCategorie", listCategorie);
        request.setAttribute("categorie", requestedCategorie);

        request.getRequestDispatcher("WEB-INF/categorie.jsp").forward(request, response);
    }

    public void getProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productId = request.getParameter("product");
        int productIdAsInt = 0;
        if(productId != null && !productId.isEmpty()) {
            try {
                productIdAsInt = Integer.parseInt(productId);
                ArticleEntity product = articleService.findById(productIdAsInt);

                //Set Attribute
                request.setAttribute("product", product);

                request.getRequestDispatcher("WEB-INF/product.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void getBasket(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/basket.jsp").forward(request, response);
    }


    public void getInvoice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userType = (String) request.getSession().getAttribute("type");

        if(userType == null || userType.equals("admin") || userType.equals("vendeur")){
            request.setAttribute("errAccess", "Veuillez vous connecter avec un compte client pour passer commande !");
            getBasket(request,response);

        } else if(userType.equals("client")){
            ClientEntity client = (ClientEntity) request.getSession().getAttribute("user");
            BasketService basketService = ProcessBasketServlet.getBasketSession(request,response);

            double totalPriceHT = basketService.getTotalPriceHT();
            double totalPriceTTC = basketService.getTotalPrice();

            //necessary point to get reduction
            if(client.getFidelity() > 500){
                request.setAttribute("fidelity", true);
            }

            request.setAttribute("totalPriceHT", totalPriceHT);
            request.setAttribute("totalPriceTTC", totalPriceTTC);
            request.getRequestDispatcher("WEB-INF/invoice.jsp").forward(request, response);
        }
    }
}