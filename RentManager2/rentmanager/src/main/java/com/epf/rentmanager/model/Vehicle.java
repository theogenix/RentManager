package com.epf.rentmanager.model;
import java.time.LocalDate;

public class Vehicle{

    private String maker;
    private String model;
    private int nb_places;
    private int id;

    public Vehicle(int id,String maker, int nb_places) {
        this.id=id;
        this.maker = maker;
        //this.model = model;
        this.nb_places = nb_places;
    }

    public Vehicle(String maker, String model, int nb_places) {
        this.maker = maker;
        this.model = model;
        this.nb_places = nb_places;
    }

    public Vehicle(String maker, int nb_places) {
        this.maker = maker;
        this.nb_places = nb_places;
    }

    public Vehicle(){
        super();
    }


    public Vehicle(int id, String maker, String model, int nb_places) {
        this.maker = maker;
        this.model = model;
        this.nb_places = nb_places;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", nb_places=" + nb_places +
                ", id=" + id +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }
}
