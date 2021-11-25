package com.safety.safetynet.dto;

import java.util.List;

/**
 * The type Fire station coverage.
 */
public class FireStationCoverage {
  /**
   * PersonInfosList.
   */
  private List<PersonInfos> personInfosList;
  /**
   * Number of child.
   */
  private int child;
  /**
   * Number of adults.
   */
  private int adult;

  /**
   * Instantiates a new Fire station coverage.
   *
   * @param personInfosList1 the person infos list 1
   * @param child1           the child 1
   * @param adult1           the adult 1
   */
  public FireStationCoverage(final List<PersonInfos> personInfosList1,
                             final int child1, final int adult1) {
    this.personInfosList = personInfosList1;
    this.child = child1;
    this.adult = adult1;
  }

  /**
   * Instantiates a new Fire station coverage.
   */
  public FireStationCoverage() {
  }

  /**
   * Gets person infos list.
   *
   * @return the person infos list
   */
  public List<PersonInfos> getPersonInfosList() {
    return personInfosList;
  }

  /**
   * Sets person infos list.
   *
   * @param personInfosList1 the person infos list 1
   */
  public void setPersonInfosList(final List<PersonInfos> personInfosList1) {
    this.personInfosList = personInfosList1;
  }

  /**
   * Gets child.
   *
   * @return the child
   */
  public int getChild() {
    return child;
  }

  /**
   * Sets child.
   *
   * @param child1 the child 1
   */
  public void setChild(final int child1) {
    this.child = child1;
  }

  /**
   * Gets adult.
   *
   * @return the adult
   */
  public int getAdult() {
    return adult;
  }

  /**
   * Sets adult.
   *
   * @param adult1 the adult 1
   */
  public void setAdult(final int adult1) {
    this.adult = adult1;
  }

  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "FireStationCoverage{"
        + "personInfosList=" + personInfosList.toString()
        + ", child=" + child + ", adult=" + adult + '}';
  }

}
