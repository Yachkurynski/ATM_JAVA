package com.epam.atm.core.github_entities.interfaces;

import java.util.Date;

@FunctionalInterface
public interface WithDateTime {

  @SuppressWarnings("unused")
  Date getDate();
}
