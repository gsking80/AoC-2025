package king.greg.aoc2025;

import java.util.ArrayList;
import java.util.List;

public class Day05 {

  final List<long[]> freshRanges = new ArrayList<>();
  final List<Long> ingredients = new ArrayList<>();

  public Day05(java.util.List<String> lines) {
    boolean ingredientsRange = false;
    for (final var line : lines) {
      if (line.isBlank()) {
        ingredientsRange = true;
      } else if (ingredientsRange) {
        ingredients.add(Long.parseLong(line));
      } else {
        final var parts = line.split("-");
        freshRanges.add(new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])});
      }
    }
  }

  public int freshIngredientsCount() {
    int freshIngredientsCount = 0;
    for (final var ingredient : ingredients) {
      for (final var freshRange : freshRanges) {
        if (ingredient >= freshRange[0] && ingredient <= freshRange[1]) {
          freshIngredientsCount++;
          break;
        }
      }
    }
    return freshIngredientsCount;
  }

  public long totalFreshIngredients() {
    List<long[]> compositeFreshRanges = null;
    final List<long[]> tempFreshRanges = new ArrayList<>();
    while (!tempFreshRanges.equals(compositeFreshRanges)) {
      compositeFreshRanges =
          tempFreshRanges.isEmpty() ? freshRanges : new ArrayList<>(tempFreshRanges);
      tempFreshRanges.clear();
      for (final long[] freshRange : compositeFreshRanges) {
        boolean inserted = false;
        for (int j = 0; j < tempFreshRanges.size(); j++) {
          final var compositeFreshRange = tempFreshRanges.get(j);
          if (freshRange[0] <= compositeFreshRange[0]) { // starts before the range
            if (freshRange[1] < compositeFreshRange[0]) { // completely before the range
              tempFreshRanges.add(j, freshRange);
            } else {
              // expand left side
              compositeFreshRange[0] = freshRange[0];
              if (freshRange[1] > compositeFreshRange[1]) { // expand right side
                compositeFreshRange[1] = freshRange[1];
              }
            }
            inserted = true;
          } else if (freshRange[0] <= compositeFreshRange[1]) { // starts inside the range
            if (freshRange[1] > compositeFreshRange[1]) { // expand right
              compositeFreshRange[1] = freshRange[1];
            }
            inserted = true;
          }
          if (inserted) {
            break;
          }
        }
        if (!inserted) { // add to the end
          tempFreshRanges.add(freshRange);
        }
      }
    }
    long count = 0;
    for (final var range : compositeFreshRanges) {
      count += range[1] - range[0] + 1;
    }
    return count;
  }
}
