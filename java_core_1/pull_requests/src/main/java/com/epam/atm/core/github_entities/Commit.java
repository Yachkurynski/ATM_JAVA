package com.epam.atm.core.github_entities;

import java.util.Objects;

public class Commit extends ComparableGithubEntity {

  private Author author;

  public Commit(Author author) {
    this.author = author;
  }

  @SuppressWarnings("unused")
  public Author getAuthor() {
    return author;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Commit commit = (Commit) o;
    return Objects.equals(author, commit.author) && Objects.equals(creationTime, commit.creationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, creationTime);
  }
}
