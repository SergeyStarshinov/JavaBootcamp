package org.s21.infrastructure.generation;

import org.s21.infrastructure.config.Config;
import org.s21.domain.model.entity.creature.enemies.Enemy;
import org.s21.domain.model.entity.creature.enemies.EnemyType;
import org.s21.domain.factory.EnemyFactory;
import org.s21.domain.factory.ItemFactory;
import org.s21.domain.model.items.Item;
import org.s21.domain.model.items.ItemType;
import org.s21.domain.model.utils.Coordinate;
import org.s21.util.RandomEnumGenerator;
import org.s21.domain.model.utils.TerrarianType;
import org.s21.domain.model.world.GameSession;
import org.s21.domain.model.world.Level;
import org.s21.domain.model.world.Room;

import java.util.List;

import static org.s21.infrastructure.config.Config.RANDOM;

public class EntityGenerator {

    public static void spawn(GameSession session) {
        spawnEnemies(session);
        spawnItems(session);
    }


    private static void spawnEnemies(GameSession session) {
        Level level = session.getLevel();
        List<Room> rooms = level.getRooms();

        RandomEnumGenerator<EnemyType> enemyTypeGenerator = new RandomEnumGenerator<>(EnemyType.class);

        int countSpawned = 0;
        while (countSpawned < Config.BASE_COUNT_ENEMY_IN_DUNGEON) {
            EnemyType type = enemyTypeGenerator.randomEnum();
            Room room = rooms.get(RANDOM.nextInt(rooms.size() - 1));
            Coordinate coordinate = room.getRandomCoordinate();

            boolean cellIsFree = !level.getCellBy(coordinate).isOccupied();
            if (room.isStartRoom() || !cellIsFree) continue;

            Enemy enemy = EnemyFactory.createEnemy(type, coordinate, session.getCurrentLevel());
            level.addEntity(enemy);
            countSpawned++;
        }
    }

    private static void spawnItems(GameSession session) {
        Level level = session.getLevel();
        List<Room> rooms = level.getRooms();
        RandomEnumGenerator<ItemType> itemTypeGenerator = new RandomEnumGenerator<>(ItemType.class);

        int countSpawned = 0;
        while (countSpawned < Config.BASE_COUNT_ITEM_IN_DUNGEON) {
            ItemType type = itemTypeGenerator.randomEnum();
            Room room = rooms.get(RANDOM.nextInt(rooms.size() - 1));
            Coordinate coordinate = room.getRandomCoordinate();
            boolean cellIsFree = !level.getCellBy(coordinate).isOccupied() &&
                !(level.getCellBy(coordinate).getTerrarianType() == TerrarianType.EXIT);
            if (room.isStartRoom() || !cellIsFree) continue;
            Item item = ItemFactory.createItem(type, coordinate, session.getCurrentLevel());
            level.addEntity(item);
            countSpawned++;
        }
    }

}
