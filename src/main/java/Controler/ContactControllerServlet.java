package Controler;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.beans.ArticleEntity;
import model.beans.Basket;
import model.service.ArticleService;
import model.service.BasketService;
import utils.GMailerUtils;
import utils.ProcessBasketServlet;
import utils.VerifyData;


//CHANGER LES REDIRECTIONS DE REDIRECT SERVLET CA CAUSE PEUT ETRE LE BUG
@WebServlet(name = "contactControllerServlet", value = "/contactController-servlet")
public class ContactControllerServlet extends HttpServlet {

    private static final String TEST_EMAIL = "loop.cytech@gmail.com";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/contact.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String message = request.getParameter("message");
        String status = "Veuillez remplir tous les champs";


        try {
            if(VerifyData.verifyParameters(name,mail,message,"testSake", "testSake", "testSake")){
                status = "Demande de contact envoyé avec succès";
                String subject = "Demande de contact";
                String content = "Bonjour Administrateur,<br><br>Vous avez reçu un nouveau messsage de "
                        + name + " le " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " dont l'adresse mail est : " + mail +
                        " : <br><br>" + message;

                new GMailerUtils().sendMail(subject,content,TEST_EMAIL);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("status", status);
        doGet(request,response);
    }
}
