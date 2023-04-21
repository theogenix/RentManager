package com.epf.rentmanager.main;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.naming.spi.ResolveResult;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

   public static void main(String argu []) {
      ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
      ClientService clientService = context.getBean(ClientService.class);
      VehicleService vehicleService = context.getBean(VehicleService.class);
      ReservationService reservationService = context.getBean(ReservationService.class);


      try {
         List<Client> clients= clientService.findAll();
         System.out.println(clients);
         for(Client c:clients){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }

/*
      try {
         List<Vehicle> vehicles= vehicleService.findAll();
         System.out.println(vehicles);
         for(Vehicle v:vehicles){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }
*/
      try {
         List<Reservation> reservations= reservationService.findAll();
         System.out.println(reservations);
         for(Reservation r:reservations){
            //System.out.println(c);
         }
      } catch (ServiceException e) {
         throw new RuntimeException(e);
      }

/*
      try {
         Client client= clientService.findById(1);
         System.out.println(client);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
*//*

      try {
         Vehicle vehicle= vehicleService.findById(4);
         System.out.println(vehicle);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }*/
/*
      try {
         long id_client= clientService.create(new Client("theo","genix","theogenix@gmail.com",LocalDate.of(2001,01,07)));
         System.out.println(id_client);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
      */

      try {
         clientService.update(new Client("theo","genix","theogenix@gmail.com",LocalDate.of(2001,01,07)));


      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
      /*

      try {
         long id_vehicle= vehicleService.create(new Vehicle("Renault","laguna", 5));
         System.out.println(id_vehicle);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
*//*
      try {
         long id_reservation= reservationService.create(new Reservation(1,1,LocalDate.of(2001,01,01),LocalDate.of(2002,01,01)));
         System.out.println(id_reservation);

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }*/


      /*
      try {
         vehicleService.delete(2);
         System.out.println("véhicule supprimé");

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }


      try {
         clientService.delete(2);
         System.out.println("client supprimé");

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }

*//*
      try {
         reservationService.delete(2);
         System.out.println("reservation supprimé");

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
/**/
/*
      try {
         Reservation reservation= reservationService.findByIdClient(2);
         System.out.println(reservation.toString2());

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }

      try {
         Reservation reservation= reservationService.findByIdVehicle(3);
         System.out.println(reservation.toString3());

      }catch(ServiceException e){
         throw new RuntimeException(e);
      }
*/
   }
}
