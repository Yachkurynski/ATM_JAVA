package com.epam.atm.core.utils;

import com.epam.atm.core.github_entities.Author;
import com.epam.atm.core.github_entities.Comment;
import com.epam.atm.core.github_entities.Commit;
import com.epam.atm.core.utils.exceptions.GitHubRuntimeException;
import java.lang.reflect.InvocationTargetException;

public class GithubEntitiesCreator {

  private GithubEntitiesCreator() {
  }

  public static Author getAuthor(String name, String email) {
    try {
      return Author.class.getConstructor(String.class, String.class).newInstance(name, email);
    } catch (IllegalAccessException | InstantiationException |NoSuchMethodException |
        InvocationTargetException e) {
      throw new GitHubRuntimeException("Can't instantiate Author!", e);
    }
  }

  public static Comment getComment(String text, Author author) {
    try {
      return Comment.class.getConstructor(String.class, Author.class).newInstance(text, author);
    } catch (IllegalAccessException | InstantiationException |NoSuchMethodException |
        InvocationTargetException e) {
      throw new GitHubRuntimeException("Can't instantiate Comment!", e);
    }
  }

  public static Commit getCommit(Author author) {
    try {
      return Commit.class.getConstructor(Author.class).newInstance(author);
    } catch (IllegalAccessException | InstantiationException |NoSuchMethodException |
        InvocationTargetException e) {
      throw new GitHubRuntimeException("Can't instantiate Commit!", e);
    }
  }

}
