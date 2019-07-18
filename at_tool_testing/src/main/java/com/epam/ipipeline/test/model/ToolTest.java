package com.epam.ipipeline.test.model;

import com.codeborne.selenide.Selenide;
import com.epam.ipipeline.core.runner.ATToolModule;
import com.epam.ipipeline.core.runner.Config;
import com.epam.ipipeline.model.steps.Steps;
import com.epam.ipipeline.model.steps.UISteps;
import com.epam.ipipeline.model.ui.services.WebDriverService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Guice;

@Guice(modules = ATToolModule.class)
public abstract class ToolTest {

  @Getter
  @Inject private Steps steps;
  @Inject private WebDriverService service;
  @Inject private Config config;

  @BeforeSuite
  public void prepareProduct() {
    if (steps instanceof UISteps) {
      Selenide.open("http://10.6.220.15:8090/tool-0.1/");
      getDriver().manage().window().maximize();
    }
  }

  @AfterSuite
  public void closeDriver() {
    if (steps instanceof UISteps) {
      service.closeWebDriver();
    }
  }

  public WebDriver getDriver() {
    return service.getDriver();
  }

  protected List<String> parseStringToList(String jsonString) {
    Type mapType = new TypeToken<List<String>>(){}.getType();

    return new Gson().fromJson(jsonString, mapType);
  }
}
