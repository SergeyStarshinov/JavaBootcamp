package org.s21.ui.actions;

import org.s21.application.usecase.movement.MoveHero;
import org.s21.domain.model.entity.creature.Hero;
import org.s21.domain.model.items.AttributeType;
import org.s21.domain.model.items.consumable.elixirs.Elixir;
import org.s21.domain.model.utils.Cell;
import org.s21.domain.model.utils.Coordinate;
import org.s21.domain.model.utils.Direction;
import org.s21.domain.model.utils.Effect;
import org.s21.domain.model.world.GameSession;

import java.util.List;
import java.util.Map;

public class MoveUserAction extends UserAction{
  private final Direction direction;

  public MoveUserAction(Direction direction) {
    this.direction = direction;
  }

  @Override
  public Map<Coordinate, Cell> doAction(GameSession gameSession) {
    Map<Coordinate, Cell> updateMap = MoveHero.moveHero(gameSession, direction);
    handleEffects(gameSession.getHero());
    return updateMap;
  }

  private void handleEffects(Hero hero) {
    List<Effect> effectsToRemove = hero.decreaseEffectsTime();
    for (Effect effect : effectsToRemove) {
      Elixir elixir = effect.getElixir();
      switch (elixir.getAttribute()) {
        case AttributeType.AGILITY ->
            hero.getStats().setAgility(hero.getStats().getAgility() - elixir.getPower());
        case AttributeType.MAX_HEALTH ->
        {
          hero.getStats().setMaxHealth(hero.getStats().getMaxHealth() - elixir.getPower());
          int health = hero.getStats().getHealth() - elixir.getPower();
          hero.getStats().setHealth(health <= 0? 1 : health);
        }
        case AttributeType.STRENGTH ->
            hero.getStats().setStrength(hero.getStats().getStrength() - elixir.getPower());
      }
      hero.removeEffect(effect);
    }
  }
}
