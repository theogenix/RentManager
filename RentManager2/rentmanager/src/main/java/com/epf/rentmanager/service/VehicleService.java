package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	/*
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}*/

	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		try{
			return this.vehicleDao.create(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur de création");
			throw new ServiceException();
		}
	}
	public long delete(long id) throws ServiceException{
		try{
			return this.vehicleDao.delete(id);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur de delete");
			throw new ServiceException();
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return this.vehicleDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

}
