package now.It.now.parser;

import java.util.ArrayList;
import java.util.List;
import now.It.now.exception.FileContentException;
import now.It.now.model.MaxPosition;
import now.It.now.model.Mower;
import now.It.now.model.Position;
import now.It.now.model.Step;

public class MowerParser {

  final InitPositionParser initPositionParser = new InitPositionParser();
  final MaxPositionParser maxPositionParser = new MaxPositionParser();
  final MowerStepsParser mowerStepsParser = new MowerStepsParser();

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

    MaxPosition maxPosition = maxPositionParser.getMaxPosition(fileLines.get(0));

    for (int i = 1; i < fileLines.size(); i = i + 2) {
      Position initPosition = initPositionParser.getInitPosition(fileLines.get(i));
      List<Step> steps = mowerStepsParser.getSteps(fileLines.get(i + 1));
      Mower mower = Mower.builder()
          .initPosition(initPosition)
          .maxPosition(maxPosition)
          .steps(steps)
          .build();
      mowers.add(mower);
    }
    return mowers;
  }
}
