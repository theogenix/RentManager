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

@WebServlet("/vehicles/details")
public class VehiculeDetailsServlet extends HttpServlet {
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
        long vehicle_id = Integer.parseInt(req.getParameter("id"));

        try {
            List<Reservation> reservations = this.reservationService.findByIdVehicle(vehicle_id);
            List<Client> clients = new ArrayList<>();
            List<Integer> client_ids = new ArrayList<>();
            int client_id = 0;
            int counter=0;
            for (Reservation reservation : reservations) {
                client_ids.add(reservation.getClient_id());
                counter+=1;
            }

            for (int i = 0; i < client_ids.size(); i++) {
                Client client = clientService.findById(client_ids.get(i));
                clients.add(client);
            }

            req.setAttribute("clients",clients);
            req.setAttribute("nb_client",counter);
            req.setAttribute("reservations", reservations);
            req.setAttribute("vehicles", this.vehicleService.findById(vehicle_id));
            req.setAttribute("nb_reservation", reservations.size());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/details.jsp").forward(req,resp);
    }
}
