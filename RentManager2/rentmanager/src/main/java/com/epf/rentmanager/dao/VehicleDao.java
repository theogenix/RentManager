package com.epf.rentmanager.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.persistence.ConnectionManager;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {

	}
	
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, model, nb_places) VALUES(?,?,?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, model, nb_places FROM Vehicle;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom = ?, prenom = ?, email = ?, naissance = ? WHERE id=?;";
	
	public long create(Vehicle vehicle) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,vehicle.getMaker());
			stmt.setString(2,vehicle.getModel());
			stmt.setInt(3,vehicle.getNb_places());
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public long delete(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(DELETE_VEHICLE_QUERY);
			stmt.setLong(1,id);
			return stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	public Vehicle findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(FIND_VEHICLE_QUERY);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			String constructeur=(rs.getString("constructeur"));
			String model=(rs.getString("model"));
			int nb_places =(rs.getInt("nb_places"));
			return new Vehicle((int) id,constructeur,model,nb_places);
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles=new ArrayList<Vehicle>();
		try {
			Connection connection=ConnectionManager.getConnection();
			Statement statement=connection.createStatement();
			ResultSet rs=statement.executeQuery(FIND_VEHICLES_QUERY);
			while(rs.next()){
				int id=(rs.getInt("id"));
				String constructeur=(rs.getString("constructeur"));
				String model=(rs.getString("model"));
				int nb_places =(rs.getInt("nb_places"));
				vehicles.add(new Vehicle(id,constructeur,model,nb_places));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}

		return vehicles;
		//return new ArrayList<Vehicle>();
		
	}
	

}
