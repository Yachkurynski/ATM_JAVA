package com.epam.ipipeline.core.runner;

import com.epam.ipipeline.model.steps.UISteps;
import com.epam.ipipeline.test.model.ToolTest;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import java.io.File;
import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

@Log4j
public class ATToolListener extends ReportPortalTestNGListener {

  @Override
  public void onTestStart(ITestResult iTestResult) {
    super.onTestStart(iTestResult);

    String testClass = iTestResult.getTestClass().getRealClass().getSimpleName();
    String test = iTestResult.getMethod().getMethodName();

    log.info(String.format("%s | %s", testClass, test));
  }

  @Override
  public void onTestFailure(ITestResult result) {
    if (result.getInstance() instanceof UISteps) {
      saveScreenShoot(result);
    }

    if (null != result.getThrowable()) {
      log.info("Test execution error", result.getThrowable());
    }
    super.onTestFailure(result);
  }

  private void saveScreenShoot(ITestResult result) {
    WebDriver driver = ((ToolTest)result.getInstance()).getDriver();
    File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

    try {
      File destScreenshotFile = new File("test-output/screenshots/" + System.currentTimeMillis() + ".png");
      FileUtils.moveFile(screenShot, destScreenshotFile);

      log.info("Screen shoot of error: " + destScreenshotFile.getPath());

      ReportPortalMessage message = new ReportPortalMessage(destScreenshotFile, "Screenshot: ");
      log.info(message);

    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      FileUtils.deleteQuietly(screenShot);
    }
  }
}