package com.company;


public class EmptySea extends Ship {

    private boolean isShot = false;

    public EmptySea() {
        this.length = 1;
    }

    @Override
    public boolean shootAt(int row, int col) {
        this.isShot = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        if(this.isShot == true) {
            return "m";
        }
        return ".";
    }

    @Override
    public String getShipType() {
        return "empty";
    }

}
