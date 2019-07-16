package com.epam.ipipeline.testing.model.pages;

import com.epam.ipipeline.testing.model.utils.Browser;
import com.epam.ipipeline.testing.model.utils.WebDriverService;
import com.google.inject.Inject;
import lombok.Getter;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

@Getter
public abstract class Page {

  @Inject private WebDriverService service;
  @Inject private Browser browser;

  public void initialize() {
    HtmlElementLoader.populatePageObject(this, service.getDriver());
  }
}
