package com.epam.atm.core.objects;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Author {

  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private short age;
  @Getter
  @Setter
  private List<Book> books;

  public Author(String name, short age) {
    this.age = age;
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("%s - %d years old", name, age);
  }
}
