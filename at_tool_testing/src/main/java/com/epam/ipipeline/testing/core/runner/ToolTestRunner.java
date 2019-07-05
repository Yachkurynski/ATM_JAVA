package com.epam.ipipeline.testing.core.runner;

import com.epam.ipipeline.testing.core.parsers.CmdLineParser;
import com.epam.ipipeline.testing.core.parsers.TestExcelParser;
import com.epam.ipipeline.testing.model.beans.SingleTest;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.testng.TestNG;

public class ToolTestRunner {

  public static void main(String[] args) {
    Config config = new Config();
    CmdLineParser.parse(config, args);

    TestNGBuilder builder = new TestNGBuilder();

    builder.withListener(new TestListener());

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

    TestNG testNG = builder.build();

    testNG.run();
  }

  private static void fillTestFlow(File source, int fileCounter, Map<String, SingleTest> testFlow) {
    AtomicInteger counter = new AtomicInteger(1);

    new TestExcelParser(source).get()
        .forEach(test->testFlow.put(String.format("%d.%d [%s] %s", fileCounter, counter.getAndIncrement(), source.getName(), test.toString()), test));
  }
}
