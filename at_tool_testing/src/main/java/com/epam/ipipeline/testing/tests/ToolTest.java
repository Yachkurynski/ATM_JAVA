package com.epam.ipipeline.testing.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class ToolTest {

  @BeforeSuite
  public void prepareProduct() {
    Selenide.open("http://10.6.220.15:8090/tool-0.1/");
    WebDriverRunner.getWebDriver().manage().window().maximize();
  }

  @AfterSuite
  public void closeDriver() {
    WebDriverRunner.closeWebDriver();
  }

}
