package com.safety.safetynet.dto;

import com.safety.safetynet.model.Person;
import java.util.List;


/**
 * The type Child alert.
 */
public class ChildAlert {
  /**
   * List of children.
   */
  private List<Child> children;
  /**
   * List of adults.
   */
  private List<Person> family;

  /**
   * Instantiates a new Child alert.
   */
  public ChildAlert() {
  }

  /**
   * Instantiates a new Child alert.
   *
   * @param children1 the children 1
   * @param family1   the family 1
   */
  public ChildAlert(final List<Child> children1, final List<Person> family1) {
    this.children = children1;
    this.family = family1;
  }

  /**
   * Gets children.
   *
   * @return the children
   */
  public List<Child> getChildren() {
    return children;
  }

  /**
   * Sets children.
   *
   * @param children1 the children 1
   */
  public void setChildren(final List<Child> children1) {
    this.children = children1;
  }

  /**
   * Gets family.
   *
   * @return the family
   */
  public List<Person> getFamily() {
    return family;
  }

  /**
   * Sets family.
   *
   * @param family1 the family 1
   */
  public void setFamily(final List<Person> family1) {
    this.family = family1;
  }

  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "ChildAlert{" + "children="
        + children.toString() + ", family=" + family.toString() + '}';
  }
}
