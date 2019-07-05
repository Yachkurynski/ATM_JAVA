package com.epam.ipipeline.testing.model.pages;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class GooglePage extends Page {

  @FindBy(xpath = "//input[@aria-label='Search']")
  private TextInput searchInput;
  @FindBy(name = "btnK")
  private Button searchButton;

  public void typeIntoSearch(String text) {
    searchInput.sendKeys(text);
  }

  public void clearSearch() {
    searchInput.clear();
  }

  public String getSearchInputValue() {
    return searchInput.getText();
  }

  public void clickSearchButton() {
    getBrowser().waitForVisibilityOf(searchButton);
    searchButton.click();
  }
}
