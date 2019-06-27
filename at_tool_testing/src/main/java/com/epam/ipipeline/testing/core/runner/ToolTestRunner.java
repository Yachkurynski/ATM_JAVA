package com.epam.ipipeline.testing.core.runner;


import com.epam.ipipeline.testing.core.injections.ATToolModule;
import com.epam.ipipeline.testing.core.services.CmdLineParser;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import java.io.IOException;
import org.testng.TestNG;

public class ToolTestRunner {

  private static final String SUITE_PATH = "com.epam.ipipeline.testing.test.suite";

  public static void main(String[] args) {
    Config config = new Config();
    CmdLineParser.parse(config, args);

    TestNGBuilder builder = new TestNGBuilder();

    builder.withListener(new ATToolListener());
    builder.withParentModule(ATToolModule.class);

    try {
      ClassPath
          .from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(SUITE_PATH + config.getSuiteNumber())
          .stream().map(ClassInfo::load).forEach(builder::withTestClasses);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    TestNG testng = builder.build();

    testng.run();
  }
}
