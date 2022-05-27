import static org.junit.Assert.assertEquals;

import model.Direction;
import model.MaxPosition;
import model.Mower;
import model.Position;
import model.Step;
import exception.InitialPositionException;
import exception.MaxPositionException;
import exception.StepException;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MainTest {

  Main main = new Main();

  @Test(expected = InitialPositionException.class)
  public void Should_GetError_When_GivenInvalidInitPosition() {
    main.getInitPosition("5 T T");
  }

  @Test
  public void Should_GetInitPosition_When_GivenValidInitPositionAsString() {
    // Given
    String initPositionAsString = "5 8 N";

    // When
    Position initPosition = main.getInitPosition(initPositionAsString);

    // Then
    assertEquals(initPosition.getX(), 5);
    assertEquals(initPosition.getY(), 8);
    assertEquals(initPosition.getDirection(), Direction.NORTH);
  }

  @Test(expected = MaxPositionException.class)
  public void Should_GetError_When_GivenInvalidMaxPosition() {
    main.getMaxPosition("T 8");
  }

  @Test
  public void Should_GetMaxPosition_When_GivenValidMaxPositionAsString() {
    // Given
    String maxPositionAsString = "5 8";

    // When
    MaxPosition maxPosition = main.getMaxPosition(maxPositionAsString);

    // Then
    assertEquals(maxPosition.getX(), 5);
    assertEquals(maxPosition.getY(), 8);
  }

  @Test(expected = StepException.class)
  public void Should_GetError_When_GivenInvalidSteps() {
    main.getSteps("GAAGAS");
  }

  @Test
  public void Should_GetSteps_When_GivenValidStepsAsString() {
    // Given
    String stepsAsString = "GAGD";

    // When
    List<Step> steps = main.getSteps(stepsAsString);

    // Then
    assertEquals(steps.size(), 4);
    assertEquals(steps, Arrays.asList(Step.LEFT, Step.ADVANCE, Step.LEFT, Step.RIGHT));
  }

  @Test
  public void Should_GetMowers_When_GivenValidInput() {
    // Given
    List<String> fileContent = Arrays.asList("5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA");

    // When
    List<Mower> mowers = main.getMowers(fileContent);

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

  @Test
  public void Should_GetFinalPositions_When_GivenValidMowers() {
    //Given
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

    // When
    List<Position> finalPositions = main.run(Arrays.asList(firstMower, secondMower));

    // Then
    Position firstFinalPosition = Position.builder().x(1).y(3).direction(Direction.NORTH).build();
    Position secondFinalPosition = Position.builder().x(5).y(1).direction(Direction.EAST).build();

    assertEquals(finalPositions.size(), 2);
    assertEquals(finalPositions, Arrays.asList(firstFinalPosition, secondFinalPosition));
  }

}
