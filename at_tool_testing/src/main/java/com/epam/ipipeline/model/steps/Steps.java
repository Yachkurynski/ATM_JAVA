package com.epam.ipipeline.model.steps;

import java.util.List;

public interface Steps {
  void addTestObject(String name, String parent);
  boolean hasTestObject(String name);
  List<String> getObjectActions(String object);
}
