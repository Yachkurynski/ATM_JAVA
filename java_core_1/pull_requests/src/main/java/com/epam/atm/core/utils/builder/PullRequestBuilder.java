package com.epam.atm.core.utils.builder;

import com.epam.atm.core.utils.annotations.ProdCode;
import com.epam.atm.core.utils.annotations.ThisCodeSmells;
import com.epam.atm.core.utils.annotations.UseList;
import com.epam.atm.core.utils.exceptions.GitHubRuntimeException;
import com.epam.atm.core.github_entities.Author;
import com.epam.atm.core.github_entities.Comment;
import com.epam.atm.core.github_entities.Commit;
import com.epam.atm.core.github_entities.PullRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class PullRequestBuilder {

  private PullRequest pullRequest;

  public PullRequestBuilder() {
    pullRequest = new PullRequest();
  }

  @ProdCode
  public PullRequestBuilder withAuthor(Author author) {
    return setField("author", author);
  }

  /**
   * @see com.epam.atm.core.utils.builder.PullRequestBuilder#withCommits(Commit...) and use it instead
   */
  @Deprecated
  @UseList
  @ThisCodeSmells(reviewer = "Pasha")
  public PullRequestBuilder withCommit(Commit commit) {
    pullRequest.addCommits(new TreeSet<>(Collections.singletonList(commit)));
    return this;
  }

  @ProdCode
  public PullRequestBuilder withCommits(Commit...commits) {
    return invokeMethod("addCommits", Set.of(commits));
  }

  /**
   * @see com.epam.atm.core.utils.builder.PullRequestBuilder#withComments(Comment...) and use it instead
   */
  @Deprecated
  @UseList
  @ThisCodeSmells(reviewer = "Pasha")
  public PullRequestBuilder withComment(Comment comment) {
    pullRequest.addComments(new TreeSet<>(Collections.singletonList(comment)));
    return this;
  }

  @ProdCode
  public PullRequestBuilder withComments(Comment...comments) {
    return invokeMethod("addComments", Set.of(comments));
  }

  @ProdCode
  public PullRequestBuilder withBranchFrom(String branchFrom) {
    return setField("branchFrom", branchFrom);
  }

  @ProdCode
  public PullRequestBuilder withBranchTo(String branchTo) {
    return setField("branchTo", branchTo);
  }

  private PullRequestBuilder setField(String fieldName, Object param) {
    for (Field field : pullRequest.getClass().getDeclaredFields()) {
      if(field.getName().equals(fieldName)) {
        try {
          field.setAccessible(true);
          field.set(pullRequest, param);
        } catch (IllegalAccessException e) {
          throw new GitHubRuntimeException("Can't set the field " + fieldName, e);
        }
      }
    }
    return this;
  }

  private PullRequestBuilder invokeMethod(String methodName, Object param) {
    for (Method method : pullRequest.getClass().getMethods()) {
      if(method.getName().equals(methodName)) {
        try {
          method.invoke(pullRequest, param);
        } catch (InvocationTargetException | IllegalAccessException e) {
          throw new GitHubRuntimeException("Can't invoke the method " + methodName, e);
        }
      }
    }
    return this;
  }

  @ProdCode
  public PullRequest build() {
    if (pullRequest.getCommits().isEmpty()) {
      throw new GitHubRuntimeException("There is NO commits for pull request.");
    }
    pullRequest.setDate(new Date());
    return pullRequest;
  }

}
