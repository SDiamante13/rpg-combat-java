package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterShould {
    @Test
    void reduceHealthWhenReceivingDamage() {
        final Character attacker = new Character();
        final Character target = new Character();
        final int initialHealth = target.health();

        target.receiveDamage(attacker, 100);

        assertThat(target.health()).isEqualTo(initialHealth - 100);
    }

    @Test
    void notReduceHealthBelowZero() {
        final Character attacker = new Character();
        final Character target = new Character();

        target.receiveDamage(attacker, 2000);

        assertThat(target.health()).isEqualTo(0);
    }

    @Test
    void beDeadWhenHealthReachesZero() {
        final Character attacker = new Character();
        final Character target = new Character();

        assertThat(target.isAlive()).isTrue();

        target.receiveDamage(attacker, 1000);

        assertThat(target.isAlive()).isFalse();
    }

    @Test
    void notReceiveDamageFromItself() {
        final Character character = new Character();
        final int initialHealth = character.health();

        character.receiveDamage(character, 100);

        assertThat(character.health()).isEqualTo(initialHealth);
    }

    @Test
    void healItselfWhenAlive() {
        final Character attacker = new Character();
        final Character character = new Character();

        character.receiveDamage(attacker, 200);

        character.heal(100);

        assertThat(character.health()).isEqualTo(900);
    }

    @Test
    void notHealItselfWhenDead() {
        final Character attacker = new Character();
        final Character character = new Character();

        character.receiveDamage(attacker, 1000);
        character.heal(100);

        assertThat(character.health()).isEqualTo(0);
    }

    @Test
    void notHealAboveMaximumHealth() {
        final Character attacker = new Character();
        final Character character = new Character();

        character.receiveDamage(attacker, 100);
        character.heal(200);

        assertThat(character.health()).isEqualTo(1000);
    }
}
