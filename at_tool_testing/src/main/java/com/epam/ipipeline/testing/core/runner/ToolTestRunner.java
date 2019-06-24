package com.epam.ipipeline.testing.core.runner;


import com.epam.ipipeline.testing.core.services.CmdLineParser;
import com.epam.ipipeline.testing.tests.KeywordsTest;
import java.util.ArrayList;
import org.testng.TestNG;

public class ToolTestRunner {

  public static void main(String[] args) {
    Config config = new Config();
    CmdLineParser.parse(config, args);

    TestNG testngSuite = new TestNG();

    testngSuite.setTestClasses(new Class[]{KeywordsTest.class});
    testngSuite.addListener(new ATToolListener());
//    testngSuite.setXmlSuites(new ArrayList<>());

//    XmlSuite xmlSuite = new XmlSuite();
//    xmlSuite.set


    testngSuite.run();
  }
}
