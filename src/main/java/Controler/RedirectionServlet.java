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
import model.service.ArticleService;
import model.dto.ArticleDTO;
import model.service.BasketService;

@WebServlet(name = "redirectionServlet", value = "/redirection-servlet")
public class RedirectionServlet extends HttpServlet {
    private ArticleService<ArticleEntity> articleService;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

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

            default :
                getIndex(request,response);
                break;

        }

    }

    public void destroy() {
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
        BasketService basketService = new BasketService(request.getSession());
        request.setAttribute("basket", basketService.getBasket().getPanier());
        request.getRequestDispatcher("WEB-INF/basket.jsp").forward(request, response);
    }

    public static int getParameterAsInt(HttpServletRequest request, String param) {
        String value = request.getParameter(param);
        int result = 0;
        if (value != null) {
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



}