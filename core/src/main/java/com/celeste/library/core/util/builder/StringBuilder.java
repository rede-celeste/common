package com.celeste.library.core.util.builder;

import com.celeste.library.core.registry.type.KeyType;
import com.celeste.library.core.util.formatter.StringFormatter;
import com.celeste.library.core.util.pattern.RegexPattern;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class StringBuilder {

  private String str;
  private KeyType keyType;

  public StringBuilder(final String str) {
    this.str = str;
    this.keyType = KeyType.STANDARD;
  }

  public StringBuilder append(final String arg) {
    this.str = str + arg;
    return this;
  }

  public StringBuilder append(final String[] args, final String format) {
    this.str = str + StringFormatter.join(args, format);
    return this;
  }

  public StringBuilder append(final String format, final String... args) {
    this.str = str + StringFormatter.join(args, format);
    return this;
  }

  public StringBuilder append(final String[] args, final String beforeArg, final String afterArg) {
    this.str = str + StringFormatter.join(args, beforeArg, afterArg);
    return this;
  }

  public StringBuilder append(final String beforeArg, final String afterArg, final String... args) {
    this.str = str + StringFormatter.join(args, beforeArg, afterArg);
    return this;
  }

  public StringBuilder remove(final RegexPattern pattern) {
    this.str = str.replaceAll(pattern.getPattern(), "");
    return this;
  }

  public StringBuilder remove(final int size) {
    this.str = str.substring(0, str.length() - size);
    return this;
  }

  public StringBuilder startAtIndex(final int index) {
    this.str = str.substring(index);
    return this;
  }

  public StringBuilder onlyWith(final RegexPattern pattern) {
    this.str = str.replaceAll("(" + RegexPattern.REMOVE_GROUP + pattern.getPattern() + ")", "");
    return this;
  }

  public StringBuilder lowerCase() {
    this.keyType = KeyType.LOWER_CASE;
    return this;
  }

  public StringBuilder upperCase() {
    this.keyType = KeyType.UPPER_CASE;
    return this;
  }

  public StringBuilder standard() {
    this.keyType = KeyType.STANDARD;
    return this;
  }

  public String build() {
    return keyType.convert(str);
  }

}
