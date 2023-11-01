package Controler;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.Basket;
import model.service.ArticleService;
import model.service.BasketService;
import utils.ProcessBasketServlet;
import utils.VerifyData;


//CHANGER LES REDIRECTIONS DE REDIRECT SERVLET CA CAUSE PEUT ETRE LE BUG
@WebServlet(name = "basketControllerServlet", value = "/basketController-servlet")
public class BasketControllerServlet extends HttpServlet {

    private ArticleService<ArticleEntity> articleService;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("stockPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        articleService = new ArticleService<>(ArticleEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");

        BasketService basketService = ProcessBasketServlet.getBasketSession(request,response);

        String stat="";
        int stockProduct = 0;

        //Choix des actions
        switch (action){
            case "undo":
                //delete entire basket
                basketService.clearBasketService();
                stat = "ok";
                break;

            case "deleteRow":
                //delete a row from basket
                int id = VerifyData.getParameterAsInt(request, "id");
                basketService.delete(articleService.findById(id));
                stat = "ok";
                break;

            case "modify":
                //add or delete 1 from basket article
                String[] parts = request.getParameter("id").split("-");
                String toDo = parts[0];
                int idTrim = Integer.parseInt(parts[1]);

                ArticleEntity article = articleService.findById(idTrim);

                ArticleEntity articleInBasket = basketService.findById(idTrim);
                int currentQuantity = basketService.getBasket().getArticleQuantity(articleInBasket);

                //if plus add 1 to basket
                if(toDo.equals("plus")){
                    //verify if article is in basket
                    if(articleInBasket != null){
                        //verify if enough in stock
                        if (currentQuantity + 1 <= article.getStock()) {
                            basketService.add(articleInBasket);
                            stat = "plus-ok";
                            stockProduct = basketService.getBasket().getArticleQuantity(articleInBasket);
                        //else stop the button
                        } else {
                            stat = "plus-fail";
                        }
                    }
                }

                if(toDo.equals("minus")){
                    //verify if article is in basket
                    if(articleInBasket != null){
                        //check if quantity > 0
                        if(currentQuantity > 0){
                            stat = "minus-ok";
                            //delete 1 from basket, if quantity = 0, delete row automatically
                            basketService.deleteQuantity(articleInBasket,1);
                            //check if article is still in basket
                            if(basketService.findById(idTrim) == null){
                                    stat = "minus-unset";
                            }else {
                                stockProduct = basketService.getBasket().getArticleQuantity(articleInBasket);
                            }
                        }
                    }
                }
                break;
        }

        //Saving the new basket
        if(action.equals("undo") || VerifyData.isHashMapEmpty(basketService.getBasket().getPanier())){
            request.getSession().removeAttribute("basket");
            request.getSession().removeAttribute("hashmapBasket");
        } else {
            request.getSession().setAttribute("basket", basketService.getBasket());
            request.getSession().setAttribute("hashmapBasket", basketService.getBasket().getPanier());
        }

        //Parameters for the json response
        Gson gson = new Gson();
        JsonObject jsonResponse = new JsonObject();

        jsonResponse.addProperty("stat", stat);
        jsonResponse.addProperty("stock", stockProduct);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonResponse));

    }

    public void destroy() {
    }
}
