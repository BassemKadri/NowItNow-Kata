package now.it.now.parser;

import static org.junit.Assert.assertEquals;

import now.It.now.exception.MaxPositionException;
import now.It.now.model.MaxPosition;
import now.It.now.parser.MaxPositionParser;
import org.junit.Test;

public class MaxPositionParserTest {

  MaxPositionParser maxPositionParser = new MaxPositionParser();

  @Test(expected = MaxPositionException.class)
  public void Should_GetError_When_GivenInvalidMaxPosition() {
    maxPositionParser.getMaxPosition("T 8");
  }

  @Test
  public void Should_GetMaxPosition_When_GivenValidMaxPositionAsString() {
    // Given
    String maxPositionAsString = "5 8";

    // When
    MaxPosition maxPosition = maxPositionParser.getMaxPosition(maxPositionAsString);

    // Then
    assertEquals(maxPosition.getX(), 5);
    assertEquals(maxPosition.getY(), 8);
  }
}
