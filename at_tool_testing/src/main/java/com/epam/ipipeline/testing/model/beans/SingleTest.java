package com.epam.ipipeline.testing.model.beans;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

public class SingleTest {

  @Getter
  @Setter
  private Class<?> testClass;
  @Getter
  @Setter
  private String testMethod;
  @Getter
  @Setter
  private Map<String, String> parameters;


  @Override
  public String toString() {
    return String.format("{[%s]}--(%s)", testClass.getSimpleName() , testMethod);
  }
}
