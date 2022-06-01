package now.it.now.parser;

import static org.junit.Assert.assertEquals;

import now.It.now.exception.InitialPositionException;
import now.It.now.model.Direction;
import now.It.now.model.Position;
import now.It.now.parser.InitPositionParser;
import org.junit.Test;

public class InitPositionParserTest {

  InitPositionParser initPositionParser = new InitPositionParser();

  @Test(expected = InitialPositionException.class)
  public void Should_GetError_When_GivenInvalidInitPosition() {
    initPositionParser.getInitPosition("5 T T");
  }

  @Test
  public void Should_GetInitPosition_When_GivenValidInitPositionAsString() {
    // Given
    String initPositionAsString = "5 8 N";

    // When
    Position initPosition = initPositionParser.getInitPosition(initPositionAsString);

    // Then
    assertEquals(initPosition.getX(), 5);
    assertEquals(initPosition.getY(), 8);
    assertEquals(initPosition.getDirection(), Direction.NORTH);
  }
}
