package com.safety.safetynet.dto;

import com.safety.safetynet.dto.Child;
import com.safety.safetynet.model.Person;

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

    @Override
    public String toString() {
        return "ChildAlert{" +
                "children=" + children.toString() +
                ", family=" + family.toString() +
                '}';
    }
}
