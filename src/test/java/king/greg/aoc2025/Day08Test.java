package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample1.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.circuitSizes(10)).isEqualTo(40);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.circuitSizes(1000)).isEqualTo(83520);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample1.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.lastCord()).isEqualTo(25272);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.lastCord()).isEqualTo(1131823407);
  }
}