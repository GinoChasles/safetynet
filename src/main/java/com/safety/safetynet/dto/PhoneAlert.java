package com.safety.safetynet.dto;

import java.util.List;

/**
 * The type Phone alert.
 */
public class PhoneAlert {
  /**
   * PhoneList.
   */
  private List<String> phoneList;

  /**
   * Instantiates a new Phone alert.
   *
   * @param phoneList1 the phone list 1
   */
  public PhoneAlert(final List<String> phoneList1) {
    this.phoneList = phoneList1;
  }

  /**
   * Instantiates a new Phone alert.
   */
  public PhoneAlert() {
  }

  /**
   * Gets phone list.
   *
   * @return the phone list
   */
  public List<String> getPhoneList() {
    return phoneList;
  }

  /**
   * Sets phone list.
   *
   * @param phoneList1 the phone list 1
   */
  public void setPhoneList(final List<String> phoneList1) {
    this.phoneList = phoneList1;
  }

  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "PhoneAlert{"
        + "phoneList=" + phoneList + '}';
  }
}
