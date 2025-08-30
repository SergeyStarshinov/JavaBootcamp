package org.s21.application.usecase.combat;

import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.entity.creature.enemies.Enemy;

public class Combat {
    public static String execute(Enemy enemy, Hero hero) {
        return hero.attack(enemy);
    }
}
