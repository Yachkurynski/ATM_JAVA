package com.epam.ipipeline.core.runner;

import com.epam.ipipeline.model.steps.APISteps;
import com.epam.ipipeline.model.steps.Steps;
import com.epam.ipipeline.model.steps.UISteps;
import com.epam.ipipeline.model.ui.pages.PageFactory;
import com.epam.ipipeline.model.ui.services.WebDriverService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class ATToolModule extends AbstractModule {

  @Override
  protected void configure() {
    Config config = ToolTestRunner.getConfig();

    bind(Config.class).toInstance(config);
    bind(Steps.class).to(config.isApi() ? APISteps.class : UISteps.class);

    bind(WebDriverService.class).in(Scopes.SINGLETON);
    bind(PageFactory.class).in(Scopes.SINGLETON);
  }
}
