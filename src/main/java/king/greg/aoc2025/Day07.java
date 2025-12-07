package king.greg.aoc2025;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day07 {

  final List<String> lines;
  final Map<Integer, Long> memo = new HashMap<>();

  public Day07(final List<String> lines) {
    this.lines = lines;
  }

  public int beamSplits() {
    int beamSplits = 0;
    var beams = new HashSet<Integer>();
    beams.add(lines.getFirst().indexOf('S'));
    for (int i = 1; i < lines.size(); i++) {
      final var splitters = findSplitters(lines.get(i));
      final var newBeams = new HashSet<Integer>();
      for (final var beam : beams) {
        if (splitters.contains(beam)) {
          beamSplits++;
          newBeams.add(beam - 1);
          newBeams.add(beam + 1);
        } else {
          newBeams.add(beam);
        }
      }
      beams = newBeams;
    }
    return beamSplits;
  }

  public long timelines() {
    var startBeam = new int[]{lines.getFirst().indexOf('S'), 0};
    return timelines(startBeam);
  }

  private long timelines(final int[] beam) {
    int beamKey = beam[0] << 16 | beam[1];
    if (memo.containsKey(beamKey)) {
      return memo.get(beamKey);
    }
    long timelines;
    if (beam[1] > lines.size() - 1) { // hit the bottom of the grid
      timelines = 1;
    } else if (lines.get(beam[1]).charAt(beam[0]) == '^') { // split!
      timelines = timelines(new int[]{beam[0] - 1, beam[1] + 1}) + timelines(
          new int[]{beam[0] + 1, beam[1] + 1});
    } else { // just keep traveling
      timelines = timelines(new int[]{beam[0], beam[1] + 1});
    }
    memo.put(beamKey, timelines);
    return timelines;
  }

  private Set<Integer> findSplitters(final String line) {
    final var splitters = new HashSet<Integer>();
    int index = line.indexOf('^');

    while (index != -1) {
      splitters.add(index);
      index = line.indexOf('^', index + 1);
    }
    return splitters;
  }
}
