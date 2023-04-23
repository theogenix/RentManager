package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
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

@WebServlet("/vehicles/delete")
public class VehiculeDeleteServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//            String strId = req.getParameter("id");
//            System.out.println("La valeur de la chaîne de caractères est : " + strId);
//            String strId2 = req.getParameter("maker");
//            System.out.println("La valeur de la chaîne de caractères est : " + strId2);
            long id = Long.parseLong(req.getParameter("id"));
            vehicleService.delete(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/cars");
    }
}
