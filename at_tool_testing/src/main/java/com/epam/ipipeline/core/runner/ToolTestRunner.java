package com.epam.ipipeline.core.runner;


import com.epam.ipipeline.core.beans.SingleTest;
import com.epam.ipipeline.core.parsers.CmdLineParser;
import com.epam.ipipeline.core.parsers.ExcelTestParser;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.testng.TestNG;

public class ToolTestRunner {

  private static Config config;

  public static void main(String[] args) {
    config = new Config();
    CmdLineParser.parse(config, args);

    TestNGBuilder builder = new TestNGBuilder();

    builder.withListener(new ATToolListener());

    Map<String, SingleTest> testFlow = new LinkedHashMap<>();
    File source = new File(config.getExcel());
    AtomicInteger fileCounter = new AtomicInteger(1);

    if(source.isDirectory()) {
      Arrays.asList(source.listFiles()).forEach(f->fillTestFlow(f, fileCounter.getAndIncrement(), testFlow));
    } else {
      fillTestFlow(source, fileCounter.getAndIncrement(), testFlow);
    }

    testFlow.forEach((testName, test)->{
      builder.withTestClass(testName, test.getTestClass());
      builder.withTestMethod(testName, test.getTestMethod(), test.getParameters());
    });

    TestNG testng = builder.build();

    testng.run();
  }

  private static void fillTestFlow(File source, int fileCounter, Map<String, SingleTest> testFlow) {
    AtomicInteger counter = new AtomicInteger(1);

    ExcelTestParser.of(source).get()
        .forEach(test->testFlow.put(String.format("%d.%d [%s] %s", fileCounter, counter.getAndIncrement(), source.getName(), test.toString()), test));
  }

  static Config getConfig() {
    return config;
  }
}
