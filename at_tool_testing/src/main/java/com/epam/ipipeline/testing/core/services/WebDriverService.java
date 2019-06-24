package com.epam.ipipeline.testing.core.services;

import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Singleton;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;

public class WebDriverService {

  public WebDriver getDriver() {
    return WebDriverRunner.getWebDriver();
  }

  public  void closeWebDriver() {
    WebDriverRunner.closeWebDriver();
  }


}
