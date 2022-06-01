package now.it.now;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import now.It.now.Main;
import now.It.now.model.Direction;
import now.It.now.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MainIT {

  Main main = new Main();

  @Test
  public void contextLoads() {
  }

  @Test
  public void Should_GetFinalPositions_When_GivenFileContent() throws IOException {

    // When & Then
    List<Position> finalPositions = main.execute(
        getClass().getClassLoader().getResource("integration-test.txt").getPath());

    // Then
    Position firstFinalPosition = Position.builder().x(1).y(3).direction(Direction.NORTH).build();
    Position secondFinalPosition = Position.builder().x(5).y(1).direction(Direction.EAST).build();

    assertEquals(finalPositions.size(), 2);
    assertEquals(finalPositions, Arrays.asList(firstFinalPosition, secondFinalPosition));

  }
}
