package com.epam.atm.core.builder;

import com.epam.atm.core.exceptions.GitHubRuntimeException;
import com.epam.atm.core.github_entities.Author;
import com.epam.atm.core.github_entities.Comment;
import com.epam.atm.core.github_entities.Commit;
import com.epam.atm.core.github_entities.PullRequest;
import java.util.Date;
import java.util.Set;

public class PullRequestBuilder {

  private PullRequest pullRequest;

  public PullRequestBuilder() {
    pullRequest = new PullRequest();
  }

  public PullRequestBuilder withAuthor(Author author) {
    pullRequest.setAuthor(author);
    return this;
  }

  public PullRequestBuilder withCommits(Commit...commits) {
    pullRequest.addCommits(Set.of(commits));
    return this;
  }

  public PullRequestBuilder withComments(Comment...comments) {
    pullRequest.addComments(Set.of(comments));
    return this;
  }

  public PullRequestBuilder withBranchFrom(String branchFrom) {
    pullRequest.setBranchFrom(branchFrom);
    return this;
  }

  public PullRequestBuilder withBranchTo(String branchTo) {
    pullRequest.setBranchTo(branchTo);
    return this;
  }

  public PullRequest build() {
    if (pullRequest.getCommits().isEmpty()) {
      throw new GitHubRuntimeException("There is NO commits for pull request.");
    }
    pullRequest.setDate(new Date());
    return pullRequest;
  }

}
