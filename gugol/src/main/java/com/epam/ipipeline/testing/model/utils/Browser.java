package com.epam.ipipeline.testing.model.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

  private static final int DEFAUL_TIMEOUT = 10;

  public void waitForVisibilityOf(WebElement element) {
    waitFor(ExpectedConditions.visibilityOf(element));
  }

  public void waitFor(ExpectedCondition<?> condition) {
    new WebDriverWait(driver(), DEFAUL_TIMEOUT).until(condition);
  }

  private WebDriver driver() {
    return WebDriverRunner.getWebDriver();
  }

}
