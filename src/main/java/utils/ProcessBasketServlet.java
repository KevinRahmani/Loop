package utils;
import jakarta.servlet.annotation.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import model.beans.Basket;

import model.service.BasketService;

import java.io.IOException;

@WebServlet(name = "processBasketServlet", value = "/processBasket-servlet")
public class ProcessBasketServlet extends HttpServlet {
    public static BasketService getBasketSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Basket basket = (Basket) request.getSession().getAttribute("basket");
        BasketService basketService = null;

        //client has already a basket
        if(basket != null){
            basketService = new BasketService(basket);
        } else {
            //client doesn't we create new Basket for him
            basketService = new BasketService(new Basket());
        }

        return basketService;
    }
}
