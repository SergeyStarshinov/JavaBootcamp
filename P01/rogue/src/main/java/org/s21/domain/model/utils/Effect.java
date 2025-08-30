package org.s21.domain.model.utils;

import org.s21.domain.model.items.consumable.elixirs.Elixir;

public class Effect {
  private final Elixir elixir;
  private int timeLeft;

  public Effect(Elixir elixir, int timeLeft) {
    this.elixir = elixir;
    this.timeLeft = timeLeft;
  }

  public Elixir getElixir() {
    return elixir;
  }

  public int getTimeLeft() {
    return timeLeft;
  }

  public void decreaseTime() {
    timeLeft--;
  }
}
