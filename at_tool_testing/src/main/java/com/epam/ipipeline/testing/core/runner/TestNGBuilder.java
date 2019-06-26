package com.epam.ipipeline.testing.core.runner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.ITestListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGBuilder {

  private Class<?> module;
  private Set<Class<?>> testClasses = new HashSet<>();
  private ITestListener listener;

  public void withParentModule(Class<?> module) {
    this.module = module;
  }

  public void withListener(ITestListener listener) {
    this.listener = listener;
  }

  public void withTestClasses(Class<?> testClass) {
    testClasses.add(testClass);
  }

  public TestNG build() {
    TestNG testNG = new TestNG();
    XmlSuite xmlSuite = new XmlSuite();

    if (null != module) {
      xmlSuite.setParentModule(module.getName());
    }

    XmlTest xmlTest = new XmlTest(xmlSuite);
    xmlTest.setXmlClasses(testClasses.stream().map(XmlClass::new).collect(Collectors.toList()));

    testNG.setXmlSuites(Arrays.asList(xmlSuite));

    if(null != listener) {
      testNG.addListener(listener);
    }
    return testNG;
  }
}
