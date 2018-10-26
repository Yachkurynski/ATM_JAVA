package com.epam.atm.core.objects;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Book {

  @Getter
  @Setter
  private String title;
  @Getter
  @Setter
  private int numberOfPages;
  @Getter
  @Setter
  private List<Author> authors;

  public Book(String title, int numberOfPages) {
    this.numberOfPages = numberOfPages;
    this.title = title;
  }

  @Override
  public String toString() {
    return String.format("\"%s\" with %d pages", title, numberOfPages);
  }
}
