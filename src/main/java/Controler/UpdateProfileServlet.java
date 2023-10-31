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
@WebServlet(name = "updateProfileServlet", value = "/connectController-servlet")
public class UpdateProfileServlet extends HttpServlet{
}
