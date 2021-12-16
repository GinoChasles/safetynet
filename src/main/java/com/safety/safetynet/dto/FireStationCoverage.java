package com.safety.safetynet.dto;

import java.util.List;

/**
 * The type Fire station coverage.
 */
public class FireStationCoverage {

  /**
   * Number of child.
   */
  private int child;
  /**
   * Number of adults.
   */
  private int adult;
  /**
   * PersonInfosList.
   */
  private List<PersonInfosForCoverage> personInfosForCoverageList;

  /**
   * Instantiates a new Fire station coverage.
   *
   * @param personInfosForCoverageList1Param the person infos list 1
   * @param child1           the child 1
   * @param adult1           the adult 1
   */
  public FireStationCoverage(final List<PersonInfosForCoverage>
      personInfosForCoverageList1Param,
                             final int child1, final int adult1) {
    this.personInfosForCoverageList = personInfosForCoverageList1Param;
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
  public List<PersonInfosForCoverage> getPersonInfosList() {
    return personInfosForCoverageList;
  }

  /**
   * Sets person infos list.
   *
   * @param personInfosForCoverageList1Param the person infos list 1
   */
  public void setPersonInfosList(final List<PersonInfosForCoverage>
      personInfosForCoverageList1Param) {
    this.personInfosForCoverageList = personInfosForCoverageList1Param;
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
        + "personInfosForCoverageList=" + personInfosForCoverageList.toString()
        + ", child=" + child + ", adult=" + adult + '}';
  }

}
