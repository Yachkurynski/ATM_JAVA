package com.epam.ipipeline.model.steps;

import com.epam.ipipeline.core.runner.ATToolModule;
import java.util.List;
import org.testng.annotations.Guice;

//@Guice(modules = ATToolModule.class)
public interface Steps {
  void addTestObject(String name, String parent);
  boolean hasTestObject(String name);
  List<String> getObjectActions(String object);
}
