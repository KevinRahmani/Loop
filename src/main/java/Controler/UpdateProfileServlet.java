package Controler;


import java.io.*;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.AdminEntity;
import model.beans.ArticleEntity;
import model.beans.ClientEntity;
import model.beans.VendeurEntity;
import model.service.ArticleService;
import model.service.UserService;
import utils.ProcessTypeUserServlet;
import utils.VerifyData;

@WebServlet(name = "updateProfileServlet", value = "/updateProfile-servlet")
public class UpdateProfileServlet extends HttpServlet{
    private UserService<ClientEntity> clientService;
    private UserService<VendeurEntity> vendeurService;
    private UserService<AdminEntity> adminService;
    EntityManager entityManagerUser;
    private ProcessTypeUserServlet userTypeProcessor;

    public void init() {
        EntityManagerFactory entityManagerFactoryUser = Persistence.createEntityManagerFactory("userPersistence");

        entityManagerUser = entityManagerFactoryUser.createEntityManager();

        entityManagerUser.getTransaction().begin();

        clientService = new UserService<>(ClientEntity.class, entityManagerUser);
        vendeurService = new UserService<>(VendeurEntity.class, entityManagerUser);
        adminService = new UserService<>(AdminEntity.class, entityManagerUser);

        userTypeProcessor = new ProcessTypeUserServlet();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userTypeProcessor.processUserType(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if (entityManagerUser == null || !entityManagerUser.isOpen()) {
                init();
            }
            String password = request.getParameter("password");
            String mail = request.getParameter("mail");
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String typeUser = (String) request.getSession().getAttribute("type");

            if(password != null && mail !=null && nom != null && adresse !=null
                    && !password.isEmpty() && !mail.isEmpty() && !nom.isEmpty() && !adresse.isEmpty()) {
                    if(typeUser.equals("admin")){
                        AdminEntity admin = (AdminEntity) request.getSession().getAttribute("user");
                        admin.setNom(nom);admin.setPassword(password);admin.setMail(mail);admin.setAdresse(adresse);
                        adminService.update(admin);
                        entityManagerUser.getTransaction().commit();
                        request.getSession().setAttribute("user", admin);
                    } else if(typeUser.equals("vendeur")){
                        VendeurEntity vendeur = (VendeurEntity) request.getSession().getAttribute("user");
                        vendeur.setNom(nom);vendeur.setPassword(password);vendeur.setMail(mail);vendeur.setAdresse(adresse);
                        vendeurService.update(vendeur);
                        entityManagerUser.getTransaction().commit();
                        request.getSession().setAttribute("user", vendeur);
                    } else {
                        ClientEntity client = (ClientEntity) request.getSession().getAttribute("user");
                        client.setNom(nom);client.setPassword(password);client.setMail(mail);client.setAdresse(adresse);
                        clientService.update(client);
                        entityManagerUser.getTransaction().commit();
                        request.getSession().setAttribute("user", client);
                    }
                    response.sendRedirect("updateProfile-servlet");
            } else {
                request.setAttribute("err", "Veuillez remplir tous les champs");
                doGet(request, response);
            }
        }catch (Exception e) {
            if (entityManagerUser.getTransaction().isActive()) {
                entityManagerUser.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManagerUser.close();
        }
    }
}
