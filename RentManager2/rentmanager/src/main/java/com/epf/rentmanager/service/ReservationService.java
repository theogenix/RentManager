package com.epf.rentmanager.service;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private long getVehicle_id() {
        return 0;
    }

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation

        /*
        try {
            List<Reservation> reservations=reservationDao.findAll();
            Map<Integer, Integer> compteur = new HashMap<>();
            Map<Integer, Integer> compteurVehicule = new HashMap<>();
            for (Reservation reservation1 : reservations) {
                int clientId = reservation1.getClient_id();
                int vehiculeId = reservation1.getVehicle_id();

                int days = (int) (ChronoUnit.DAYS.between(reservation1.getEnd(), reservation1.getStart()));
                int count = compteur.getOrDefault(clientId, 0);
                if (count + days > 7 && vehiculeId == compteurVehicule.getOrDefault(clientId, -1)) {
                    throw new IllegalArgumentException("Le client " + clientId + " ne peut pas réserver le véhicule " + vehiculeId + " pour plus de 7 jours");
                }

                // mettre à jour le compteur pour ce client
                compteur.put(clientId, count + days);
                compteurVehicule.put(clientId, vehiculeId);
            }

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        */

        if (ChronoUnit.DAYS.between(reservation.getStart(), reservation.getEnd()) > 30) {
            throw new ServiceException("a car can't be rent for more than 30 days without break");
        }


        try {
            List<Reservation> reservations=reservationDao.findAll();
            for (int i = 0; i < reservations.size(); i++){
                try {
                    if(((reservationDao.findAll().get(i).getVehicle_id())==(reservation.getVehicle_id()))&&(reservationDao.findAll().get(i).getStart().equals(reservation.getStart())))
                        throw new ServiceException("vehicles can't be rent twice the same day");
                } catch (DaoException e) {
                    throw new ServiceException("vehicles can't be rent twice the same day");
                }
            }

        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

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
