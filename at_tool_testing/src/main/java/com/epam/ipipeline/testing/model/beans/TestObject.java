package com.epam.ipipeline.testing.model.beans;

import com.epam.ipipeline.testing.model.enums.TestObjectsType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TestObject {

  @Getter
  private String name;
  @Getter
  private TestObjectsType type;
}
