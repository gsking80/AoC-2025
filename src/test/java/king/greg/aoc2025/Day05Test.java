package king.greg.aoc2025;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/sample1.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.freshIngredientsCount()).isEqualTo(3);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.freshIngredientsCount()).isEqualTo(733);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/sample1.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.totalFreshIngredients()).isEqualTo(14);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.totalFreshIngredients()).isEqualTo(345821388687084L);
  }
}