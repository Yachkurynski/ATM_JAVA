package com.epam.ipipeline.model.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestObjectsType {
  SCREEN_1(1, "1-Screen"),
  TEXTBOX_1(2, "1-Textbox"),
  CHECKBOX_1(3, "1-Checkbox"),
  BUTTON_1(4, "1-Button"),
  TEMPLATE(0);

  private int id;
  private String name;

  TestObjectsType(int id) {
    this.id = id;
    this.name = this.name();
  }

  public static int getId(String name) {
    return Arrays.stream(values())
        .filter(v -> name.equals(v.getName()))
        .findFirst().get().getId();
  }

  public static int getName(int id) {
    return Arrays.stream(values())
        .filter(v -> id == v.getId())
        .findFirst().get().getId();
  }
}
