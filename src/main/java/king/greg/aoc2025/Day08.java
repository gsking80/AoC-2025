package king.greg.aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day08 {

  final int[][] junctionBoxes;

  public Day08(final List<String> lines) {
    junctionBoxes = new int[lines.size()][3];
    for (int i = 0; i < lines.size(); i++) {
      junctionBoxes[i] = Arrays.stream(lines.get(i).split(",")).mapToInt(Integer::parseInt)
          .toArray();
    }
  }

  public long circuitSizes(final int numberOfConnections) {
    return connectJunctions(numberOfConnections);
  }

  public int lastCord() {
    return connectJunctions(0);
  }

  private int connectJunctions(final int connectionLimit) {
    final int[] junctions = new int[junctionBoxes.length];

    final List<int[]> distances = new ArrayList<>();
    for (int i = 0; i < junctionBoxes.length - 1; i++) {
      for (int j = i + 1; j < junctionBoxes.length; j++) {
        int distance = distance(junctionBoxes[i], junctionBoxes[j]);
        distances.add(new int[]{i, j, distance});
      }
    }

    distances.sort((a, b) -> Integer.compare(a[2], b[2]));
    int junctionKey = 1;
    int unPaired = junctionBoxes.length;
    int circuits = 0;

    for (int i = 0; i < distances.size(); i++) {
      if (i >= connectionLimit && connectionLimit != 0) {
        break;
      }
      final int[] pairing = distances.get(i);
      if (junctions[pairing[0]] == 0 && junctions[pairing[1]] == 0) { // new junction
        junctions[pairing[0]] = junctionKey;
        junctions[pairing[1]] = junctionKey;
        junctionKey++;
        unPaired -= 2;
        circuits++;
      } else if (junctions[pairing[0]] == 0) { // add 0 into 1
        junctions[pairing[0]] = junctions[pairing[1]];
        unPaired--;
      } else if (junctions[pairing[1]] == 0) { // add 1 into 0
        junctions[pairing[1]] = junctions[pairing[0]];
        unPaired--;
      } else if (junctions[pairing[0]] != junctions[pairing[1]]) { // merge all 0s into 1;
        final int source = junctions[pairing[0]];
        final int destination = junctions[pairing[1]];
        for (int k = 0; k < junctions.length; k++) {
          if (junctions[k] == source) {
            junctions[k] = destination;
          }
        }
        circuits--;
      }
      if (connectionLimit == 0 && unPaired == 0 && circuits == 1) {
        return junctionBoxes[pairing[0]][0] * junctionBoxes[pairing[1]][0];
      }
    }

    final int[] circuitSizes = new int[junctionKey - 1];
    for (final int circuit : junctions) {
      if (circuit > 0) {
        circuitSizes[circuit - 1]++;
      }
    }
    Arrays.sort(circuitSizes);

    int circuitSizesMult = 1;
    for (int i = 0; i < 3; i++) {
      circuitSizesMult *= circuitSizes[circuitSizes.length - 1 - i];
    }

    return circuitSizesMult;
  }

  private int distance(final int[] junctionBox1, final int[] junctionBox2) {
    return (int) Math.sqrt(
        Math.pow((double) junctionBox1[0] - junctionBox2[0], 2)
            + Math.pow((double) junctionBox1[1] - junctionBox2[1], 2)
            + Math.pow((double) junctionBox1[2] - junctionBox2[2], 2));
  }
}
