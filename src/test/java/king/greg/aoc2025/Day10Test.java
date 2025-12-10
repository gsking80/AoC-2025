package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day10Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/sample1.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines);
    Assertions.assertThat(day10.fewestPressesToInitialize()).isEqualTo(7);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/input.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines);
    Assertions.assertThat(day10.fewestPressesToInitialize()).isEqualTo(502);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/sample1.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines);
    Assertions.assertThat(day10.fewestPressesToJoltage()).isEqualTo(33);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day10/input.txt"))
            .toURI()));
    final Day10 day10 = new Day10(lines);
    Assertions.assertThat(day10.fewestPressesToJoltage()).isEqualTo(21467);
  }
}