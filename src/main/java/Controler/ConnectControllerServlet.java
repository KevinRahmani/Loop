package Controler;
import java.io.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.AdminEntity;
import model.beans.ClientEntity;
import model.beans.VendeurEntity;
import model.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "connectControllerServlet", value = "/connectController-servlet")
public class ConnectControllerServlet extends HttpServlet{

    private UserService<ClientEntity> clientService;
    private UserService<VendeurEntity> vendeurService;
    private UserService<AdminEntity> adminService;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("userPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        clientService = new UserService<>(ClientEntity.class, entityManager);
        vendeurService = new UserService<>(VendeurEntity.class,entityManager);
        adminService = new UserService<>(AdminEntity.class,entityManager);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");

        String mail = request.getParameter("name");

        if(password != null && mail !=null && !password.isEmpty() && !mail.isEmpty()) {
            if (UserService.isVendeurEmail(mail)) {
                VendeurEntity vendeur = vendeurService.connect(mail, password);
                if (vendeur != null && BCrypt.checkpw(password, vendeur.getPassword())) {
                    request.getSession().setAttribute("user", vendeur);
                    request.getSession().setAttribute("type", "vendeur");
                    response.sendRedirect("redirection-servlet");
                } else {
                    request.setAttribute("err", 1);
                    doGet(request, response);
                }
            } else if (UserService.isAdminEmail(mail)) {
                AdminEntity admin = adminService.connect(mail, password);
                if (admin != null && BCrypt.checkpw(password, admin.getPassword())) {
                    request.getSession().setAttribute("user", admin);
                    request.getSession().setAttribute("type", "admin");
                    response.sendRedirect("redirection-servlet");
                } else {
                    request.setAttribute("err", 1);
                    doGet(request, response);
                }
            } else {
                ClientEntity client = clientService.connect(mail, password);
                if (client != null && BCrypt.checkpw(password, client.getPassword())) {
                    request.getSession().setAttribute("user", client);
                    request.getSession().setAttribute("type", "client");
                    response.sendRedirect("redirection-servlet");
                } else {
                    request.setAttribute("err", 1);
                    doGet(request, response);
                }
            }
        } else {
            request.setAttribute("err", 1);
            doGet(request, response);
        }
    }


}
