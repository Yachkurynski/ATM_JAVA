package com.epam.ipipeline.model.ui.services;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;

public class WebDriverService {

  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public  void closeWebDriver() {
    WebDriverRunner.closeWebDriver();
  }


}
