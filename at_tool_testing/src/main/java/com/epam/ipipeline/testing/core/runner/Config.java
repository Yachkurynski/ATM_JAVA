package com.epam.ipipeline.testing.core.runner;

import lombok.Getter;
import org.kohsuke.args4j.Option;

@Getter
public class Config {

  @Option(name = "-excel")
  private String excel;

}
