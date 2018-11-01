package com.epam.atm.core.github_entities;

import java.util.Objects;
import lombok.Getter;

public class Comment extends ComparableGithubEntity {

  @Getter
  private String text;
  @Getter
  private Author author;

  public Comment(String text, Author author) {
    this.text = text;
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
    Comment comment = (Comment) o;
    return Objects.equals(text, comment.getText()) &&
        Objects.equals(author, comment.getAuthor()) &&
        Objects.equals(creationTime, comment.getCreationTime());
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, author, creationTime);
  }
}
