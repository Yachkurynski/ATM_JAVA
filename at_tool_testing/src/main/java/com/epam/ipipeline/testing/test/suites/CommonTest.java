package com.epam.ipipeline.testing.test.suites;

import com.epam.ipipeline.testing.test.model.KeywordsTest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javassist.util.proxy.ProxyFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public abstract class CommonTest {

  public abstract void steps();

  protected KeywordsTest keywordsTest() {
    return testObject(KeywordsTest.class);
  }

  protected <T> T testObject(Class<T> clazz) {
    ProxyFactory factory = new ProxyFactory();

    factory.setSuperclass(clazz);
    factory.setFilter(m->m.isAnnotationPresent(Test.class));

    try {
      return (T) factory.create(new Class<?>[0], new Object[0], (self, thisMethod, proceed, args) -> {
        Parameters paramNames = thisMethod.getAnnotation(Parameters.class);
        Map<String, String> params = new HashMap<>();
        AtomicInteger counter = new AtomicInteger();

        Arrays.stream(paramNames.value()).forEach(p->params.put(p, String.valueOf(counter.getAndIncrement())));



        return null;
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
