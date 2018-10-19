package com.epam.atm.core.github_entities;

import com.epam.atm.core.utils.annotations.UseTreeOnly;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class PullRequest extends ComparableGithubEntity {

  private static final String CHECKED_REQUEST_COMMENT = "Checked";
  private static final String REQUEST_MESSAGE_PATTERN =
      "%s wants to merge %d commits into %s from %s; [%s]";

  private Author author;
  @UseTreeOnly
  private TreeSet<Commit> commits;
  @UseTreeOnly
  private TreeSet<Comment> comments;
  private Date date;
  private String branchFrom;
  private String branchTo;

  public PullRequest() {
    commits = new TreeSet<>();
    comments = new TreeSet<>();
  }

  @SuppressWarnings("unused")
  public Author getAuthor() {
    return author;
  }

  public Set<Commit> getCommits() {
    return commits;
  }

  @SuppressWarnings("unused")
  public Set<Comment> getComments() {
    return comments;
  }

  public Date getDate() {
    return date;
  }

  @SuppressWarnings("unused")
  public String getBranchFrom() {
    return branchFrom;
  }

  @SuppressWarnings("unused")
  public String getBranchTo() {
    return branchTo;
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

  public void setAuthor(Author author) {
    this.author = author;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setBranchFrom(String branchFrom) {
    this.branchFrom = branchFrom;
  }

  public void setBranchTo(String branchTo) {
    this.branchTo = branchTo;
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
