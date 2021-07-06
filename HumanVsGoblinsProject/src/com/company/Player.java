package com.company;
/**
 * a player is an entity and has 100 HP
 * it has the UTF char 'P'
 */
public class Player extends Entity {

    private float health;

    public Player(int x, int y) {
        super(x, y, 'P');
        health = 100;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float value) {
        this.health = value;
    }
}
