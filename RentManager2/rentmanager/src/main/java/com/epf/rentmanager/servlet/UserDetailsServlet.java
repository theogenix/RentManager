package com.epf.rentmanager.servlet;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
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

@WebServlet("/users/details")
public class UserDetailsServlet extends HttpServlet {
    @Autowired
    ClientService clientService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long client_id = Integer.parseInt(req.getParameter("id"));

        try {
            List<Reservation> reservations = this.reservationService.findByIdClient(client_id);
            List<Vehicle> vehicles = new ArrayList<>();
            List<Integer> vehicle_ids = new ArrayList<>();
            int vehicle_id = 0;
            int counter=0;
            for (Reservation reservation : reservations) {
                vehicle_ids.add(reservation.getVehicle_id());
                counter+=1;
            }

            for (int i = 0; i < vehicle_ids.size(); i++) {
                Vehicle vehicle = vehicleService.findById(vehicle_ids.get(i));
                vehicles.add(vehicle);
            }

            req.setAttribute("vehicles",vehicles);
            req.setAttribute("nb_vehicle",counter);
            req.setAttribute("reservations", reservations);
            req.setAttribute("clients", this.clientService.findById(client_id));
            req.setAttribute("nb_reservation", reservations.size());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(req,resp);
    }
}
