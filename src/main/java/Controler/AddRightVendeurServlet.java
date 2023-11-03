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


@WebServlet(name = "addRightVendeurServlet", value = "/addRightVendeur-servlet")
public class AddRightVendeurServlet extends HttpServlet{

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
            int idVendeur = VerifyData.getParameterAsInt(request,"vendeur");
            String right = request.getParameter("right");

            if(idVendeur != 0 && right != null && !right.isEmpty()){

                VendeurEntity vendeur = userService.findById(idVendeur);
                switch (right){
                    case "addRight":
                        vendeur.setAddRight(1);
                        break;
                    case "modifyRight":
                        vendeur.setModifyRight(1);
                        break;
                    case "deleteRight":
                        vendeur.setRemoveRight(1);
                        break;
                }
                userService.update(vendeur);
                entityManager.getTransaction().commit();

                response.sendRedirect("addRightVendeur-servlet");
            } else {
                request.setAttribute("errAddRight", "Erreur");
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
