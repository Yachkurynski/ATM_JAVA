package com.epam.atm.core.runner;

import com.epam.atm.core.collection.realisation.DynamicArray;

public class Main {

  public static void main(String[] args) {
    DynamicArray<Double> array1 = new DynamicArray<>();
    DynamicArray<Integer> array2 = new DynamicArray<>(10);

    for (int i = 0; i < 30; i++) {
      array1.add((double)i);
    }
    System.out.println("Array1: " + array1.toString());

    for (int i = 0; i < 10; i++) {
      array2.add(i * 10);
    }
    System.out.println("Array2: " + array2.toString());

    System.out.println(String.format("Remove Array1[%d]: %s", 2, array1.remove(2)));
    System.out.println("Array1: " + array1.toString());

    System.out.println(String.format("Remove Array2[%d]: %s", 2, array2.remove(2)));
    System.out.println("Array2: " + array2.toString());

    System.out.println("Array1[10]: " + array1.get(10));
    System.out.println("Array1: " + array1.toString());

    System.out.println("Array2[3]: " + array2.get(3));
    System.out.println("Array2: " + array2.toString());
  }
}
