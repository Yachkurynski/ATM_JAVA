package com.epam.ipipeline.core.runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.ITestListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGBuilder {

  private XmlSuite xmlSuite;
  private Class<?> module;
  private Map<String, XmlClass> xmlTestClasses;
  private ITestListener listener;

  public TestNGBuilder() {
    xmlSuite = new XmlSuite();
    xmlTestClasses = new HashMap<>();
  }

  public void withParentModule(Class<?> module) {
    this.module = module;
  }

  public void withListener(ITestListener listener) {
    this.listener = listener;
  }

  public void withTestClass(String testName, Class<?> testClass) {
    XmlTest xmlTest = new XmlTest(xmlSuite);
    XmlClass xmlClass = new XmlClass(testClass);

    xmlTest.setName(testName);
    xmlTest.setPreserveOrder(false);
    xmlTest.setXmlClasses(Arrays.asList(xmlClass));

    xmlTestClasses.put(testName, xmlClass);
  }

  public void withTestMethod(String testName, String method, Map<String, String> parameters) {
    XmlClass xmlClass = xmlTestClasses.get(testName);
    List<XmlInclude> includeMethods = new LinkedList<>();
    XmlInclude testMethod = new XmlInclude(method);

    parameters.forEach(testMethod::addParameter);
    includeMethods.add(testMethod);
    xmlClass.setIncludedMethods(includeMethods);

  }

  public TestNG build() {
    TestNG testNG = new TestNG();

    if (null != module) {
      xmlSuite.setParentModule(module.getName());
    }

    if(null != listener) {
      testNG.addListener(listener);
    }
    testNG.setXmlSuites(Arrays.asList(xmlSuite));
    return testNG;
  }
}
