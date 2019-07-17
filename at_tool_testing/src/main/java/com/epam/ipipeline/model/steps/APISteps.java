package com.epam.ipipeline.model.steps;

import com.epam.ipipeline.model.ws.KeywordsRequests;
import com.epam.ipipeline.model.ws.beans.WSObjectAction;
import com.google.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class APISteps implements Steps {

  @Inject private KeywordsRequests requests;

  @Override
  public void addTestObject(String name, String parent) {
    requests.addTestObject(name, parent);
  }

  @Override
  public boolean hasTestObject(String name) {
    return requests.getTestObjects().stream().anyMatch(t -> name.equals(t.getName()));
  }

  @Override
  public List<String> getObjectActions(String object) {
    return requests.getObjectActions(object).stream()
        .map(WSObjectAction::getName)
        .collect(Collectors.toList());
  }
}
