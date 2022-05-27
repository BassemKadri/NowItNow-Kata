import static org.apache.commons.lang3.StringUtils.containsOnly;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import exception.StepException;
import exception.FileContentException;
import exception.MaxPositionException;
import exception.InitialPositionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import model.Step;
import model.Direction;
import model.MaxPosition;
import model.Mower;
import model.Position;
import org.apache.commons.lang3.StringUtils;


@Slf4j
public class Main {

  public static void main(final String[] args) throws IOException {

    Main instance = new Main();
    List<String> lines = Files.readAllLines(Paths.get(args[0]));
    List<Mower> movements = instance.getMowers(lines);
    List<Position> finalPositions = instance.run(movements);

    log.info(" Mower final position : " + StringUtils.join(finalPositions, " "));
  }

  /**
   * Transform the content of the file as input to list of Mowers.
   *
   * @param fileLines lines in the file.
   * @return List of Mowers.
   */
  public List<Mower> getMowers(final List<String> fileLines) {
    List<Mower> mowers = new ArrayList<>();
    if (fileLines.isEmpty()) {
      throw FileContentException.builder().message("Check content of file!").build();
    }

    MaxPosition maxPosition = getMaxPosition(fileLines.get(0));

    for (int i = 1; i < fileLines.size(); i = i + 2) {
      Position initPosition = getInitPosition(fileLines.get(i));
      List<Step> steps = getSteps(fileLines.get(i + 1));
      Mower mower = Mower.builder()
          .initPosition(initPosition)
          .maxPosition(maxPosition)
          .steps(steps)
          .build();
      mowers.add(mower);
    }
    return mowers;
  }

  /**
   * Convert the init position as String to Position object.
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

  /**
   * Convert the max position as String to MaxPosition object.
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


  /**
   * Convert the steps to list of steps.
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

  /**
   * Start moving the Mowers.
   *
   * @param mowers list of mowers.
   * @return list of final position.
   */
  public List<Position> run(List<Mower> mowers) {
    return mowers.stream()
        .map(Mower::move)
        .collect(Collectors.toList());
  }
}
