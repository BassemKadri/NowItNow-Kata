package now.It.now.parser;

import static org.apache.commons.lang3.StringUtils.containsOnly;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import now.It.now.exception.StepException;
import now.It.now.model.Step;

public class MowerStepsParser {

  /**
   * Parse the steps as string and convert it to list of steps.
   *
   * @param stepsAsString steps position as String.
   * @return List of steps
   */
  public List<Step> getSteps(String stepsAsString) {
    if (isValidStep(stepsAsString)) {
      List<Step> stps = new ArrayList<>();
      char[] steps = stepsAsString.toCharArray();
      for (char c : steps) {
        stps.add(Arrays.stream(Step.values())
            .filter(value -> value.getCode().equals(String.valueOf(c)))
            .findFirst()
            .orElseGet(null));
      }
      return stps;
    } else {
      throw StepException.builder().message("steps not valid").build();
    }
  }

  /**
   * Check if the steps as String matches the right format.
   *
   * @param steps steps as String
   * @return True/false
   */
  private boolean isValidStep(String steps) {
    return isNotBlank(steps) && containsOnly(steps,
        Step.ADVANCE.getCode() + Step.RIGHT.getCode() + Step.LEFT.getCode());
  }
}
