package com.allever.example.java.annotation;

import java.lang.annotation.Repeatable;

public @interface Persons {
    Person[] value();
}
