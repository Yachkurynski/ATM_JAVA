package com.epam.ipipeline.testing.steps;

import com.epam.ipipeline.testing.core.services.WebDriverService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class ToolSteps {

  private WebDriverService service;

  public ToolSteps() {
    service = new WebDriverService();
  }

  public WebDriver getDriver() {
    return getService().getDriver();
  }

  public WebDriverService getService() {
    return service;
  }

  protected List<String> parseStringToList(String jsonString) {
    Type mapType = new TypeToken<List<String>>(){}.getType();

    return new Gson().fromJson(jsonString, mapType);
  }
}
