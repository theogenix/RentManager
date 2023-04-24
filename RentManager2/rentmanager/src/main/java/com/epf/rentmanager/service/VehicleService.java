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
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule

		if(vehicle.getMaker()==null){
			throw new ServiceException("vehicle must have a maker");
		}
		if(vehicle.getModel()==null){
			throw new ServiceException("vehicle must have a model");
		}
		if(vehicle.getNb_places()<2 || vehicle.getNb_places()>9 ){
			throw new ServiceException("vehicle seats must be included between 2 and 9");
		}

		try{
			return this.vehicleDao.create(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException("An error occurred while creating the vehicle.");
		}
	}
	public long update(Vehicle vehicle) throws ServiceException {
		// TODO: modifier un véhicule
		try{
			return this.vehicleDao.update(vehicle);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur de modification");
			throw new ServiceException("An error occurred while updating the vehicle.");
		}
	}
	public long delete(long id) throws ServiceException{
		try{
			return this.vehicleDao.delete(id);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur de delete");
			throw new ServiceException("An error occurred while deleting the vehicle.");
		}
	}

	public Vehicle findById(long id) throws ServiceException {
		try{
			return this.vehicleDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException("An error occurred while finding by id the vehicle.");
		}
	}
	public List<Vehicle> findAll() throws ServiceException {
		try {
			return this.vehicleDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("An error occurred while finding vehicles.");
		}
	}

}
