package com.company;


public class Submarine extends Ship {

    public Submarine() {
        this.length = 1;
    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }

}

