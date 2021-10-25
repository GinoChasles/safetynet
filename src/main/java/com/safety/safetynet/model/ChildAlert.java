package com.safety.safetynet.model;

import java.util.List;


public class ChildAlert {
    List<Child> children;
    List<Person> family;

    public ChildAlert() {
    }

    public ChildAlert(List<Child> children, List<Person> family) {
        this.children = children;
        this.family = family;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Person> getFamily() {
        return family;
    }

    public void setFamily(List<Person> family) {
        this.family = family;
    }
}
