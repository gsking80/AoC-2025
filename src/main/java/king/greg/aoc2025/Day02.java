package king.greg.aoc2025;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day02 {

  final List<Pair<Long, Long>> ranges = new ArrayList<>();

  public Day02(java.util.List<String> lines) {
    for (final String line : lines) {
      final var splits = line.split(",");
      for (final String split : splits) {
        final var range = split.split("-");
        ranges.add(Pair.of(Long.parseLong(range[0]), Long.parseLong(range[1])));
      }
    }
  }

  private static boolean isValid(final String id, final int parts) {
    if (id.length() % parts != 0) {
      return true;
    }
    var testLength = id.length() / parts;
    int i = testLength;
    var testString = id.substring(0, testLength);
    var validTest = false;
    while (!validTest && i < id.length()) {
      if (!testString.equals(id.substring(i, i + testLength))) {
        validTest = true;
      }
      i += testLength;
    }
    return validTest;
  }

  long invalidSum() {
    final Set<Long> invalidIds = new HashSet<>();
    for (final var range : ranges) {
      for (long i = range.getLeft(); i <= range.getRight(); i++) {
        final String id = String.valueOf(i);
        if (!isValid(id, 2)) {
          invalidIds.add(i);
        }
      }
    }
    return invalidIds.stream().mapToLong(Long::longValue).sum();
  }

  long invalidSum2() {
    final Set<Long> invalidIds = new HashSet<>();
    for (final var range : ranges) {
      for (long i = range.getLeft(); i <= range.getRight(); i++) {
        final String id = String.valueOf(i);
        var valid = true;
        int parts = 2;
        while (valid && parts <= id.length()) {
          valid = isValid(id, parts);
          parts++;
        }
        if (!valid) {
          invalidIds.add(i);
        }
      }
    }
    return invalidIds.stream().mapToLong(Long::longValue).sum();
  }
}
