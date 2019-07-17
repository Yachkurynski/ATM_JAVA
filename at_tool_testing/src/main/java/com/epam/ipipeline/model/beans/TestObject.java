package com.epam.ipipeline.model.beans;

import com.epam.ipipeline.model.enums.TestObjectsType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TestObject {

  @Getter
  private String name;
  @Getter
  private TestObjectsType type;
}
