package com.epam.ipipeline.testing.tests;

import com.epam.ipipeline.testing.model.beans.TestObject;
import com.epam.ipipeline.testing.model.enums.TestObjectsType;
import com.epam.ipipeline.testing.steps.KeywordsSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KeywordsTest extends ToolTest {

  private KeywordsSteps keywordsSteps;

  @BeforeClass
  public void initialize() {
    keywordsSteps = new KeywordsSteps();
  }

  @Test
  public void addTestObject() {
//    keywordsSteps.addTestObject(new TestObject("z-checkbox", TestObjectsType.Version));
//    keywordsSteps.hasTestObject("z-checkbox");
  }

  @Test
  public void hasTestObject() {
//    keywordsSteps.hasTestObject("Preview");
  }

  @Test
  public void objectHasActions() {
//    keywordsSteps.openTestObject("Report");
//    keywordsSteps.hasActions("[ logCase ]");
  }
}
