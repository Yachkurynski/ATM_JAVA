package com.epam.ipipeline.testing.model.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends Page {

  @FindBy(xpath = "//div[@class='bkWMgd']//div[@class='g']")
  private List<WebElement> results;

  public void clickResult(String result) {
    results.stream()
        .filter(res-> !res.findElements(By.xpath(".//h3[@class='LC20lb']")).isEmpty())
        .filter(res->result.equals(res.findElement(By.xpath(".//h3[@class='LC20lb']")).getText()))
        .findFirst().get().click();
  }
}
