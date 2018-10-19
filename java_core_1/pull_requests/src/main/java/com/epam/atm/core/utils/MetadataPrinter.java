package com.epam.atm.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MetadataPrinter {

  private MetadataPrinter() {}

  public static void print(Class clazz) {
    Field[] fields = clazz.getDeclaredFields();
    Method[] methods = clazz.getDeclaredMethods();
    Class[] interfaces = clazz.getInterfaces();
    Class superClass = clazz.getSuperclass();

    StringBuffer fieldsList = new StringBuffer("Fields:\n");
    Arrays.stream(fields).forEach(f->{
      fieldsList.append(String.format(" - %s %s\n", f.getType().getSimpleName(), f.getName()));
    });

    StringBuffer methodsList = new StringBuffer("Methods:\n");
    Arrays.stream(methods).forEach(m->{
      methodsList.append(String.format(" - %s %s\n", m.getReturnType().getSimpleName(), m.getName()));
    });

    StringBuffer interfacesList = new StringBuffer(String.format("%s extends %s implements "
        , clazz.getSimpleName(), superClass.getSimpleName()));
    Arrays.stream(interfaces).forEach(i->{
      fieldsList.append(i.getSimpleName()). append(" ");
    });

    System.out.println(interfacesList);
    System.out.println(fieldsList);
    System.out.println(methodsList);
  }
}
