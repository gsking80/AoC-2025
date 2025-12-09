package king.greg.aoc2025;

import java.util.Arrays;
import java.util.List;

public class Day09 {

  final int[][] tiles;

  public Day09(final List<String> lines) {
    tiles = lines.stream()
        .map(line -> Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray())
        .toArray(int[][]::new);
  }

  public long largestRectangle() {
    long largest = 0;
    for (int i = 0; i < tiles.length - 1; i++) {
      for (int j = i + 1; j < tiles.length; j++) {
        final long size = (long) (Math.abs(tiles[i][0] - tiles[j][0]) + 1) * (long) (
            Math.abs(tiles[i][1] - tiles[j][1]) + 1);
        if (size > largest) {
          largest = size;
        }
      }
    }
    return largest;
  }

  public long largestRedGreentangle() {
    long largestRedGreen = 0;
    for (int i = 0; i < tiles.length - 1; i++) {
      for (int j = i + 1; j < tiles.length; j++) {
        final long size = (long) (Math.abs(tiles[i][0] - tiles[j][0]) + 1) * (long) (
            Math.abs(tiles[i][1] - tiles[j][1]) + 1);
        if (size > largestRedGreen && valid(tiles[i], tiles[j])) {
          largestRedGreen = size;
        }
      }
    }
    return largestRedGreen;
  }

  private boolean valid(final int[] cornerA, final int[] cornerB) {
    var minX = Math.min(cornerA[0], cornerB[0]);
    var maxX = Math.max(cornerA[0], cornerB[0]);
    var minY = Math.min(cornerA[1], cornerB[1]);
    var maxY = Math.max(cornerA[1], cornerB[1]);

    for (int i = 0; i < tiles.length; i++) {
      var tileA = tiles[i];
      var tileB = tiles[(i + 1) % tiles.length];
      if (!(Math.max(tileA[0], tileB[0]) <= minX || Math.min(tileA[0], tileB[0]) >= maxX
          || Math.max(tileA[1], tileB[1]) <= minY || Math.min(tileA[1], tileB[1]) >= maxY)) {
        return false;
      }
    }
    return true;
  }
}
