package com.company;


public class Cruiser extends Ship {


    public Cruiser() {
        this.length = 3;
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }

    @Override
    public int getLength() {
        return this.length;
    }

}
