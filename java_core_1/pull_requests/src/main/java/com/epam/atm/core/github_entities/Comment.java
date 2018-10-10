package com.epam.atm.core.github_entities;

import java.util.Objects;

public class Comment extends ComparableGithubEntity {

  private String text;
  private Author author;

  public Comment(String text, Author author) {
    this.text = text;
    this.author = author;
  }

  public String getText() {
    return text;
  }

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
    Comment comment = (Comment) o;
    return Objects.equals(text, comment.text) &&
        Objects.equals(author, comment.author) &&
        Objects.equals(creationTime, comment.creationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, author, creationTime);
  }
}
