package com.epf.rentmanager.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
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
		LocalDate today = LocalDate.now();
		if (ChronoUnit.YEARS.between(client.getBirthday(), today) < 18) {
			throw new ServiceException();
		}
		// TODO: créer un client
		try{
			return this.clientDao.create(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public long update(Client client) throws ServiceException {
		// TODO: modifier un client
		try{
			return this.clientDao.update(client);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public long delete(long id) throws ServiceException{
		try{
			return this.clientDao.delete(id);
		}catch(DaoException e){
			e.printStackTrace();
			System.out.println("erreur");
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		try{
			return this.clientDao.findById(id);
		}catch(DaoException e){
			e.printStackTrace();
			throw new ServiceException();
		}
		// TODO: récupérer un client par son id
	}

	public List<Client> findAll() throws ServiceException {
		try {
			return this.clientDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
}
