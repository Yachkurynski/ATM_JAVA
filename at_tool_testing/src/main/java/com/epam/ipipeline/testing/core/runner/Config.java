package com.epam.ipipeline.testing.core.runner;

import lombok.Getter;
import org.kohsuke.args4j.Option;

public class Config {

  @Getter
  @Option(name = "-url")
  private String url;

  @Getter
  @Option(name = "-suite")
  private String suiteNumber;

}
