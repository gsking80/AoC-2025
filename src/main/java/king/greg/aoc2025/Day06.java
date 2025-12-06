package king.greg.aoc2025;

import java.util.ArrayList;
import java.util.List;

public class Day06 {

  final List<String> lines;

  public Day06(final List<String> lines) {
    this.lines = lines;
  }

  public long sumAnswers() {
    String[] operators = lines.getLast().trim().split("\\s+");
    long[] values = new long[operators.length];
    for (int i = 0; i < operators.length; i++) {
      values[i] = operators[i].equals("+") ? 0 : 1;
    }
    for (int i = 0; i < lines.size() - 1; i++) {
      var numbers = lines.get(i).trim().split("\\s+");
      for (int j = 0; j < numbers.length; j++) {
        if (operators[j].equals("+")) {
          values[j] += Long.parseLong(numbers[j]);
        } else {
          values[j] *= Long.parseLong(numbers[j]);
        }
      }
    }
    long sum = 0;
    for (long value : values) {
      sum += value;
    }
    return sum;
  }

  public long sumCephalopodAnswers() {

    char[][] grid = lines.stream().map(String::toCharArray).toArray(char[][]::new);
    int operandRow = grid.length - 1;

    long sum = 0;

    int index = grid[0].length - 1;
    while (index >= 0) {

      final List<Long> values = new ArrayList<>();
      char operand = ' ';
      while (operand == ' ') {
        final StringBuilder sb = new StringBuilder();
        for (int row = 0; row < operandRow; row++) {
          sb.append(grid[row][index]);
        }
        var value = sb.toString();
        if (!value.isBlank()) {
          values.add(Long.parseLong(value.trim()));
        }
        operand = grid[operandRow][index];
        index--;
      }
      if (operand == '+') {
        sum += values.stream().reduce(0L, Long::sum);
      } else {
        sum += values.stream().reduce(1L, (a, b) -> a * b);
      }
    }
    return sum;
  }
}
