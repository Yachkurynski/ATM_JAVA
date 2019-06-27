package com.epam.ipipeline.testing.model.pages;

import com.codeborne.selenide.Selenide;
import com.epam.ipipeline.testing.model.beans.ObjectAction;
import com.epam.ipipeline.testing.model.pages.elements.AddTestObjectCard;
import com.epam.ipipeline.testing.model.pages.elements.ObjectActionsForm;
import com.epam.ipipeline.testing.model.enums.TestObjectsType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class KeywordsPage extends Page {

  private static final String ACTION_NAME_SEPARATOR = ". ";

  @FindBy(xpath = ".//div[@class='col-sm-9']/div/div[@aria-hidden='false']")
  private ObjectActionsForm objectActionsForm;
  @FindBy(xpath = "//div[@class='nav-item']//a")
  private List<WebElement> testObjects;

  private AddTestObjectCard addObjectCard;

  public void addTestObject(String name, TestObjectsType type) {
    addObjectCard.clickAddObjectButton();
    Selenide.Wait().until((ExpectedCondition<Boolean>) driver -> addObjectCard.isExpanded());

    addObjectCard.typeNewObjectName(name);
    addObjectCard.selectObjectType(type);
    addObjectCard.clickCreateObjectButton();
  }

  public void clickTestObject(String name) {
    Map<String, WebElement> namesToObjects = testObjects.stream()
        .collect(Collectors.toMap(obj-> getObjectName(obj.getText()), obj->obj));

    namesToObjects.get(name).click();
  }

  /*public boolean isTestObjectPresent(String name) {
    return !testObjects.stream()
        .map(o->getObjectName(o.getText()))
        .filter(obj->obj.equals(name))
        .collect(Collectors.toList()).isEmpty();
  }*/

  public List<String> getTestObjectsNames() {
    return testObjects.stream()
        .map(o->getObjectName(o.getText()))
        .collect(Collectors.toList());
  }

  public List<String> getActionsNames() {
    return objectActionsForm.getActions().stream().map(ObjectAction::getName).collect(Collectors.toList());
  }

  private String getObjectName(String action) {
    return StringUtils.substringAfter(action, ACTION_NAME_SEPARATOR);
  }
}
