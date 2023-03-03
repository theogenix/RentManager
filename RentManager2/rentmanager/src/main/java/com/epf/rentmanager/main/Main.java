package com.epf.rentmanager.main;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

   public static void main(String argu []) {
      try {
         List<Client> clients= ClientService.getInstance().findAll();
         System.out.println(clients);
         for(Client c:clients){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
      try {
         List<Vehicle> vehicles= VehicleService.getInstance().findAll();
         System.out.println(vehicles);
         for(Vehicle v:vehicles){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
      try {
         List<Reservation> reservations= ReservationService.getInstance().findAll();
         System.out.println(reservations);
         for(Reservation r:reservations){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
      try {
         Client client= ClientService.getInstance().findById(1);
         System.out.println(client);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
      try {
         Vehicle vehicle= VehicleService.getInstance().findById(2);
         System.out.println(vehicle);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
      try {
         long id_client= ClientService.getInstance().create(new Client("theo","genix","theogenix@gmail.com",LocalDate.of(2001,01,07)));
         System.out.println(id_client);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
      try {
         long id_vehicle= VehicleService.getInstance().create(new Vehicle("Renault", 5));
         System.out.println(id_vehicle);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }

      try {
         long id_reservation= ReservationService.getInstance().create(new Reservation(1,1,LocalDate.of(2001,01,01),LocalDate.of(2002,01,01)));
         System.out.println(id_reservation);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
   }
}
