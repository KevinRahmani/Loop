package Controler;

import java.io.*;
import java.sql.Timestamp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ClientEntity;
import model.service.UserService;

//TO DO : A CHAQUE FOIS QUE JE VEUX CHANGER/ AJOUTER QQCH DANS LA BDD JE DOIS : entityManager.getTransaction().begin(); ET LA CLOSE
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
        String nom = request.getParameter("nom");
        String mail = request.getParameter("mail");
        String address = request.getParameter("adresse");
        String password = request.getParameter("password");

        if(nom != null && mail !=null && address != null && password != null){
            if(!nom.isEmpty() && !mail.isEmpty() && !address.isEmpty() && !password.isEmpty()){
                if(!UserService.isAdminEmail(mail) && !UserService.isVendeurEmail(mail)){
                    ClientEntity client = new ClientEntity();
                    client.setUp(nom, password, mail, address);
                    clientService.add(client);
                    request.getSession().setAttribute("user", client);
                    request.getSession().setAttribute("type", "client");
                    MailControllerServlet.sendMail(request,response,"inscription", client);

                    response.sendRedirect("redirection-servlet");
                } else {
                    request.setAttribute("errRegister", "Veuillez choisir un nom de domaine différent");
                    doGet(request, response);
                }
            } else {
                request.setAttribute("errRegister", "Champs manquants");
                doGet(request, response);
            }
        } else {
            request.setAttribute("errRegister", "Veuillez remplir tous les champs");
            doGet(request, response);
        }

        entityManager.close();
    }
}
