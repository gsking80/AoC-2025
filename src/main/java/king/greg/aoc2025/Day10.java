package king.greg.aoc2025;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.IntNum;
import com.microsoft.z3.Model;
import com.microsoft.z3.Optimize;
import com.microsoft.z3.Status;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Day10 {

  final List<Machine> machines = new ArrayList<>();

  public Day10(final List<String> lines) {
    lines.stream().map(Machine::new).forEach(machines::add);
  }

  public int fewestPressesToInitialize() {
    int presses = 0;
    for (final var machine : machines) {
      presses += machine.initialize();
    }
    return presses;
  }

  public int fewestPressesToJoltage() {
    int presses = 0;
    for (final var machine : machines) {
      presses += machine.configureJoltage();
    }
    return presses;
  }

  static class Machine {

    final List<Integer> buttonMasks = new ArrayList<>();
    final List<int[]> buttons = new ArrayList<>();
    int expectedLights;
    ArrayList<Integer> joltages;

    public Machine(final String config) {
      final var parts = config.split(" ");
      for (final var part : parts) {
        final var partArray = part.toCharArray();
        switch (partArray[0]) {
          case '[': // expectedLights
            for (int i = partArray.length - 1; i > 0; i--) {
              expectedLights = expectedLights << 1;
              if (partArray[i] == '#') {
                expectedLights++;
              }
            }
            break;
          case '(': // button
            final int[] buttonArray = Arrays.stream(part.substring(1, part.length() - 1).split(","))
                .mapToInt(Integer::parseInt).toArray();
            int buttonMask = 0;
            for (final var buttonPart : buttonArray) {
              buttonMask += (int) Math.pow(2, buttonPart);
            }
            buttonMasks.add(buttonMask);
            buttons.add(buttonArray);
            break;
          case '{':  // joltage - do nothing for now
            joltages = Arrays.stream(part.substring(1, part.length() - 1).split(","))
                .mapToInt(Integer::parseInt).boxed()
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            break;
          default:
            break;
        }
      }
    }

    public int initialize() {
      final Map<Integer, Integer> seenLights = new HashMap<>();
      final Queue<Integer> states = new ArrayDeque<>();
      seenLights.put(0, 0);
      states.add(0);

      while (!states.isEmpty()) {
        final int state = states.poll();
        final var presses = seenLights.get(state);
        if (state == expectedLights) {
          return presses;
        }
        for (final var buttonMask : buttonMasks) {
          final int newState = state ^ buttonMask;
          if (!seenLights.containsKey(newState)) {
            seenLights.put(newState, presses + 1);
            states.add(newState);
          }
        }
      }

      throw new RuntimeException("Couldn't initialize lights");
    }

    public int configureJoltage() {
      final Context ctx = new Context();
      Optimize opt = ctx.mkOptimize();
      IntExpr presses = ctx.mkIntConst("presses");

      final List<List<IntExpr>> joltageButtons = new ArrayList<>();
      for (int i = 0; i < joltages.size(); i++) {
        joltageButtons.add(new ArrayList<>());
      }

      IntExpr zero = ctx.mkInt(0);
      IntExpr[] buttonNames = new IntExpr[buttons.size()];
      for (int i = 0; i < buttons.size(); i++) {
        final var buttonName = ctx.mkIntConst("button" + i);
        BoolExpr noNegativePresses = ctx.mkGe(buttonName, zero);
        opt.Add(noNegativePresses);
        buttonNames[i] = buttonName;
        for (final var button : buttons.get(i)) {
          joltageButtons.get(button).add(buttonName);
        }
      }

      for (int i = 0; i < joltages.size(); i++) {
        final IntExpr[] relevantPresses = joltageButtons.get(i).toArray(IntExpr[]::new);
        final IntExpr sumRelevantPresses = (IntExpr) ctx.mkAdd(relevantPresses);
        final IntExpr joltageTarget = ctx.mkInt(joltages.get(i));
        final BoolExpr equation = ctx.mkEq(joltageTarget, sumRelevantPresses);
        opt.Add(equation);
      }

      final IntExpr sumPresses = (IntExpr) ctx.mkAdd(buttonNames);
      final BoolExpr equation = ctx.mkEq(presses, sumPresses);
      opt.Add(equation);

      opt.MkMinimize(presses);

      Status status = opt.Check();
      if (status == Status.SATISFIABLE) {
        Model model = opt.getModel();
        IntNum outputValue = (IntNum) model.evaluate(presses, false);
        return outputValue.getInt();
      }

      throw new RuntimeException("Couldn't configure joltage");
    }
  }
}
