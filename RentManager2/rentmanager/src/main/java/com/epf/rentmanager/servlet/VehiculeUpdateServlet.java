package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
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

@WebServlet("/vehicles/update")
public class VehiculeUpdateServlet extends HttpServlet {
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
            long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("vehicle",vehicleService.findById(id));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maker;
        String model;
        int Nb_places;

        long id = Long.parseLong(req.getParameter("id"));
        maker = req.getParameter("constructeur");
        model = req.getParameter("model");
        Nb_places = Integer.parseInt(req.getParameter("nb_places"));

        Vehicle vehicle = new Vehicle((int)id,maker, model, Nb_places);
        try {
            this.vehicleService.update(vehicle);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/cars");
    }


}
