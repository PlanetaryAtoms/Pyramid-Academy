package com.company;


public class Destroyer extends Ship {

    public Destroyer() {
        this.length = 2;
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }

    @Override
    public int getLength() {
        return this.length;
    }

}
