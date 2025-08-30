package org.s21.domain.factory;

import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.entity.creature.enemies.EnemyType;
import org.s21.domain.model.entity.creature.enemies.Ghost;
import org.s21.domain.model.entity.creature.enemies.Ogre;
import org.s21.domain.model.entity.creature.enemies.Snake;
import org.s21.domain.model.entity.creature.enemies.Vampire;
import org.s21.domain.model.entity.creature.enemies.Zombie;
import org.s21.domain.model.entity.stats.EnemyStats;
import org.s21.domain.model.utils.Coordinate;

import static org.s21.infrastructure.config.Config.EXTRA_HIGH;
import static org.s21.infrastructure.config.Config.LOW;
import static org.s21.infrastructure.config.Config.MID;
import static org.s21.infrastructure.config.Config.HIGH;

public class EnemyFactory {

    public static Enemy createEnemy(EnemyType type, Coordinate coordinate, int level) {
        Enemy enemy;
        switch (type) {
            case VAMPIRE -> {
                EnemyStats enemyStats = new EnemyStats(HIGH, MID, HIGH, HIGH,HIGH);
                enemy = new Vampire(coordinate, enemyStats);
            }
            case GHOST -> {
                EnemyStats enemyStats = new EnemyStats(HIGH, LOW, LOW, LOW, LOW);
                enemy = new Ghost(coordinate, enemyStats);
            }
            case OGRE -> {
                EnemyStats enemyStats = new EnemyStats(LOW, EXTRA_HIGH, EXTRA_HIGH, EXTRA_HIGH, MID);
                enemy = new Ogre(coordinate, enemyStats);
            }
            case SNAKE -> {
                EnemyStats enemyStats = new EnemyStats(EXTRA_HIGH, LOW, LOW, LOW, LOW);
                enemy = new Snake(coordinate, enemyStats);
            }
            default -> {
                EnemyStats enemyStats = new EnemyStats(LOW, MID, HIGH, HIGH, MID);
                enemy = new Zombie(coordinate, enemyStats);
            }
        }
        enemy.getStats().improveStats(level);
        return enemy;
    }
}
