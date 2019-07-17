package com.epam.ipipeline.model.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ObjectAction {

  private String name;
  private List<Parameter> parameters;
}
