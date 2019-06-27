package com.epam.ipipeline.testing.test.model;

import com.epam.ipipeline.testing.model.enums.TestObjectsType;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

public class KeywordsTest extends ToolTest {

  @Test
  public void addTestObject(String name, String type) {
    factory().getKeywordsPage().addTestObject(name, TestObjectsType.valueOf(type));
  }

  @Test
  public void hasTestObject(String name) {
    List<String> objects = factory().getKeywordsPage().getTestObjectsNames();
    Assert.assertTrue(objects.contains(name));
  }

  @Test
  public void objectHasActions(String object, String actions) {
    factory().getKeywordsPage().clickTestObject(object);
    Assert.assertEquals(factory().getKeywordsPage().getActionsNames(), parseStringToList(actions));
  }
}
