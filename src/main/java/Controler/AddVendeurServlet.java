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

import java.sql.Timestamp;


@WebServlet(name = "addVendeurServlet", value = "/addVendeur-servlet")
public class AddVendeurServlet extends HttpServlet{
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
            String nom = request.getParameter("nom");
            String password = request.getParameter("password");
            String adresse = request.getParameter("adresse");
            //must end with @loop.com
            String mail = request.getParameter("mail");

            if (VerifyData.verifyParameters(nom, password, adresse, mail, "testSake", "testSake")) {
                if(mail.endsWith("@loop.com")){
                    VendeurEntity vendeur = new VendeurEntity();

                    vendeur.setNom(nom);vendeur.setPassword(password);vendeur.setMail(mail);
                    vendeur.setAdresse(adresse);vendeur.setDatesignup(new Timestamp(System.currentTimeMillis()));
                    vendeur.setNbtotalsales(0);vendeur.setAddRight(1);vendeur.setModifyRight(1);vendeur.setRemoveRight(1);

                    userService.add(vendeur);
                    entityManager.getTransaction().commit();

                    response.sendRedirect("addVendeur-servlet");
                } else {
                    request.setAttribute("errAddVendeur", "Veuillez mettre le nom de domaine à @loop.com");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("errAddVendeur", "Veuillez remplir correctement les champs");
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
