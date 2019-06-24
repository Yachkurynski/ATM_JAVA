package com.epam.ipipeline.testing.model.pages.elements;

import com.epam.ipipeline.testing.model.beans.ObjectAction;
import com.epam.ipipeline.testing.model.beans.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class ObjectActionsForm extends HtmlElement {

  private static final String ADD_TO_FORM_INPUT = ".//input[@id='addToForm']";

  @FindBy(xpath = ADD_TO_FORM_INPUT + "[@type = 'text']")
  private TextInput newObjectActionInput;
  @FindBy(xpath = ADD_TO_FORM_INPUT + "[@type = 'button']")
  private Button addActionButton;
  @FindBy(xpath = ".//div[contains(@class, 'to-oa-wrapper')]")
  private List<WebElement> actionsRows;

  public void typeNewObjectAction(String actionName) {
    newObjectActionInput.sendKeys(actionName);
  }

  public void clickAddActionButton() {
    addActionButton.click();
  }

  public List<ObjectAction> getActions() {
    List<ObjectAction> actions = new ArrayList<>();

    actionsRows.forEach(actionRow->
        actions.add(new ObjectAction(
            actionRow.findElement(By.xpath("./div[@class='col-sm-9']")).getText(),
            getParams(actionRow))));
    return actions;
  }

  public void addParameter(String actionName, String paramName, String paramType, String paramForm,
      boolean isOptional) {
    AddParameterForm form = new AddParameterForm();
    WebElement row = getActionRow(actionName);

    row.findElement(By.xpath(".//button[contains(@class, 'link')]")).click();
    HtmlElementLoader.populateHtmlElement(form, row);

    form.typeName(paramName);
    form.selectType(paramType);
    form.selectForm(paramForm);

    if (isOptional) {
      form.checkIsOptional();
    }
    form.clickAddButton();
  }

  private List<Parameter> getParams(WebElement actionRow) {
    List<Parameter> params = new ArrayList<>();

    actionRow.findElements(By.xpath("./div[@class='col-sm-9']//div[@class='to-oa-ap-wrapper']")).forEach(el->
        params.add(new Parameter(el.getText(), el.findElement(By.xpath(".//span[contains(@class, 'badge')]")).getText())));
    return params;
  }

  private WebElement getActionRow(String actionName) {
    return actionsRows.stream().filter(r->r.findElement(By.xpath("./div[contains(@class, 'sm-3')]")).getText().equals(actionName)).findFirst().get();
  }
}
