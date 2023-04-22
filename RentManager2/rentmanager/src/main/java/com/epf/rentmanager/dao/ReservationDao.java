package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.stereotype.Repository;

import javax.naming.spi.ResolveResult;

@Repository
public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {

	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	ClientService clientService;
	public long getVehicle_id(Reservation reservation){
		return reservation.getVehicle_id();
	}
	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,reservation.getClient_id());
			stmt.setInt(2,reservation.getVehicle_id());
			stmt.setDate(3, Date.valueOf(reservation.getStart()));
			stmt.setDate(4, Date.valueOf(reservation.getEnd()));
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}/*	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1,reservation.getClient());
			stmt.setObject(2,reservation.getVehicle());
			stmt.setDate(3, Date.valueOf(reservation.getStart()));
			stmt.setDate(4, Date.valueOf(reservation.getEnd()));
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}*/

	public long delete(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(DELETE_RESERVATION_QUERY);
			stmt.setLong(1,id);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public  Reservation findResaByClientId(long clientId ) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			stmt.setLong(1, clientId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id=(rs.getInt("id"));
			int id_vehicle=(rs.getInt("vehicle_id"));
			LocalDate start=(rs.getDate("debut").toLocalDate());
			LocalDate end=(rs.getDate("fin").toLocalDate());
			return new Reservation(id,id_vehicle,start,end);
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	/*
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {

		try {
			Connection connexion = ConnectionManager.getConnection();

			PreparedStatement statement = connexion.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);

			statement.setLong(1, clientId);

			ResultSet rs = statement.executeQuery();

			List<Reservation> reservations = new ArrayList<>();


			while (rs.next()) {
				Client client = clientService.findById(clientId);
				Vehicle vehicle = vehicleService.findById(rs.getLong("vehicle_id"));

				reservations.add(new Reservation(rs.getLong("id"),
						client, vehicle,
						rs.getDate("debut").toLocalDate(),
						rs.getDate("fin").toLocalDate()));
			}
			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	 */

	public  Reservation findResaByVehicleId(long vehicleID) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			stmt.setLong(1, vehicleID);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id=(rs.getInt("id"));
			int id_client=(rs.getInt("client_id"));
			LocalDate start=(rs.getDate("debut").toLocalDate());
			LocalDate end=(rs.getDate("fin").toLocalDate());
			return new Reservation(id,id_client,start,end);
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations=new ArrayList<Reservation>();
		try {
			Connection connection=ConnectionManager.getConnection();
			Statement statement=connection.createStatement();
			ResultSet rs=statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while(rs.next()){
				int id=(rs.getInt("id"));
				int id_client=(rs.getInt("client_id"));
				int id_vehicle=(rs.getInt("vehicle_id"));
				LocalDate start=(rs.getDate("debut").toLocalDate());
				LocalDate end=(rs.getDate("fin").toLocalDate());
				reservations.add(new Reservation(id,id_client,id_vehicle,start,end));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return reservations;
		//return new ArrayList<Reservation>();
	}/*
public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations=new ArrayList<Reservation>();
		try {
			Connection connection=ConnectionManager.getConnection();
			Statement statement=connection.createStatement();
			ResultSet rs=statement.executeQuery(FIND_RESERVATIONS_QUERY);
			while(rs.next()){
				int id=(rs.getInt("id"));
				Client client= clientService.findById(rs.getInt("client_id"));
				Vehicle vehicle = VehicleService.instance.findById(rs.getInt("vehicle_id"));
				LocalDate start=(rs.getDate("debut").toLocalDate());
				LocalDate end=(rs.getDate("fin").toLocalDate());
				reservations.add(new Reservation(id,client,vehicle,start,end));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		} catch (ServiceException e) {
			throw new RuntimeException(e);
		}

	return reservations;
		//return new ArrayList<Reservation>();
	}*/
}
