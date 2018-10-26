package com.epam.atm.core.utils.services;

import static java.util.Arrays.stream;

import com.epam.atm.core.objects.Author;
import com.epam.atm.core.objects.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class for task1.
 */
public class AuthorService {

  private AuthorService(){}

  public static List<Author> sortAuthorsByName(Author[] authors) {
    return stream(authors).sorted(Comparator.comparing(Author::getName)).collect(Collectors.toList());
  }

  public static List<Author> sortAuthorsByAge(Author[] authors) {
    return stream(authors).sorted(Comparator.comparing(Author::getAge)).collect(Collectors.toList());
  }

  public static boolean authorHasBook(Author author, Book book) {
    return LibraryService.isTrue(author, book, (a, b) -> a.getBooks().contains(b));
  }
}
