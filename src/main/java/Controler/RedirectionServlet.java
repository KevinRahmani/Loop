package Controler;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.StockEntity;
import model.service.ArticleDAO;
import model.service.ArticleDTO;

@WebServlet(name = "redirectionServlet", value = "/redirection-servlet")
public class RedirectionServlet extends HttpServlet {
    private ArticleDAO<StockEntity> articleDAO;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleDAO = new ArticleDAO<>(StockEntity.class, entityManager);
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

            case "popular" :
                getPopular(request,response);
                break;

            case "about_us" :
                request.getRequestDispatcher("WEB-INF/about_us.jsp").forward(request, response);
                break;

            case "collaborator" :
                request.getRequestDispatcher("WEB-INF/collaborator.jsp").forward(request, response);
                break;

            case "cgv" :
                request.getRequestDispatcher("WEB-INF/cgv.jsp").forward(request, response);
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
        List<StockEntity> smartphoneList = articleDAO.findAllByFilters(dto);

        //Watch Carousel List
        List<StockEntity> watchList = articleDAO.findAllByType("Montres connectées");

        //Set Attribute
        request.setAttribute("smartphoneList", smartphoneList);
        request.setAttribute("watchList", watchList);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    public void getCategorie(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String requestedCategorie = request.getParameter("categorie");
        String categorieDisplay;

        switch(requestedCategorie){
            case "ht" :
                categorieDisplay = "High-Tech";
                break;
            case "automobile" :
                categorieDisplay = "Automobiles";
                break;
            case "bja" :
                categorieDisplay = "Bricolage & co";
                break;
            case "mdb":
                categorieDisplay = "Musique, DVD et Blu-ray";
                break;
            case "livre" :
                categorieDisplay = "Livres";
                break;
            case "cm" :
                categorieDisplay = "Cuisine et Maison";
                break;
            case "sports" :
                categorieDisplay = "Sports et Loisirs";
                break;
            case "vetements" :
                categorieDisplay = "Vetements";
                break;
            default :
                categorieDisplay = "";
                break;
        }

        List<StockEntity> listCategorie= articleDAO.findAllByCategorie(requestedCategorie);

        //Set Attribute
        request.setAttribute("listCategorie", listCategorie);
        request.setAttribute("categorie", requestedCategorie);
        request.setAttribute("categorieDisplay", categorieDisplay);

        request.getRequestDispatcher("WEB-INF/categorie.jsp").forward(request, response);
    }

    public void getProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productId = request.getParameter("product");
        int productIdAsInt = 0;
        if(productId != null && !productId.isEmpty()) {
            try {
                productIdAsInt = Integer.parseInt(productId);
                StockEntity product = articleDAO.findById(productIdAsInt);

                //Set Attribute
                request.setAttribute("product", product);

                request.getRequestDispatcher("WEB-INF/product.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void getPopular(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> listCategory = Arrays.asList("automobile", "bja", "cm", "ht", "livre", "mdb", "sports", "vetements");
        List<StockEntity> listPopular = new ArrayList<StockEntity>();

        for (String c:listCategory){
            listPopular.add(articleDAO.findByCategoryMaxSales(c));
        }

        request.setAttribute("listPopular", listPopular);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("WEB-INF/popular.jsp").forward(request, response);

    }

    public void getAboutUs(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.getRequestDispatcher("WEB-INF/popular.jsp").forward(request, response);

    }


}