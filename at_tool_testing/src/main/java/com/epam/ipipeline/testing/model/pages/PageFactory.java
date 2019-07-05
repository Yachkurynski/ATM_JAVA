package com.epam.ipipeline.testing.model.pages;

import com.epam.ipipeline.testing.model.pages.GooglePage;
import com.epam.ipipeline.testing.model.pages.Page;
import com.epam.ipipeline.testing.model.pages.SearchResultPage;
import com.google.inject.Inject;

public class PageFactory {

  @Inject private GooglePage googlePage;
  @Inject private SearchResultPage searchResultPage;

  public GooglePage getGooglePage() {
    return initPage(googlePage);
  }

  public SearchResultPage getSearchResultPage() {
    return initPage(searchResultPage);
  }

  private <P extends Page>  P initPage(P page) {
    page.initialize();

    return page;
  }
}
