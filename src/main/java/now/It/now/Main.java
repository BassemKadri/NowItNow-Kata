package now.It.now;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import now.It.now.model.Mower;
import now.It.now.model.Position;
import now.It.now.parser.MowerParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class Main implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }


  @Override
  public void run(String... args) throws IOException {
    List<Position> finalPositions = execute(args[0]);

    log.info(" Mower final position : " + StringUtils.join(finalPositions, " "));
  }

  /**
   * Start moving the Mowers.
   *
   * @param file file as input.
   * @return list of final position.
   */
  public List<Position> execute(String file) throws IOException {
    List<String> lines = Files.readAllLines(Paths.get(file));
    MowerParser mowerParser = new MowerParser();
    List<Mower> movements = mowerParser.getMowers(lines);
    return movements.stream()
        .map(Mower::move)
        .collect(Collectors.toList());
  }
}
