package com.company;


public class Battleship extends Ship {

    public Battleship() {
        this.length = 4;
    }



    @Override
    public String getShipType() {
        return "BattleShip";
    }


    @Override
    public int getLength() {
        return this.length;
    }

}
