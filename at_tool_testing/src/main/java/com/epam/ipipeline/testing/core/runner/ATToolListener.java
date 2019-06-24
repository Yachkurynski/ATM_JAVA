package com.epam.ipipeline.testing.core.runner;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ATToolListener extends TestListenerAdapter {

  private static Logger LOGGER = Logger.getLogger(ATToolListener.class);

  @Override
  public void onTestStart(ITestResult iTestResult) {
    String testClass = iTestResult.getInstanceName();
    String test = iTestResult.getMethod().getMethodName();

    LOGGER.info(String.format("%s | %s", testClass, test));
  }
}
