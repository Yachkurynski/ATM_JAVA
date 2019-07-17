package com.epam.ipipeline.model.steps;

import com.epam.ipipeline.model.ui.pages.PageFactory;
import com.google.inject.Inject;
import java.util.List;

public class UISteps implements Steps {

  @Inject private PageFactory factory;

  @Override
  public void addTestObject(String name, String parent) {
    factory.getKeywordsPage().addTestObject(name, parent);
  }

  @Override
  public boolean hasTestObject(String name) {
    return factory.getKeywordsPage().getTestObjectsNames().contains(name);
  }

  @Override
  public List<String> getObjectActions(String object) {
    factory.getKeywordsPage().clickTestObject(object);
    return factory.getKeywordsPage().getActionsNames();
  }
}
