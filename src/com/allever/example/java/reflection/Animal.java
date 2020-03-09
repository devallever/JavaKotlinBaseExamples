package com.allever.example.java.reflection;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class Animal {
    private String priField;
    protected String name;
    public String head;

    public List<Cat> cats = new ArrayList<>();
}
