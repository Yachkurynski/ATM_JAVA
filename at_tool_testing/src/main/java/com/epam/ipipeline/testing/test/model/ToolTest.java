package com.epam.ipipeline.testing.test.model;

import com.codeborne.selenide.Selenide;
import com.epam.ipipeline.testing.core.injections.ATToolModule;
import com.epam.ipipeline.testing.core.services.PageFactory;
import com.epam.ipipeline.testing.core.services.WebDriverService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

@Guice(modules = ATToolModule.class)
public abstract class ToolTest {

  @Inject private PageFactory factory;
  @Inject private WebDriverService service;

  @BeforeSuite
  public void prepareProduct() {
    Selenide.open("http://10.6.220.15:8090/tool-0.1/");
    getDriver().manage().window().maximize();
  }

  @AfterSuite
  public void closeDriver() {
    service.closeWebDriver();
  }

  public WebDriver getDriver() {
    return service.getDriver();
  }

  public PageFactory factory() {
    return factory;
  }

  protected List<String> parseStringToList(String jsonString) {
    Type mapType = new TypeToken<List<String>>(){}.getType();

    return new Gson().fromJson(jsonString, mapType);
  }
}
