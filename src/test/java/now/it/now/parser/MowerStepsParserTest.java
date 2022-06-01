package now.it.now.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import now.It.now.exception.StepException;
import now.It.now.model.Step;
import now.It.now.parser.MowerStepsParser;
import org.junit.Test;

public class MowerStepsParserTest {

  MowerStepsParser mowerStepsParser = new MowerStepsParser();

  @Test(expected = StepException.class)
  public void Should_GetError_When_GivenInvalidSteps() {
    mowerStepsParser.getSteps("GAAGAS");
  }

  @Test
  public void Should_GetSteps_When_GivenValidStepsAsString() {
    // Given
    String stepsAsString = "GAGD";

    // When
    List<Step> steps = mowerStepsParser.getSteps(stepsAsString);

    // Then
    assertEquals(steps.size(), 4);
    assertEquals(steps, Arrays.asList(Step.LEFT, Step.ADVANCE, Step.LEFT, Step.RIGHT));
  }

}
