package com.epam.ipipeline.testing.model.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

public class WebDriverService {

  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public  void closeWebDriver() {
    if (WebDriverRunner.hasWebDriverStarted()) {
      WebDriverRunner.closeWebDriver();
    }
  }
}
