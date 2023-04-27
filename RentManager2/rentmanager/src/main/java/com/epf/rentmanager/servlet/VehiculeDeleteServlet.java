package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vehicles/delete")
public class VehiculeDeleteServlet extends HttpServlet {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            List<Reservation> reservations = this.reservationService.findByIdVehicle(id);
            List<Integer> reservations_id = new ArrayList<>();
            System.out.println("ma liste vehicle"+reservations);
            for (int i = 0; i < reservations.size(); i++) {
                reservations_id.add(reservations.get(i).getClient_id());
            }
            System.out.println("ma liste"+reservations_id);
            for (int i = 0; i < reservations_id.size(); i++) {
                reservationService.delete(reservations_id.get(i));
            }
            vehicleService.delete(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/cars");
    }
}
