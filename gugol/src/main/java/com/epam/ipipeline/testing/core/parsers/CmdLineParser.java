package com.epam.ipipeline.testing.core.parsers;

import org.kohsuke.args4j.CmdLineException;

public class CmdLineParser<T> {

  public static <T> void parse(T bean, String...args) {
    try {
      new org.kohsuke.args4j.CmdLineParser(bean).parseArgument(args);
    } catch (CmdLineException e) {
      throw new RuntimeException(e);
    }
  }
}
