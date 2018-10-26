package com.epam.atm.runner;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

import com.epam.atm.core.objects.Author;
import com.epam.atm.core.objects.Book;
import com.epam.atm.core.utils.services.AuthorService;
import com.epam.atm.core.utils.services.BookService;
import java.util.Comparator;
import java.util.List;

public class BooksCollection {


  public static void main(String[] args) {
    Book book1 = new Book("First", 300);
    Book book2 = new Book("Second", 200);
    Book book3 = new Book("Third", 100);

    Author author1 = new Author("Petr", (short)25);
    Author author2 = new Author("Ivan", (short)30);
    Author author3 = new Author("Vovan", (short)35);

    book1.setAuthors(asList(author1, author3));
    book2.setAuthors(asList(author2, author3));
    book3.setAuthors(asList(author1, author2));

    author1.setBooks(asList(book1, book3));
    author2.setBooks(asList(book2, book3));
    author3.setBooks(asList(book1, book2));

    Book[] books = new Book[]{book1, book2, book3};
    Author[] authors = new Author[]{author1, author2, author3};

    System.out.println("All books: ");
    stream(books).forEach(System.out::println);

    System.out.println("Books with more than 200 pages: ");
    BookService.getBigBooks(books, 200).forEach(System.out::println);

    System.out.println("Biggest book: " + BookService.getMaxBookBy(books, Comparator.comparingInt(Book::getNumberOfPages)));
    System.out.println("Smallest book: " + BookService.getMinBookBy(books, Comparator.comparingInt(Book::getNumberOfPages)));

    System.out.println("Number of books with 1 author: " + BookService.getBooksWithOneAuthor(books).size());

    System.out.println("Sort by number of pages: ");
    BookService.sortByNumberOfPages(books).forEach(System.out::println);

    System.out.println("All authors");
    stream(authors).forEach(System.out::println);

    System.out.println("Sort by name");
    AuthorService.sortAuthorsByName(authors).forEach(System.out::println);

    System.out.println("Sort by age");
    AuthorService.sortAuthorsByAge(authors).forEach(System.out::println);
  }

  private static void printList(List<String> list) {
    list.forEach(System.out::println);
  }

}
