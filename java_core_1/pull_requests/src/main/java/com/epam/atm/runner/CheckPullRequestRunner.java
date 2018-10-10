package com.epam.atm.runner;

import com.epam.atm.core.builder.PullRequestBuilder;
import com.epam.atm.core.github_entities.Author;
import com.epam.atm.core.github_entities.Comment;
import com.epam.atm.core.github_entities.Commit;
import com.epam.atm.core.github_entities.PullRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CheckPullRequestRunner {

  public static void main(String[] args) {
    Author tester = new Author("Petr Ivanov", "petr_ivanov@gmail.com");
    Author manager = new Author("John Doe", "john_doe@gmail.com");

    Commit commit1 = new Commit(tester);
    Comment comment1 = new Comment("First comment", manager);
    Commit commit2 = new Commit(tester);
    Comment comment2 = new Comment("Checked", manager);
    Commit commit3 = new Commit(tester);
    Comment comment3 = new Comment("Third comment", manager);
    Comment comment4 = new Comment("Checked", manager);

    PullRequest pullRequest1 = new PullRequestBuilder().withAuthor(tester).withBranchFrom("tests")
        .withBranchTo("master").withCommits(commit1, commit2, commit3)
        .withComments(comment2).build();

    //Checked
    PullRequest pullRequest2 = new PullRequestBuilder().withAuthor(tester).withBranchFrom("tests")
        .withBranchTo("master").withCommits(commit1, commit2, commit3)
        .withComments(comment2, comment4).build();

    //Checked
    PullRequest pullRequest3 = new PullRequestBuilder().withAuthor(tester).withBranchFrom("tests")
        .withBranchTo("master").withCommits(commit1, commit2)
        .withComments(comment1, comment2).build();

    PullRequest pullRequest4 = new PullRequestBuilder().withAuthor(tester).withBranchFrom("tests")
        .withBranchTo("master").withCommits(commit1, commit2, commit3)
        .withComments(comment1, comment3).build();

    PullRequest pullRequest5 = new PullRequestBuilder().withAuthor(tester).withBranchFrom("tests")
        .withBranchTo("master").withCommits(commit1, commit2, commit3)
        .withComments().build();

    Set<PullRequest> requests = new TreeSet<>();

    requests.addAll(getList(pullRequest1, pullRequest2, pullRequest3, pullRequest4, pullRequest5));

    Set<PullRequest> checked = requests.stream().filter(PullRequest::isChecked)
        .collect(Collectors.toSet());

    System.out.println(checked);
  }

  @SafeVarargs
  private static <T> List<T> getList(T...objects) {
    return Arrays.asList(objects);
  }
}
