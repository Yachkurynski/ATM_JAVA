package com.epam.atm.runner;

import static com.epam.atm.core.utils.GithubEntitiesCreator.getAuthor;
import static com.epam.atm.core.utils.GithubEntitiesCreator.getComment;
import static com.epam.atm.core.utils.GithubEntitiesCreator.getCommit;

import com.epam.atm.core.github_entities.Author;
import com.epam.atm.core.github_entities.Comment;
import com.epam.atm.core.github_entities.Commit;
import com.epam.atm.core.github_entities.PullRequest;
import com.epam.atm.core.utils.MetadataPrinter;
import com.epam.atm.core.utils.builder.PullRequestBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CheckPullRequestRunner {

  public static void main(String[] args) {
    Author tester = getAuthor("Petr Ivanov", "petr_ivanov@gmail.com");
    Author manager = getAuthor("John Doe", "john_doe@gmail.com");

    Commit commit1 = getCommit(tester);
    Comment comment1 = getComment("First comment", manager);
    Commit commit2 = getCommit(tester);
    Comment comment2 = getComment("Checked", manager);
    Commit commit3 = getCommit(tester);
    Comment comment3 = getComment("Third comment", manager);
    Comment comment4 = getComment("Checked", manager);

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

    Set<PullRequest> requests = new TreeSet<>(
        getList(pullRequest1, pullRequest2, pullRequest3, pullRequest4, pullRequest5));

    Set<PullRequest> checked = requests.stream().filter(PullRequest::isChecked)
        .collect(Collectors.toSet());

    System.out.println(checked);

    MetadataPrinter.print(Author.class);
    MetadataPrinter.print(Comment.class);
    MetadataPrinter.print(Commit.class);
    MetadataPrinter.print(PullRequest.class);

  }

  @SafeVarargs
  private static <T> List<T> getList(T...objects) {
    return Arrays.asList(objects);
  }


}
