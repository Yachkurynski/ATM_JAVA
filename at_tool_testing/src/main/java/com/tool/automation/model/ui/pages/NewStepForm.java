package com.tool.automation.model.ui.pages;

import static java.lang.String.format;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

import com.tool.automation.model.enums.StepsTableColumns;
import com.tool.automation.model.ui.elements.TableSelect;
import com.tool.automation.model.ui.elements.TableTypifiedSelect;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class NewStepForm extends HtmlElement {

  private static final String ROW = "./tbody/tr[%d]";

  public NewStepForm(int rowNumber) {
    super();
    setWrappedElement(findElement(xpath(format(ROW, rowNumber))));
  }

  public void selectObject(String object) {
    new TableSelect(findElement(id("dDObject"))).selectValue(object);
  }

  public void selectName(String name) {
    new TableTypifiedSelect(getElementInColumn(StepsTableColumns.NAME, "/div/div")).select(name);
  }

  public void typeAndSelectName(String name) {
    new TableTypifiedSelect(getElementInColumn(StepsTableColumns.NAME, "/div/div")).typeAndSelect(name);
  }

  public List<String> getNames() {
    return new TableTypifiedSelect(getElementInColumn(StepsTableColumns.NAME, "/div/div")).getOptions();
  }

  public void selectAction(String action) {
    new TableSelect(findElement(id("dDAction"))).selectValue(action);
  }

  public void clickAddStep() {
    getElementInColumn(StepsTableColumns.OTHER, "/input[@type='button']").click();
  }

  public void typeArgument(String argument) {
    getElementInColumn(StepsTableColumns.ACTION_AND_ARGS, "//table//input[@type='text']")
        .sendKeys(argument);
  }

  public List<String> getArguments() {
    return getElementsInColumn(StepsTableColumns.ACTION_AND_ARGS, "//table//label")
        .stream().map(WebElement::getText).collect(Collectors.toList());
  }

  private WebElement getElementInColumn(StepsTableColumns column, String xpath) {
    return getElementsInColumn(column, xpath).get(0);
  }

  private List<WebElement> getElementsInColumn(StepsTableColumns column, String xpath) {
    return findElements(xpath(format("./td[%d]" + xpath, column.getColumn())));
  }
}