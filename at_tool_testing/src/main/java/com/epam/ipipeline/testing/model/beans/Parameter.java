package com.epam.ipipeline.testing.model.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Parameter {

  @Getter
  private String name;
  @Getter
  private String type;
}
