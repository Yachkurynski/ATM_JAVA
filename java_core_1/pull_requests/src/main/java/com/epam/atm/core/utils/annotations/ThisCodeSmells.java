package com.epam.atm.core.utils.annotations;

import java.lang.annotation.Inherited;

@Inherited
public @interface ThisCodeSmells {
  String reviewer() default "Petya";
}
