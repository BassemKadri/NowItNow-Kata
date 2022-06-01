package now.It.now.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Position {

  private int x;
  private int y;
  private Direction direction;

  public Position(final int x, final int y, final Direction direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }

  public void moveToRight() {
    direction = direction.getRight();
  }

  public void moveToLeft() {
    direction = direction.getLeft();
  }

  public void advance(final MaxPosition maxPosition) {
    switch (direction) {
      case NORTH:
        if (y < maxPosition.getY()) {
          y += 1;
        }
        break;
      case SOUTH:
        if (y > 0) {
          y -= 1;
        }
        break;
      case EAST:
        if (x < maxPosition.getX()) {
          x += 1;
        }
        break;
      case WEST:
        if (x > 0) {
          x -= 1;
        }
        break;
    }
  }

  @Override
  public String toString() {
    return x + " " + y + " " + direction;
  }

}
