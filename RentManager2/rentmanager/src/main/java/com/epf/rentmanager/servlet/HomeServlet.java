package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ClientService clientService;
	@Autowired
	ReservationService reservationService;
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("nbClients", this.clientService.findAll().size());
		} catch (ServiceException e) {
			throw new ServletException();
		}
		try {
			request.setAttribute("nbVehicle", this.vehicleService.findAll().size());
		} catch (ServiceException e) {
			throw new ServletException();
		}
		try {
			request.setAttribute("nbReservation", this.reservationService.findAll().size());
		} catch (ServiceException e) {
			throw new ServletException();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}
}
