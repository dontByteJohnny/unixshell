package com.mulesoft.model;

public class PathOrFolder {
    private String name;
    private String type;

    public PathOrFolder(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

}