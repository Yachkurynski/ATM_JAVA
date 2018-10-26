package com.epam.atm.core.utils.services;

import static java.util.Arrays.stream;

import com.epam.atm.core.objects.Author;
import com.epam.atm.core.utils.annotations.UseConsumer;
import com.epam.atm.core.utils.annotations.UseFunction;
import com.epam.atm.core.utils.annotations.UsePredicate;
import com.epam.atm.core.objects.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Util class for task2.
 */
public class BookService {

  private BookService(){}

  @UsePredicate
  @UseConsumer
  public static List<Book> getBigBooks(Book[] books, int minNumberOfPages) {
    return stream(books).filter(b->b.getNumberOfPages() > minNumberOfPages).collect(Collectors.toList());
  }

  public static Book getMaxBookBy(Book[] books, Comparator<? super Book> comparator) {
    return stream(books).max(comparator).get();
  }

  public static Book getMinBookBy(Book[] books, Comparator<? super Book> comparator) {
    return stream(books).min(comparator).get();
  }

  @UsePredicate
  public static List<Book> getBooksWithOneAuthor(Book[] books) {
    return stream(books).filter(b->b.getAuthors().size() == 1).collect(Collectors.toList());
  }

  public static List<Book> sortByNumberOfPages(Book[] books) {
    return stream(books).sorted(Comparator.comparingInt(Book::getNumberOfPages))
        .collect(Collectors.toList());
  }

  @UseFunction
  public static List<String> getAllTitles(Book[] books) {
    return stream(books).map(Book::getTitle).collect(Collectors.toList());
  }

  public static boolean bookAuthorIs(Book book, Author author) {
    return LibraryService.isTrue(author, book, (a, b) -> {
      List<Author> authors = b.getAuthors();

      if (authors.size() != 1) {
        return false;
      } else {
        return a.equals(authors.get(0));
      }
    });
  }
}
