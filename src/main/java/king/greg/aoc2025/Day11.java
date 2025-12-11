package king.greg.aoc2025;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Day11 {

  private final Map<String, Long> pathsMap = new ConcurrentSkipListMap<>();
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
    return pathsMap.computeIfAbsent(start, k -> {
      if (start.equals(end)) {
        return 1L;
      }
      if (!cables.containsKey(start)) {
        return 0L;
      }
      long paths = 0;
      for (final var cable : cables.get(start)) {
        paths += paths(cable, end);
      }
      return paths;
    });
  }
}
