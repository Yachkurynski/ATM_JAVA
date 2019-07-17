package com.epam.ipipeline.model.ui.pages;

import com.google.inject.Inject;

public class PageFactory {

  @Inject private KeywordsPage keywordsPage;

  public KeywordsPage getKeywordsPage() {
    return initPage(keywordsPage);
  }

  private <P extends Page>  P initPage(P page) {
    page.initialize();

    return page;
  }
}
