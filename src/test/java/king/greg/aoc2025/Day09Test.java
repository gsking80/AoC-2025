package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day09Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.largestRectangle()).isEqualTo(50);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.largestRectangle()).isEqualTo(4729332959L);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.largestRedGreentangle()).isEqualTo(24);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.largestRedGreentangle()).isEqualTo(1474477524L);
  }
}