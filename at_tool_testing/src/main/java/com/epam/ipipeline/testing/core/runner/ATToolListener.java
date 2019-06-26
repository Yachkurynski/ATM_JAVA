package com.epam.ipipeline.testing.core.runner;

import com.epam.ipipeline.testing.tests.ToolTest;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ATToolListener extends TestListenerAdapter {

  private static Logger LOGGER = Logger.getLogger(ATToolListener.class);

  @Override
  public void onTestStart(ITestResult iTestResult) {
    super.onTestStart(iTestResult);

    String testClass = iTestResult.getTestClass().getRealClass().getSimpleName();
    String test = iTestResult.getMethod().getMethodName();

    LOGGER.info(String.format("%s | %s", testClass, test));
  }

  @Override
  public void onTestFailure(ITestResult result) {
    saveScreenShoot(result);

    if (null != result.getThrowable()) {
      LOGGER.info("Test execution error", result.getThrowable());
    }
    super.onTestFailure(result);
  }

  private void saveScreenShoot(ITestResult result) {
    WebDriver driver = ((ToolTest)result.getInstance()).getDriver();
    File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    try {
      File destScreenshotFile = new File("test-output/screenshots/" + System.currentTimeMillis() + ".png");
      FileUtils.moveFile(screenShot, destScreenshotFile);

      LOGGER.info("Screen shoot of error: " + destScreenshotFile.getPath());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      FileUtils.deleteQuietly(screenShot);
    }
  }
}
