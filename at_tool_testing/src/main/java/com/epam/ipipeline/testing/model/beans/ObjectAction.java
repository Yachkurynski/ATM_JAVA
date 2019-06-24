package com.epam.ipipeline.testing.model.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ObjectAction {

  @Getter
  private String name;
  @Getter
  private List<Parameter> properties;
}
