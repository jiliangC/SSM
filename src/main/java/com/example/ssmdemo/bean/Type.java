package com.example.ssmdemo.bean;

public class Type {
    private Integer id;

    private String typename;

    public Type(String name) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Type(Integer id, String typename) {
        this.id = id;
        this.typename = typename;
    }

    public Type() {
    }
}