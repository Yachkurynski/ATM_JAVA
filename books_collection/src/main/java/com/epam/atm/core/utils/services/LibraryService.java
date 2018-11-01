package com.epam.atm.core.utils.services;

import com.epam.atm.core.objects.Author;
import com.epam.atm.core.objects.Book;
import com.epam.atm.core.utils.function.ThreeFunction;

public class LibraryService {

  public static boolean isTrue(
      Author author, Book book, ThreeFunction<Author, Book, Boolean> function) {
    return function.perform(author, book);
  }
}
