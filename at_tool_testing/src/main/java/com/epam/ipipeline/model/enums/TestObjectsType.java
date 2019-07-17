package com.epam.ipipeline.model.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestObjectsType {
  Screen_1(1, "1-Screen"),
  Textbox_1(2, "1-Textbox"),
  Checkbox_1(3, "1-Checkbox"),
  Button_1(4, "1-Button"),
  Template(0);

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
