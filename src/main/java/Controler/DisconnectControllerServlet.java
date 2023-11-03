package Controler;

import jakarta.servlet.annotation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;


import java.io.IOException;

@WebServlet(name = "disconnectControllerServlet", value = "/disconnectController-servlet")
public class DisconnectControllerServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("type");
        request.getSession().removeAttribute("errModify");

        response.sendRedirect("redirection-servlet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
