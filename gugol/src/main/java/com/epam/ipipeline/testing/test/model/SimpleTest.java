package com.epam.ipipeline.testing.test.model;

import com.codeborne.selenide.Selenide;
import com.epam.ipipeline.testing.core.injections.TestModule;
import com.epam.ipipeline.testing.model.pages.PageFactory;
import com.epam.ipipeline.testing.model.utils.WebDriverService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Guice;

@Guice(modules = TestModule.class)
public abstract class SimpleTest {

  @Inject private PageFactory factory;
  @Inject private WebDriverService service;

  protected void open(String url) {
    Selenide.open(url);
    getDriver().manage().window().maximize();
  }

  @AfterSuite
  public void closeDriver() {
    service.closeWebDriver();
  }

  public WebDriver getDriver() {
    return service.getDriver();
  }

  public PageFactory pages() {
    return factory;
  }

  protected List<String> parseStringToList(String jsonString) {
    Type mapType = new TypeToken<List<String>>(){}.getType();

    return new Gson().fromJson(jsonString, mapType);
  }
}
