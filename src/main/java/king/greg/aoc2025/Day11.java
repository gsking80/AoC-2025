package king.greg.aoc2025;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

  private final Map<String, Long> pathsMap = new HashMap<>();
  private final Map<String, List<String>> cables;

  public Day11(final List<String> lines) {
    cables = new HashMap<>();
    for (final var line : lines) {
      final var parts = line.split(": ");
      cables.put(parts[0], List.of(parts[1].split(" ")));
    }
  }

  public long pathsOut() {
    return paths("you", "out");
  }

  public long transformPathsOut() {
    final long svrToFft = paths("svr", "fft");
    pathsMap.clear();
    final long svrToDac = paths("svr", "dac");
    pathsMap.clear();
    final long FftToDac = paths("fft", "dac");
    pathsMap.clear();
    final long dacToFft = paths("dac", "fft");
    pathsMap.clear();
    final long fftToOut = paths("fft", "out");
    pathsMap.clear();
    final long dacToOut = paths("dac", "out");

    final long fftDacPaths = svrToFft * FftToDac * dacToOut;
    final long dacFftPaths = svrToDac * dacToFft * fftToOut;

    return fftDacPaths + dacFftPaths;
  }

  private long paths(final String start, final String end) {
    if (pathsMap.containsKey(start)) {
      return pathsMap.get(start);
    }
    if (start.equals(end)) {
      pathsMap.put(start, 1L);
      return 1L;
    }
    if (!cables.containsKey(start)) {
      pathsMap.put(start, 0L);
      return 0;
    }
    long paths = 0;
    for (final var cable : cables.get(start)) {
      paths += paths(cable, end);
    }
    pathsMap.put(start, paths);
    return paths;
  }
}
