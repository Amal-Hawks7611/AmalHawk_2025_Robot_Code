package frc.robot.commands.Led;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Constants.OI;
import frc.robot.subsystems.StatusLED;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LEDMorseScroller extends Command {
  private final StatusLED statusLED;
  private final Timer timer = new Timer();
  private final int ledCount;
  private final double scrollInterval = 0.5;
  private double lastShiftTime = 0.0;
  private int scrollOffset = 0;
  private final boolean[] paddedPattern;
  private final Color backgroundColor = Color.kDarkBlue;
  private final Color symbolColor = Color.kDarkRed;

  public LEDMorseScroller(StatusLED statusLED, int ledCount, String message) {
    this.statusLED = statusLED;
    this.ledCount = ledCount;
    boolean[] rawPattern = createPattern(message);
    paddedPattern = addPadding(rawPattern, ledCount);
    addRequirements(statusLED);
  }

  private boolean[] createPattern(String message) {
    Map<Character, String> morseMap = new HashMap<>();
    morseMap.put('A', ".-");
    morseMap.put('B', "-...");
    morseMap.put('C', "-.-.");
    morseMap.put('D', "-..");
    morseMap.put('E', ".");
    morseMap.put('F', "..-.");
    morseMap.put('G', "--.");
    morseMap.put('H', "....");
    morseMap.put('I', "..");
    morseMap.put('J', ".---");
    morseMap.put('K', "-.-");
    morseMap.put('L', ".-..");
    morseMap.put('M', "--");
    morseMap.put('N', "-.");
    morseMap.put('O', "---");
    morseMap.put('P', ".--.");
    morseMap.put('Q', "--.-");
    morseMap.put('R', ".-.");
    morseMap.put('S', "...");
    morseMap.put('T', "-");
    morseMap.put('U', "..-");
    morseMap.put('V', "...-");
    morseMap.put('W', ".--");
    morseMap.put('X', "-..-");
    morseMap.put('Y', "-.--");
    morseMap.put('Z', "--..");
    List<Boolean> list = new ArrayList<>();
    char[] chars = message.toUpperCase().toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == ' ') {
        for (int j = 0; j < 7; j++) list.add(false);
        continue;
      }
      String code = morseMap.get(c);
      if (code == null) continue;
      for (int j = 0; j < code.length(); j++) {
        char sym = code.charAt(j);
        if (sym == '.') list.add(true);
        else if (sym == '-') { list.add(true); list.add(true); list.add(true); }
        if (j < code.length() - 1) list.add(false);
      }
      if (i < chars.length - 1 && chars[ i + 1] != ' ') {
        for (int j = 0; j < 3; j++) list.add(false);
      }
    }
    boolean[] arr = new boolean[list.size()];
    for (int i = 0; i < list.size(); i++) arr[i] = list.get(i);
    return arr;
  }

  private boolean[] addPadding(boolean[] pattern, int pad) {
    boolean[] padded = new boolean[pattern.length + pad];
    System.arraycopy(pattern, 0, padded, 0, pattern.length);
    for (int i = pattern.length; i < padded.length; i++) padded[i] = false;
    return padded;
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    lastShiftTime = timer.get();
    scrollOffset = 0;
    OI.IS_LED_MORSE_SHOWING = true;
  }

  @Override
  public void execute() {
    double now = timer.get();
    if (now - lastShiftTime >= scrollInterval) { scrollOffset++; lastShiftTime = now; }
    if (scrollOffset > paddedPattern.length - ledCount) scrollOffset = 0;
    for (int i = 0; i < ledCount; i++) {
      int index = scrollOffset + i;
      if (index < paddedPattern.length && paddedPattern[index]) statusLED.buffer.setLED(i, symbolColor);
      else statusLED.buffer.setLED(i, backgroundColor);
    }
    statusLED.led.setData(statusLED.buffer);
  }

  @Override
  public void end(boolean interrupted) {
    timer.stop();
    for (int i = 0; i < ledCount; i++) statusLED.buffer.setLED(i, backgroundColor);
    statusLED.led.setData(statusLED.buffer);
    OI.IS_LED_MORSE_SHOWING = false;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
