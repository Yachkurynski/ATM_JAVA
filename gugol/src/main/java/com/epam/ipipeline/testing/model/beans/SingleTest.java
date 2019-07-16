package com.epam.ipipeline.testing.model.beans;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SingleTest {

  private Class<?> testClass;
  private String testMethod;
  private Map<String, String> parameters;

  @Override
  public String toString() {
    return String.format("{[%s]}--(%s)", testClass.getSimpleName() , testMethod);
  }
}
