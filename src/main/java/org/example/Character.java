package org.example;

public class Character {
    private static final int STARTING_HEALTH = 1000;
    private int health;

    public Character() {
        this.health = STARTING_HEALTH;
    }

    public int health() {
        return this.health;
    }

    public void receiveDamage(final int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public boolean isAlive() {
        return this.health > 0;
    }
}
