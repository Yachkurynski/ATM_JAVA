package com.epam.ipipeline.testing.steps;

import com.epam.ipipeline.testing.model.beans.TestObject;
import com.epam.ipipeline.testing.model.pages.KeywordsPage;
import java.util.List;
import lombok.Getter;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class KeywordsSteps extends ToolSteps {

  @Getter
  private KeywordsPage keywordsPage;

  public KeywordsSteps() {
    keywordsPage = new KeywordsPage();
    HtmlElementLoader.populatePageObject(keywordsPage, getDriver());
  }

  public void addTestObject(TestObject testObject) {
    getKeywordsPage().addTestObject(testObject.getName(), testObject.getType());
  }

  public void hasTestObject(String name) {
    Assert.assertTrue(getKeywordsPage().isTestObjectPresent(name), "TestObject is not present.");
  }

  public void openTestObject(String name) {
    getKeywordsPage().clickTestObject(name);
  }

  public void hasActions(String actions) {
    List<String> expected = parseStringToList(actions);

    Assert.assertEquals(getKeywordsPage().getActionsNames(), expected);
  }
}
