package com.epf.rentmanager.model;
import java.time.LocalDate;

public class Reservation{

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
}
