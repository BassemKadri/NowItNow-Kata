package model;

import java.util.Arrays;

public enum Direction {
  WEST("W", "S", "N"),
  EAST("E", "N", "S"),
  NORTH("N", "W", "E"),
  SOUTH("S", "E", "W");


  private final String code;
  private final String right;
  private final String left;


  Direction(final String code, final String left, final String right) {
    this.code = code;
    this.left = left;
    this.right = right;
  }

  public String getCode() {
    return this.code;
  }

  public Direction getLeft() {
    return getDirectionFromCode(left);
  }

  public Direction getRight() {
    return getDirectionFromCode(right);
  }

  public static Direction getDirectionFromCode(final String code) {
    return Arrays.stream(Direction.values())
        .filter(value -> value.getCode().equals(code))
        .findFirst()
        .orElseGet(null);
  }

  @Override
  public String toString() {
    return this.code;
  }
}
