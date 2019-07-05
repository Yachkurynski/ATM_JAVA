package com.epam.ipipeline.testing.core.injections;

import com.epam.ipipeline.testing.model.pages.PageFactory;
import com.epam.ipipeline.testing.model.utils.WebDriverService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class TestModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(WebDriverService.class).in(Scopes.SINGLETON);
    bind(PageFactory.class).in(Scopes.SINGLETON);
  }
}
