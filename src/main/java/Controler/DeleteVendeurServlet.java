package Controler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.VendeurEntity;
import model.service.UserService;
import utils.ProcessTypeUserServlet;
import utils.VerifyData;

import java.io.IOException;


@WebServlet(name = "deleteVendeurServlet", value = "/deleteVendeur-servlet")
public class DeleteVendeurServlet extends HttpServlet{
    EntityManager entityManager;
    UserService<VendeurEntity> userService;
    private ProcessTypeUserServlet userTypeProcessor;

    public void init(){
        EntityManagerFactory entityManagerFactoryUser = Persistence.createEntityManagerFactory("userPersistence");

        entityManager = entityManagerFactoryUser.createEntityManager();
        entityManager.getTransaction().begin();

        userService = new UserService<>(VendeurEntity.class, entityManager);

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
            //Parameters
            int idVendeur = VerifyData.getParameterAsInt(request,"idVendeur");

            if(idVendeur != 0){

                VendeurEntity vendeur = userService.findById(idVendeur);

                userService.delete(vendeur);
                entityManager.getTransaction().commit();

                response.sendRedirect("deleteVendeur-servlet");
            } else {
                request.setAttribute("errDeleteVendeur", "Veuillez remplir correctement les champs");
                doGet(request, response);
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
