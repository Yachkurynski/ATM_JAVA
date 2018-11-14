package com.epam.atm.core.github_entities;

import com.epam.atm.core.utils.annotations.UseTreeOnly;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.Setter;

public class PullRequest extends ComparableGithubEntity {

  private static final String CHECKED_REQUEST_COMMENT = "Checked";
  private static final String REQUEST_MESSAGE_PATTERN =
      "%s wants to merge %d commits into %s from %s; [%s]";

  @Setter
  private Author author;
  @Getter
  @UseTreeOnly
  private TreeSet<Commit> commits;
  @UseTreeOnly
  private TreeSet<Comment> comments;
  @Setter
  private Date date;
  @Setter
  private String branchFrom;
  @Setter
  private String branchTo;

  public PullRequest() {
    commits = new TreeSet<>();
    comments = new TreeSet<>();
  }

  public boolean isChecked() {
    if (comments.isEmpty()) {
      return false;
    }

    Commit lastCommit = commits.last();
    Comment lastComment = comments.last();

    return lastComment.compareTo(lastCommit) > 0 &&
        lastComment.getText().equals(CHECKED_REQUEST_COMMENT);
  }

  public void addCommits(Set<Commit> commits) {
    this.commits.addAll(commits);
  }

  public void addComments(Set<Comment> comments) {
    this.comments.addAll(comments);
  }

  @Override
  public String toString() {
    return String.format(REQUEST_MESSAGE_PATTERN, author.getName(), commits.size(), branchTo,
        branchFrom, date);
  }
}
