package now.It.now.parser;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import now.It.now.exception.InitialPositionException;
import now.It.now.model.Direction;
import now.It.now.model.Position;

public class InitPositionParser {

  /**
   * Parse the init position as String and convert it to Position object.
   *
   * @param initPosition init position as String.
   * @return init position as object
   */
  public Position getInitPosition(String initPosition) {
    if (isValidInitPosition(initPosition)) {
      return Position.builder()
          .x(Integer.parseInt(initPosition.substring(0, 1)))
          .y(Integer.parseInt(initPosition.substring(2, 3)))
          .direction(Direction.getDirectionFromCode(initPosition.substring(4, 5)))
          .build();
    } else {
      throw InitialPositionException.builder().message("Initial position not valid").build();
    }
  }

  /**
   * Check if the init position as String matches the right format.
   *
   * @param initPosition init position as String
   * @return True/false
   */
  private boolean isValidInitPosition(String initPosition) {
    return isNotBlank(initPosition) && initPosition.matches("^[0-9] [0-9] (N|S|W|E)$");
  }
}
