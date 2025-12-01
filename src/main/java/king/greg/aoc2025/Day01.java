package king.greg.aoc2025;

import java.util.List;

public record Day01(List<String> lines) {

  public long password() {
    long password = 0;
    int location = 50;
    for (final String line : lines) {
      int distance = Integer.parseInt(line.substring(1)) % 100;
      if (line.charAt(0) == 'R') {
        location += distance;
      } else {
        location += 100 - distance;
      }
      location = location % 100;
      if (location == 0) {
        password++;
      }
    }
    return password;
  }

  public long password2() {
    long password = 0;
    int location = 50;
    for (final String line : lines) {
      int distance = Integer.parseInt(line.substring(1));
      int extras = distance / 100;
      password += extras;
      distance = distance % 100;
      if (line.charAt(0) == 'R') {
        location += distance;
        if (location > 100) {
          password++;
        }
      } else {
        location = (location == 0) ? 100 : location;
        location -= distance;
        if (location < 0) {
          password++;
        }
        location += 100;
      }
      location = location % 100;
      if (location == 0) {
        password++;
      }
    }
    return password;
  }
}
