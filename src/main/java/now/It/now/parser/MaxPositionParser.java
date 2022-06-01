package now.It.now.parser;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import now.It.now.exception.MaxPositionException;
import now.It.now.model.MaxPosition;

public class MaxPositionParser {

  /**
   * Parse the init position as String and convert it to MaxPosition object.
   *
   * @param maxPosition max position as String.
   * @return max position as object
   */
  public MaxPosition getMaxPosition(String maxPosition) {
    if (isValidMaxPosition(maxPosition)) {
      return MaxPosition.builder()
          .x(Integer.parseInt(maxPosition.substring(0, 1)))
          .y(Integer.parseInt(maxPosition.substring(2, 3)))
          .build();
    } else {
      throw MaxPositionException.builder().message("Max position not valid").build();
    }
  }

  /**
   * Check if the max position as String matches the right format.
   *
   * @param maxPosition max position as String
   * @return True/false
   */
  private boolean isValidMaxPosition(String maxPosition) {
    return isNotBlank(maxPosition) && maxPosition.matches("^[0-9] [0-9]$");
  }
}
