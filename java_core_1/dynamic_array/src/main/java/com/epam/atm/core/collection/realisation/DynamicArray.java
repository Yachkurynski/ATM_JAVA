package com.epam.atm.core.collection.realisation;

import com.epam.atm.core.collection.collections.IDynamicArray;
import java.util.Arrays;

/**
 * Typed dynamic array collection.
 * @param <T> type of collection
 */
public class DynamicArray<T> implements IDynamicArray<T> {

  private static final int INITIAL_SIZE = 15;
  private static final double LOAD_FACTOR = 0.8;
  private static final int INCREASE = 2;

  private T[] innerArray;
  private int vacantIndex;

  public DynamicArray() {
    this(INITIAL_SIZE);
  }

  public DynamicArray(int initialSize) {
    innerArray = (T[])new Object[initialSize];

    vacantIndex = 0;
  }

  /**
   * Add given element to array.
   * @param element element to insert into array
   */
  @Override
  public void add(T element) {
    checkCapacity();

    innerArray[vacantIndex++] = element;
  }

  /**
   * Return element on specified position.
   * @param position position of element
   * @return element from array
   */
  @Override
  public T get(int position) {
    return innerArray[position];
  }

  /**
   * Return element on specified position and remove it from array
   * @param position position of element
   * @return removed element
   */
  @Override
  public T remove(int position) {
    T elementToRemove = get(position);

    updateArray(position);
    return elementToRemove;
  }

  @Override
  public String toString() {
    StringBuffer arrayString = new StringBuffer();

    Arrays.stream( Arrays.copyOfRange(innerArray, 0, vacantIndex))
        .forEach(d-> arrayString.append(d).append(" "));

    return String.format("[%s]", arrayString.toString().trim());
  }

  private void checkCapacity() {
    if (vacantIndex >= LOAD_FACTOR * innerArray.length) {
      innerArray = Arrays.copyOf(innerArray, innerArray.length * INCREASE);
    }
  }

  private void updateArray(int fromPosition) {
    for (int i = fromPosition + 1; i < innerArray.length; i++) {
      innerArray[i - 1] = innerArray[i];
    }
    vacantIndex--;
  }
}
