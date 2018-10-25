package com.epam.atm.core.github_entities;

import java.util.Objects;
import lombok.Getter;

public class Commit extends ComparableGithubEntity {

  @Getter
  private Author author;

  public Commit(Author author) {
    this.author = author;
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
    return Objects.equals(author, commit.getAuthor()) && Objects.equals(creationTime, commit.getCreationTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(author, creationTime);
  }
}
