package com.epf.rentmanager.service;
import java.util.List;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

/*
    public long getVehcile_id(ReservationDao reservationDao){
        return reservationDao.getVehicle_id(reservation);
    }*/

    private long getVehicle_id() {
        return 0;
    }

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation
        try{
            return this.reservationDao.create(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException("An error occurred while creating the rent.");
        }
    }
    public long update(Reservation reservation) throws ServiceException {
        // TODO: modifier une reservation
        try{
            return this.reservationDao.update(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException("An error occurred while updating the rent.");
        }
    }

    public List <Reservation> findByIdClient(long id) throws ServiceException {
        try{
            return this.reservationDao.findResaByClientId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException("An error occurred while finding by client id the rent.");
        }
    }
    public List<Reservation> findByIdVehicle(long id) throws ServiceException {
        try{
            return this.reservationDao.findResaByVehicleId(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException("An error occurred while finding by vehicle id the rent.");
        }
    }
    public long delete(long id) throws ServiceException{
        try{
            return this.reservationDao.delete(id);
        }catch(DaoException e){
            e.printStackTrace();
            System.out.println("erreur");
            throw new ServiceException("An error occurred while deleting the rent.");
        }
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return this.reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("An error occurred while finding rents.");
        }
    }
    public Reservation findById(long id) throws ServiceException {
        try{
            return this.reservationDao.findById(id);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException("An error occurred while finding by id the rent.");
        }
        // TODO: récupérer un client par son id
    }
}
