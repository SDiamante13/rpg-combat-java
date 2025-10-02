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

    public void receiveDamage(final Character attacker, final int damage) {
        if (attacker == this) {
            return;
        }
        this.health = Math.max(0, this.health - damage);
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void heal(final int amount) {
        if (!this.isAlive()) {
            return;
        }
        this.health = Math.min(STARTING_HEALTH, this.health + amount);
    }
}
