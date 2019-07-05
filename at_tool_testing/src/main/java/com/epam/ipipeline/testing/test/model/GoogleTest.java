package com.epam.ipipeline.testing.test.model;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleTest extends SimpleTest {

  @Test
  @Parameters("url")
  public void open(String url) {
    super.open(url);
  }

  @Test
  @Parameters("subject")
  public void search(String subject) {
    pages().getGooglePage().typeIntoSearch(subject);
    pages().getGooglePage().clickSearchButton();
  }

  @Test
  @Parameters("subject")
  public void typeIntoSearch(String subject) {
    pages().getGooglePage().typeIntoSearch(subject);
  }

  @Test
  public void clearSearchInput() {
    pages().getGooglePage().clearSearch();
  }

  @Test
  @Parameters("value")
  public void hasSearchInputValue(String value) {
    Assert.assertEquals(value, pages().getGooglePage().getSearchInputValue());
  }

  @Test
  @Parameters("subject")
  public void click(String subject) {
    pages().getSearchResultPage().clickResult(subject);
  }
}
