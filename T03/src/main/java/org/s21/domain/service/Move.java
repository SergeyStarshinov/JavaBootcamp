package org.s21.domain.service;

import org.s21.domain.model.Coordinate;

public class Move {
  private final Coordinate coordinate;
  private final int score;

  public Move(Coordinate coordinate, int score) {
    this.coordinate = coordinate;
    this.score = score;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public int getScore() {
    return score;
  }

  @Override
  public String toString() {
    return "Move{" +
        "coordinate=" + coordinate +
        ", score=" + score +
        '}';
  }
}
