package com.epf.rentmanager.servlet;


import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
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

@WebServlet("/rents/update")
public class ReservationUpdateServlet extends HttpServlet {
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
            req.setAttribute("reservation",reservationService.findById(id));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/update.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int client_id;
        int vehicle_id;
        LocalDate start;
        LocalDate end;

        long id = Long.parseLong(req.getParameter("id"));
        client_id = Integer.parseInt(req.getParameter("client_id"));
        vehicle_id = Integer.parseInt(req.getParameter("vehicle_id"));
        start = LocalDate.parse(req.getParameter("start"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        end = LocalDate.parse(req.getParameter("end"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Reservation reservation = new Reservation((int)id,client_id,vehicle_id,start,end);
        try {
            this.reservationService.update(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/rentmanager/rents");
    }


}
