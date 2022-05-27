package model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class Mower {

  private final Position initPosition;
  private final MaxPosition maxPosition;
  private final List<Step> steps;

  @Builder
  public Mower(final MaxPosition maxPosition, final Position initPosition, final List<Step> steps) {
    this.initPosition = initPosition;
    this.maxPosition = maxPosition;
    this.steps = steps;
  }

  public Position move() {
    for (Step step : steps) {
      moveStep(step);
    }
    return initPosition;
  }

  private void moveStep(final Step step) {
    switch (step) {
      case ADVANCE -> initPosition.advance(maxPosition);
      case RIGHT -> initPosition.moveToRight();
      case LEFT -> initPosition.moveToLeft();
    }
  }
}