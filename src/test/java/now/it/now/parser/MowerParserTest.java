package now.it.now.parser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import now.It.now.model.Direction;
import now.It.now.model.MaxPosition;
import now.It.now.model.Mower;
import now.It.now.model.Position;
import now.It.now.model.Step;
import now.It.now.parser.MowerParser;
import org.junit.Test;

public class MowerParserTest {

  MowerParser mowerParser = new MowerParser();

  @Test
  public void Should_GetMowers_When_GivenValidInput() {
    // Given
    List<String> fileContent = Arrays.asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");

    // When
    List<Mower> mowers = mowerParser.getMowers(fileContent);

    // Then
    Mower firstMower = Mower.builder()
        .maxPosition(MaxPosition.builder().x(5).y(5).build())
        .initPosition(Position.builder().x(1).y(2).direction(Direction.NORTH).build())
        .steps(Arrays
            .asList(Step.LEFT, Step.ADVANCE, Step.LEFT, Step.ADVANCE, Step.LEFT, Step.ADVANCE,
                Step.LEFT, Step.ADVANCE, Step.ADVANCE))
        .build();

    Mower secondMower = Mower.builder()
        .maxPosition(MaxPosition.builder().x(5).y(5).build())
        .initPosition(Position.builder().x(3).y(3).direction(Direction.EAST).build())
        .steps(Arrays
            .asList(Step.ADVANCE, Step.ADVANCE, Step.RIGHT, Step.ADVANCE, Step.ADVANCE, Step.RIGHT,
                Step.ADVANCE, Step.RIGHT, Step.RIGHT, Step.ADVANCE))
        .build();

    assertEquals(mowers.size(), 2);
    assertEquals(mowers, Arrays.asList(firstMower, secondMower));
  }
}
