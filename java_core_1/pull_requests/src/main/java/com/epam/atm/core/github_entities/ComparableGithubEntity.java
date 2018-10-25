package com.epam.atm.core.github_entities;

import com.epam.atm.core.github_entities.interfaces.WithDateTime;
import java.util.Date;
import lombok.Getter;

public class ComparableGithubEntity implements WithDateTime, Comparable<ComparableGithubEntity> {

  private long dateTime;
  @Getter
  protected long creationTime;

  public ComparableGithubEntity() {
    this.dateTime = System.currentTimeMillis();
    this.creationTime = System.nanoTime();
  }

  @Override
  public Date getDate() {
    return new Date(dateTime);
  }

  @Override
  public int compareTo(ComparableGithubEntity other) {
    return Long.compare(creationTime, other.getCreationTime());
  }
}
