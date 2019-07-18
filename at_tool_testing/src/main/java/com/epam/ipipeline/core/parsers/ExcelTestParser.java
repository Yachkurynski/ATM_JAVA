package com.epam.ipipeline.core.parsers;

import com.epam.ipipeline.core.beans.SingleTest;
import com.epam.ipipeline.core.enums.ColumnNames;
import com.epam.ipipeline.core.exceptions.ATToolRuntimeException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@RequiredArgsConstructor(staticName = "of")
public class ExcelTestParser {

  private static final String TESTS_PATH = "com.epam.ipipeline.test.model.";

  private final File source;
  private Sheet sheet;
  private Map<ColumnNames, Integer> headers;

  private void initialize() {
    try(Workbook workbook = WorkbookFactory.create(source)) {
      sheet = workbook.getSheet("Sheet1");
    } catch (IOException | InvalidFormatException e) {
      throw new ATToolRuntimeException(e);
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
    return SingleTest.builder()
        .testClass(getTestClass(getCellValue(row, ColumnNames.Object)))
        .testMethod(getCellValue(row, ColumnNames.Action))
        .parameters(getParams(row)).build();
  }

  private Class<?> getTestClass(String className) {
    try {
      return ClassLoader.getSystemClassLoader().loadClass(TESTS_PATH + className);
    } catch (ClassNotFoundException e) {
      throw new ATToolRuntimeException(e);
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
          parameters.put(StringUtils.substringBefore(str, "="),
              StringUtils.substringAfter(str, "=")));
    }
    return parameters;
  }
}
