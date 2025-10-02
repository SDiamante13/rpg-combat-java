package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {
    @Test
    void reduceHealthWhenReceivingDamage() {
        final Character character = new Character();
        final int initialHealth = character.health();

        character.receiveDamage(100);

        assertThat(character.health()).isEqualTo(initialHealth - 100);
    }

    @Test
    void notReduceHealthBelowZero() {
        final Character character = new Character();

        character.receiveDamage(2000);

        assertThat(character.health()).isEqualTo(0);
    }

    @Test
    void beAliveWhenHealthIsAboveZero() {
        final Character character = new Character();

        assertThat(character.isAlive()).isTrue();
    }

    // [TEST] Be dead when health reaches zero
}
