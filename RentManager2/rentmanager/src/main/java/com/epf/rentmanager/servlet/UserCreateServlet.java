package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom;
        String prenom;
        String email;
        LocalDate birthday;
        nom = req.getParameter("first_name");
        prenom = req.getParameter("last_name");
        email = req.getParameter("email");
        //System.out.println(req.getParameter("naissance"));
        birthday = LocalDate.parse(req.getParameter("naissance"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Client client = new Client(nom, prenom, email, birthday);
        try {
            this.clientService.create(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/users/create");
    }


}
