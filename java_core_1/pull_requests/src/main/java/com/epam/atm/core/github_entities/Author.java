package com.epam.atm.core.github_entities;

import lombok.Getter;

public class Author {

  @Getter
  private String name;
  @Getter
  private String email;

  public Author(String name, String email) {
    this.name = name;
    this.email = email;
  }

}
