package com.epam.ipipeline.model.ui.pages;

import com.epam.ipipeline.model.ui.services.WebDriverService;
import com.google.inject.Inject;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public abstract class Page {

  @Inject private WebDriverService service;

  public void initialize() {
    HtmlElementLoader.populatePageObject(this, service.getDriver());
  }
}
