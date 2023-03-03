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


public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation
        try{
            return ReservationDao.getInstance().create(reservation);
        }catch(DaoException e){
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    public Reservation findById(long id) throws ServiceException {
        // TODO: récupérer une réservation par son id
        return new Reservation();
    }

    public List<Reservation> findAll() throws ServiceException {
        try {
            return ReservationDao.getInstance().findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
}
