package com.epam.ipipeline.testing.core.runner;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ATToolListener implements ITestListener {

  private static Logger LOGGER = Logger.getLogger(ATToolListener.class);

  @Override
  public void onTestStart(ITestResult iTestResult) {
    String testClass = iTestResult.getInstanceName();
    String test = iTestResult.getMethod().getMethodName();

    LOGGER.info(String.format("%s | %s", testClass, test));
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {

  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {

  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

  }

  @Override
  public void onStart(ITestContext iTestContext) {

  }

  @Override
  public void onFinish(ITestContext iTestContext) {

  }
}
