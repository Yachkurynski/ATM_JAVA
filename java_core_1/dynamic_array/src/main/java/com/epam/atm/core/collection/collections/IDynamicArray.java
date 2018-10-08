package com.epam.atm.core.collection.collections;

public interface IDynamicArray<T> {

  void add(T number);
  T get(int position);
  T remove(int position);
}
