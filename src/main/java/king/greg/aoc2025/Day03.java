package king.greg.aoc2025;

import java.util.List;

public class Day03 {

  private final List<String> lines;

  public Day03(java.util.List<String> lines) {
    this.lines = lines;
  }

  long maxJoltage() {
    return maxJoltage(2);
  }

  long maxJoltage(final int batteries) {
    long max = 0;
    for (final var line : lines) {
      max += joltage(line, batteries);
    }

    return max;
  }

  private long joltage(final String ratingString, final int batteries) {
    final var ratings = ratingString.toCharArray();
    return joltage(ratings, 0, batteries);
  }

  private long joltage(final char[] ratings, final int start, final int batteries) {
    char max = '0';
    int index = start;
    for (int i = start; i <= ratings.length - batteries; i++) {
      if (ratings[i] > max) {
        max = ratings[i];
        index = i;
      }
    }
    long joltageToAdd = batteries == 1 ? 0 : joltage(ratings, index + 1, batteries - 1);
    return (long) (Integer.parseInt(String.valueOf(max)) * Math.pow(10, (double) batteries - 1))
        + joltageToAdd;
  }
}
