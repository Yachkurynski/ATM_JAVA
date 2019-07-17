package com.epam.ipipeline.model.beans;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Parameter {

  private String name;
  private String type;
}
