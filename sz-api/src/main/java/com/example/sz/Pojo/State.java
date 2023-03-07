package com.example.sz.Pojo;

import lombok.ToString;


@ToString
public class State {
    public String name;
    public String path;

    public State(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
