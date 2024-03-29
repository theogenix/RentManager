package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.EmailAlreadyExistsException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;

	public static ClientService instance;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;
	}
	
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		LocalDate today = LocalDate.now();
		if (ChronoUnit.YEARS.between(client.getBirthday(), today) < 18) {
			throw new ServiceException("the client is too young");
		}
		if((client.getName().length())<3){
			throw new ServiceException("Name length are too short, must be > 2");
		}
		if((client.getSurname().length())<3){
			throw new ServiceException("surname length are too short, must be > 2");
		}
		try {
			if (clientDao.findByEmail(client.getEmail()) != null) {
				throw new EmailAlreadyExistsException();
			}
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}

		try{
			return this.clientDao.create(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException("An error occurred while creating the client.");
		}
	}
	public long update(Client client) throws ServiceException {
		// TODO: modifier un client
		try{
			return this.clientDao.update(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException("An error occurred while creating the client.");
		}
	}
	public long delete(long id) throws ServiceException{

		/*

		try {
			if(ReservationDao.findById(id)!= null)
				ReservationDao.delete(id);
		} catch (DaoException e) {
			throw new RuntimeException(e);
		}*/

		try{
			return this.clientDao.delete(id);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur");
			throw new ServiceException("An error occurred while deleting the client.");
		}
	}

	public Client findById(long id) throws ServiceException {
		try{
			return this.clientDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException("An error occurred while finding by id the client.");
		}
		// TODO: récupérer un client par son id
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException("An error occurred while finding clients.");
		}
	}
}
