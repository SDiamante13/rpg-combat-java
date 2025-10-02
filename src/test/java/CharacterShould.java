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

    // [TEST] Not reduce health below zero
    // [TEST] Be alive when health is above zero
    // [TEST] Be dead when health reaches zero
}
