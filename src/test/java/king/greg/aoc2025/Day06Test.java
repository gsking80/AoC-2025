package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.sumAnswers()).isEqualTo(4277556);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.sumAnswers()).isEqualTo(4951502530386L);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.sumCephalopodAnswers()).isEqualTo(3263827);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.sumCephalopodAnswers()).isEqualTo(8486156119946L);
  }
}