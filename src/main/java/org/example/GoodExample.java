package org.example;

import org.jspecify.annotations.Nullable;

/**
 * GOOD EXAMPLE - Follows all rules
 * - Methods ≤ 10 lines
 * - Cyclomatic complexity ≤ 3
 * - Cognitive complexity ≤ 3
 * - Nesting depth ≤ 1
 * - No magic numbers
 * - No Law of Demeter violations
 * - ≤ 3 parameters
 * - Final parameters
 * - Behavioral methods instead of setters
 * - JSpecify annotations for null safety
 */
public class GoodExample {

    private static final int MIN_DAMAGE = 5;
    private static final int MEDIUM_DAMAGE = 10;
    private static final int HIGH_DAMAGE = 15;

    private int health;

    public GoodExample(final int initialHealth) {
        this.health = initialHealth;
    }

    public void receiveDamage(final int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    public int calculateDamage(final int level) {
        if (level > MIN_DAMAGE) {
            return HIGH_DAMAGE;
        }
        if (level > 0) {
            return MEDIUM_DAMAGE;
        }
        return MIN_DAMAGE;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public int health() {
        return this.health;
    }

    @Nullable
    public String status() {
        if (!isAlive()) {
            return null;
        }
        return "alive";
    }
}
