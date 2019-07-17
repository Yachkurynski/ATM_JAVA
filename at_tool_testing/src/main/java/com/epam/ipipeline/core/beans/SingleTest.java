package com.epam.ipipeline.core.beans;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SingleTest {

  private Class<?> testClass;
  private String testMethod;
  private Map<String, String> parameters;
}
