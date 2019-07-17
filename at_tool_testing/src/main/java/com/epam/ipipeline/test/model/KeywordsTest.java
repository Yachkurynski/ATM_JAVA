package com.epam.ipipeline.test.model;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class KeywordsTest extends ToolTest {

  @Test
  @Parameters({"name", "parent"})
  public void addTestObject(String name, @Optional String parent) {
    getSteps().addTestObject(name, StringUtils.defaultIfBlank(parent, ""));
  }

  @Test
  @Parameters("name")
  public void hasTestObject(String name) {
    Assert.assertTrue(getSteps().hasTestObject(name));
  }

  @Test
  @Parameters({"object", "actions"})
  public void objectHasActions(String object, String actions) {
    Assert.assertEquals(getSteps().getObjectActions(object), parseStringToList(actions));
  }
}
