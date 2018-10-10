package com.epam.atm.core.github_entities;

import com.epam.atm.core.github_entities.interfaces.WithDateTime;
import java.util.Date;

public class ComparableGithubEntity implements WithDateTime, Comparable<ComparableGithubEntity> {

  protected long dateTime;
  protected long creationTime;

  public ComparableGithubEntity() {
    this.dateTime = System.currentTimeMillis();
    this.creationTime = System.nanoTime();
  }

  @Override
  public Date getDate() {
    return new Date(dateTime);
  }

  public long getCreationTime() {
    return creationTime;
  }

  @Override
  public int compareTo(ComparableGithubEntity other) {
    return Long.compare(creationTime, other.getCreationTime());
  }
}
