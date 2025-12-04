package king.greg.aoc2025;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 {

  final Set<Point> rolls;

  public Day04(final List<String> lines) {
    rolls = new HashSet<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        if (lines.get(y).charAt(x) == '@') {
          rolls.add(new Point(x, y));
        }
      }
    }
  }

  public int accessibleRollsCount() {
    return accessibleRolls().size();
  }

  public int eventuallyAccessibleRollsCount() {
    int count = 0;
    while (true) {
      final Set<Point> accessible = accessibleRolls();
      if (accessible.isEmpty()) {
        return count;
      }
      count += accessible.size();
      rolls.removeAll(accessible);
    }
  }

  private Set<Point> accessibleRolls() {
    final Set<Point> accessible = new HashSet<>();
    for (final var roll : rolls) {
      int adjacent = -1;
      for (int x = roll.x - 1; x <= roll.x + 1; x++) {
        for (int y = roll.y - 1; y <= roll.y + 1; y++) {
          if (rolls.contains(new Point(x, y))) {
            adjacent++;
          }
        }
      }
      if (adjacent < 4) {
        accessible.add(roll);
      }
    }
    return accessible;
  }
}
