package com.epf.rentmanager.model;
import java.time.LocalDate;

public class Reservation{
    private Client client;
    private Vehicle vehicle;

    private int client_id;
    private int vehicle_id;
    private LocalDate start;
    private LocalDate end;
    private int id;

    public Reservation(int id, LocalDate start, LocalDate end) {
        this.id=id;
        this.start = start;
        this.end = end;
    }
    /*
    public Reservation(int id, Client client, Vehicle vehicle,LocalDate start,LocalDate end){
        this.id=id;
        this.client=client;
        this.vehicle=vehicle;
        this.start=start;
        this.end=end;
    }*/


    public Reservation(int client_id, int vehicle_id, LocalDate start, LocalDate end) {
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public Reservation(){
        super();
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", start=" + start +
                ", end=" + end +
                ", id=" + id +
                '}';
    }
}
