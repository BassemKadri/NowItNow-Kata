package model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MaxPosition {

  private final int x;
  private final int y;

  public MaxPosition(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
}
