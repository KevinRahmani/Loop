package Controler;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.Basket;
import model.beans.ClientEntity;
import model.service.UserService;
import utils.GMailerUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "mailControllerServlet", value = "/mailController-servlet")
public class MailControllerServlet extends HttpServlet {

    private UserService<ClientEntity> clientService;

    private EntityManager entityManager;
    public void init() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("userPersistence");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        clientService = new UserService<>(ClientEntity.class, entityManager);
    }


    public static void sendMail(HttpServletRequest request, HttpServletResponse response, String choice, ClientEntity client) throws IOException, ServletException {

        String emailContent = "", emailSubject = "";

        try {
            switch (choice){
                case "inscription":
                    //Parameters
                    emailSubject = "Bienvenue chez Loop !";
                    emailContent = "<h1>Inscription Loop</h1><br>"+
                            "    <div style=\"background-color: #f4f4f4; padding: 20px; text-align: center;\"><br>" +
                            "        <h2>"+ client.getNom() +", Loop vous souhaite la bienvenue sur notre plateforme !</h2><br>" +
                            "        <p>Merci de vous être inscrit(e). Votre compte a été créé avec succès.</p><br>" +
                            "        <p>Vous pouvez maintenant accéder à notre service en vous connectant avec vos identifiants.</p><br>" +
                            "        <p>Nous vous disons à très vite.</p><br>" +
                            "    </div>";
                    break;

                case "command" :
                    //Parameters
                    Basket basket = (Basket) request.getSession().getAttribute("basket");
                    double totalPriceTTC = (Double) request.getSession().getAttribute("totalPriceTTC");

                    emailSubject = "Commande Loop";
                    emailContent = "Bonjour "+client.getNom()+",<br><br>"+
                            "Merci pour votre commande d'un montant total de " +totalPriceTTC + " &euro.<br>" +
                            "Vous serez livré sous un délai de 3 jours hors week-end et jours fériés au " + client.getAdresse() +".<br>" +
                            "Voici votre commande : <br><br>";
                    for (Map.Entry<ArticleEntity, Integer> entry : basket.getPanier().entrySet()){
                        emailContent+="Article : " +entry.getKey().getNom() + "<br>Quantité : " + entry.getValue() +"<br><br>";
                    }
                    emailContent+="<br>Merci pour votre achat.<br>L'équipe Loop vous dis à bientot !";
                    break;

                case "publicity":
                    //
                    emailSubject = "Promotions Loop !";
                    emailContent ="Bonjour " + client.getNom() +",<br><br>" +
                            "C'est la période des promus sur Loop, ne manquer pas nos soldes pouvant aller jusqu'à -60%<br><br>" +
                            "Et venez découvrir nos nouvelles voitures fraichement arrivé comme la Peugeot 208 édition GTLine, la Audi R8 ou encore la toute nouvelle Mercedes Classe A. " +
                            "<br><br>L'équipe Loop vous dis à bientot !";
                    break;
            }

            if(client != null && !emailSubject.isEmpty() && !emailContent.isEmpty()){
                new GMailerUtils().sendMail(emailSubject, emailContent, client.getMail());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (entityManager == null || !entityManager.isOpen()) {
            init();
        }
        List<ClientEntity> listClient = clientService.findAll();
        for(ClientEntity client : listClient){
            sendMail(request,response,"publicity",client);
        }
        response.setContentType("text/plain");
        response.getWriter().write("ok");
    }

}
