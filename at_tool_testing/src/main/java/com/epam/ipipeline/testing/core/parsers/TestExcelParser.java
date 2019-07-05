package com.epam.ipipeline.testing.core.parsers;

import com.epam.ipipeline.testing.model.beans.SingleTest;
import com.epam.ipipeline.testing.model.enums.ColumnNames;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TestExcelParser {

  private static final String TESTS_PATH = "com.epam.ipipeline.testing.test.model.";

  private File source;
  private Sheet sheet;
  private Map<ColumnNames, Integer> headers;

  public TestExcelParser(File fileToParse) {
    source = fileToParse;
  }

  private void initialize() {
    try(Workbook workbook = WorkbookFactory.create(source)) {
      sheet = workbook.getSheet("Sheet1");
    } catch (IOException | InvalidFormatException e) {
      throw new RuntimeException(e);
    }
    headers = new HashMap<>();

    sheet.getRow(sheet.getFirstRowNum())
        .forEach(c-> headers.put(ColumnNames.valueOf(c.getStringCellValue()), c.getColumnIndex()));
  }

  public List<SingleTest> get() {
    List<SingleTest> tests = new LinkedList<>();

    initialize();

    for (Iterator<Row> rowIterator = sheet.rowIterator(); rowIterator.hasNext(); ) {
      Row row = rowIterator.next();

      if (row.getRowNum() != sheet.getFirstRowNum()) {
        tests.add(createSingleTest(row));
      }
    }
    return tests;
  }

  private SingleTest createSingleTest(Row row) {
    SingleTest singleTest = new SingleTest();

    singleTest.setTestClass(getTestClass(getCellValue(row, ColumnNames.Object)));
    singleTest.setTestMethod(getCellValue(row, ColumnNames.Action));
    singleTest.setParameters(getParams(row));

    return singleTest;
  }

  private Class<?> getTestClass(String className) {
    try {
      return ClassLoader.getSystemClassLoader().loadClass(TESTS_PATH + className);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private String getCellValue(Row row, ColumnNames column) {
    return row.getCell(headers.get(column)).getStringCellValue();
  }

  private Map<String, String> getParams(Row row) {
    Map<String, String> parameters = new HashMap<>();
    String paramStr = getCellValue(row, ColumnNames.Parameters);

    if (StringUtils.isNotBlank(paramStr)) {
      Arrays.stream(paramStr.split(", ")).forEach(str ->
          parameters.put(StringUtils.substringBefore(str, "='"),
              StringUtils.substringBetween(str, "'")));
    }
    return parameters;
  }
}
