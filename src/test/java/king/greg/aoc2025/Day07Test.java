package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.beamSplits()).isEqualTo(21);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.beamSplits()).isEqualTo(1504);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.timelines()).isEqualTo(40);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.timelines()).isEqualTo(5137133207830L);
  }
}