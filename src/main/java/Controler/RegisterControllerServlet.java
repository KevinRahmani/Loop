package Controler;

import java.io.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ClientEntity;
import model.service.UserService;
import utils.VerifyData;

@WebServlet(name = "registerControllerServlet", value = "/registerController-servlet")
public class RegisterControllerServlet extends HttpServlet {

    private UserService<ClientEntity> clientService;

    private EntityManager entityManager;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("userPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        clientService = new UserService<>(ClientEntity.class, entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (entityManager == null || !entityManager.isOpen()) {
                init();
            }

            //Parameters
            String nom = request.getParameter("nom");
            String mail = request.getParameter("mail");
            String address = request.getParameter("adresse");
            String password = request.getParameter("password");

            //if parameters not null and not empty
            if (VerifyData.verifyParameters(nom, mail, address, password, "testSake", "testSake")) {
                //if email not domain email from seller and admin
                if (!UserService.isAdminEmail(mail) && !UserService.isVendeurEmail(mail)) {
                    //if email valid
                    if(VerifyData.isValidMail(mail)){
                        //verify if mail is already taken
                        if(VerifyData.isFreeMailClient(mail, clientService, null)){
                            ClientEntity client = new ClientEntity();
                            client.setUp(nom, password, mail, address);
                            clientService.add(client);
                            entityManager.getTransaction().commit();

                            request.getSession().setAttribute("user", client);
                            request.getSession().setAttribute("type", "client");
                            //sending mail
                            MailControllerServlet.sendMail(request, response, "inscription", client);

                            response.sendRedirect("redirection-servlet");
                        } else {
                            request.setAttribute("errRegister", "Adresse mail déjà prise");
                            doGet(request, response);
                        }
                    } else {
                        request.setAttribute("errRegister", "Veuillez rentrer une adresse mail valide");
                        doGet(request, response);
                    }
                } else {
                    request.setAttribute("errRegister", "Veuillez choisir un nom de domaine différent");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("errRegister", "Champs manquants");
                doGet(request, response);
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
}
