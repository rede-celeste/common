package com.celeste.library.shared.util.formatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NumberFormatter {

  private static final String[] SUFFIXES = new String[]{ "", "K", "M", "B", "T", "Q", "QQ", "S" };

  @NotNull
  public static String format(float value) {
    int index = 0;

    float formattedValue;
    while ((formattedValue = value / 1000) >= 1) {
      value = formattedValue;
      index++;
    }

    final DecimalFormat decimalFormat = new DecimalFormat("#.##");
    return decimalFormat.format(value) + SUFFIXES[index];
  }

}