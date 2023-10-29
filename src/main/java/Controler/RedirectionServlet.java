package Controler;

import java.io.*;
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

        List<StockEntity> listCategorie= articleDAO.findAllByCategorie(requestedCategorie);

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
                StockEntity product = articleDAO.findById(productIdAsInt);

                //Set Attribute
                request.setAttribute("product", product);

                request.getRequestDispatcher("WEB-INF/product.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }


}