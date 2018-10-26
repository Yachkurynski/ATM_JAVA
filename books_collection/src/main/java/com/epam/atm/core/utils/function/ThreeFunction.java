package com.epam.atm.core.utils.function;

import com.epam.atm.core.objects.Author;
import com.epam.atm.core.objects.Book;

@FunctionalInterface
public interface ThreeFunction<A, B, C> {

  C perform(A a, B b);

  default boolean instanceOfAuthor(A a) {
    return a instanceof Author;
  }

  default boolean instanceOfBook(B b) {
    return b instanceof Book;
  }
}
