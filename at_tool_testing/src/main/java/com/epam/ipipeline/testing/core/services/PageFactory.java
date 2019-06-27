package com.epam.ipipeline.testing.core.services;

import com.epam.ipipeline.testing.model.pages.KeywordsPage;
import com.epam.ipipeline.testing.model.pages.Page;
import com.google.inject.Inject;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

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
