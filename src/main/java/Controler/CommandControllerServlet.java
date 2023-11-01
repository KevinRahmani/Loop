package Controler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.*;
import model.dto.UserDTO;
import model.service.ArticleService;
import model.service.BasketService;
import model.service.UserService;
import utils.CalculationUtils;
import utils.ProcessBasketServlet;
import utils.VerifyData;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@WebServlet(name = "commandControllerServlet", value = "/commandController-servlet")
public class CommandControllerServlet extends HttpServlet{
    private ArticleService<ArticleEntity> articleService;
    EntityManager entityManagerStock;
    EntityManager entityManagerUser;
    UserService<ClientEntity> clientService;
    UserService<VendeurEntity> vendeurService;
    UserService<AdminEntity> adminService;
    String status;

    public void init() {
        //stock
        EntityManagerFactory entityManagerFactoryStock = Persistence.createEntityManagerFactory("stockPersistence");
        entityManagerStock = entityManagerFactoryStock.createEntityManager();
        entityManagerStock.getTransaction().begin();
        articleService = new ArticleService<>(ArticleEntity.class, entityManagerStock);

        //user
        EntityManagerFactory entityManagerFactoryUser = Persistence.createEntityManagerFactory("userPersistence");
        entityManagerUser = entityManagerFactoryUser.createEntityManager();
        entityManagerUser.getTransaction().begin();

        //client
        clientService = new UserService<>(ClientEntity.class, entityManagerUser);

        //vendeur
        vendeurService = new UserService<>(VendeurEntity.class, entityManagerUser);

        //admin
        adminService = new UserService<>(AdminEntity.class, entityManagerUser);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            if (entityManagerStock == null || !entityManagerStock.isOpen() || entityManagerUser == null ||!entityManagerUser.isOpen()) {
                init();
            }
            status = "ok";

            //Parameters
            ClientEntity client = (ClientEntity) request.getSession().getAttribute("user");
            BasketService basketService = ProcessBasketServlet.getBasketSession(request,response);
            String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("dd:MM:yyyy"));
            double totalPriceTTC = basketService.getTotalPrice();

            //remove quantity from stock and add sales quantity
            for(Map.Entry<ArticleEntity, Integer> entry : basketService.getBasket().getPanier().entrySet()){
                //remove stock quantity part
                ArticleEntity article = articleService.findById(entry.getKey().getId());
                //check if article was found and stock > quantityRequested
                if(article != null && article.getStock() >= entry.getValue()){
                    //article part
                    article.setStock(article.getStock()-entry.getValue()); // stock article = previous stock - quantityRequested
                    article.setSales(article.getSales()+entry.getValue()); //sales article = previous sales + quantityRequested
                    articleService.update(article);

                    //client Part
                    client.setNbproduct(client.getNbproduct()+ entry.getValue()); //nbProduct of client = previous + quantityRequested
                    client.setHistocommand(client.getHistocommand() + "<br><br>"+dateStr + " : "+article.getMarque()+article.getNom()+"<br>Quantite : "+entry.getValue());
                    clientService.update(client);

                    //vendeur or Admin part
                    if(article.getVendeur().equals("Loop")){
                        AdminEntity admin = adminService.findById(19042003); //find admin by ID
                        admin.setNbtotalsales(admin.getNbtotalsales() + (entry.getValue() * article.getPrix())); //add the value of the line command : price of article * quantity
                        adminService.update(admin);
                    } else {
                        UserDTO user = new UserDTO();user.setName(article.getVendeur()); //same here retrieve seller with dto
                        VendeurEntity vendeur = vendeurService.findAllByFilters(user).get(0);
                        vendeur.setNbtotalsales(vendeur.getNbtotalsales()+(entry.getValue())*article.getPrix()); //same
                        vendeurService.update(vendeur);
                    }
                }
            }

            client.setFidelity(client.getFidelity()+CalculationUtils.calculateFidelityPoint(totalPriceTTC));
            if(client.getFidelity() > 500){
                totalPriceTTC *= 0.95; // 5% retrieved
                client.setFidelity(client.getFidelity()-500);
                clientService.update(client);
            }

            entityManagerUser.getTransaction().commit();
            entityManagerStock.getTransaction().commit();

            response.setContentType("text/plain");
            response.getWriter().write(status);
        } catch (Exception e) {
            if (entityManagerStock.getTransaction().isActive() || entityManagerUser.getTransaction().isActive() ) {
                entityManagerStock.getTransaction().rollback();
                entityManagerUser.getTransaction().rollback();
            }
            status = "Error while attempting";
            e.printStackTrace();
        } finally {
            entityManagerUser.close();
            entityManagerStock.close();
        }
    }
}
