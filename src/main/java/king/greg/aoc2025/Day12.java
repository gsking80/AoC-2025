package king.greg.aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Day12 {

  final Present[] presents;
  final List<Pair<String, int[]>> trees;

  public Day12(final List<String> lines) {
    presents = new Present[6];
    for (int i = 0; i < 6; i++) {
      int presentIndex = (5 * i) + 1;
      presents[i] = new Present(lines.get(presentIndex), lines.get(presentIndex + 1),
          lines.get(presentIndex + 2));
    }

    trees = new ArrayList<>();
    for (int i = 30; i < lines.size(); i++) {
      final var pieces = lines.get(i).split(": ");
      trees.add(Pair.of(pieces[0],
          Arrays.stream(pieces[1].split(" ")).mapToInt(Integer::parseInt).toArray()));
    }
  }

  public int presentsThatFit() {
    int fits = 0;
    final List<Pair<String, int[]>> mightFit = new ArrayList<>();
    for (final var tree : trees) {
      final var sizeArray = Arrays.stream(tree.getLeft().split("x")).mapToInt(Integer::parseInt)
          .toArray();
      // First weed out any trees that can't fit any presents no matter how they're arranged
      final int maxSize = sizeArray[0] * sizeArray[1];
      int packageEstate = 0;
      for (int i = 0; i < tree.getRight().length; i++) {
        packageEstate += presents[i].minSize * tree.getRight()[i];
      }
      if (packageEstate > maxSize) {
        continue;
      }
      // Now find the trees with more than enough room for all the presents
      final int roughSize = (sizeArray[0] / 3) * (sizeArray[1] / 3);
      final int totalPresents = Arrays.stream(tree.getRight()).sum();
      if (roughSize >= totalPresents) {
        fits++;
      } else {
        mightFit.add(tree);
      }
    }
    if (mightFit.isEmpty()) { // There is a chance that this problem is easier than it looks
      return fits;
    }
    throw new RuntimeException("Still have work to do");
  }

  static class Present {

    final int minSize;

    Present(final String line1, final String line2, final String line3) {
      minSize = (int) (line1.chars().filter(a -> a == '#').count() + line2.chars()
          .filter(a -> a == '#').count() + line3.chars().filter(a -> a == '#').count());
    }
  }
}
