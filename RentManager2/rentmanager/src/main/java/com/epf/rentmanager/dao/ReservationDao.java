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
	public ReservationDao() {

	}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String UPDATE_RESERVATIONS_QUERY = "UPDATE Reservation SET client_id = ?,vehicle_id = ?, debut = ?, fin = ? WHERE id=?;";
	ClientService clientService;
	VehicleService vehicleService;
	public static long getVehicle_id(Reservation reservation){
		return reservation.getVehicle_id();
	}
	public static long create(Reservation reservation) throws DaoException {
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
	}
	public static long delete(long id) throws DaoException {
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

	public static List<Reservation> findResaByClientId(long clientId) throws DaoException {

		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement statement = connexion.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY);
			statement.setLong(1, clientId);
			ResultSet rs = statement.executeQuery();
			List<Reservation> reservations = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				int vehicleId = rs.getInt("vehicle_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, vehicleId, start, end);
				reservations.add(reservation);
			}
			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
		} /*catch (ServiceException e) {
			e.printStackTrace();
		}*/
		return null;
	}

	public static List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {

		try {
			Connection connexion = ConnectionManager.getConnection();
			PreparedStatement statement = connexion.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);
			statement.setLong(1, vehicleId);
			ResultSet rs = statement.executeQuery();
			List<Reservation> reservations = new ArrayList<>();

			while (rs.next()) {
				int id = rs.getInt("id");
				int clientId = rs.getInt("client_id");
				LocalDate start = rs.getDate("debut").toLocalDate();
				LocalDate end = rs.getDate("fin").toLocalDate();
				Reservation reservation = new Reservation(id, clientId, start, end);
				reservations.add(reservation);
			}
			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
		} /*catch (ServiceException e) {
			e.printStackTrace();
		}*/
		return null;
	}

	public static long update(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(UPDATE_RESERVATIONS_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1,reservation.getClient_id());
			stmt.setInt(2,reservation.getVehicle_id());
			stmt.setDate(3, Date.valueOf(reservation.getStart()));
			stmt.setDate(4, Date.valueOf(reservation.getEnd()));
			stmt.setInt(5, reservation.getId());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public static List<Reservation> findAll() throws DaoException {
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
	}

	public static Reservation findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			id=(rs.getInt("id"));
			int id_client=(rs.getInt("client_id"));
			int id_vehicle=(rs.getInt("vehicle_id"));
			LocalDate start=(rs.getDate("debut").toLocalDate());
			LocalDate end=(rs.getDate("fin").toLocalDate());
			return new Reservation((int) id,id_client,id_vehicle,start,end);
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
}
