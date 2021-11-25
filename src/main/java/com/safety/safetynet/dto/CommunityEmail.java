package com.safety.safetynet.dto;

import java.util.List;

/**
 * The type Community email.
 */
public class CommunityEmail {

  /**
   * List of emails.
   */
  private List<String> email;

  /**
   * Instantiates a new Community email.
   *
   * @param email1 the email 1
   */
  public CommunityEmail(final List<String> email1) {
    this.email = email1;
  }

  /**
   * Instantiates a new Community email.
   */
  public CommunityEmail() {
  }

  /**
   * Gets email.
   *
   * @return the email
   */
  public List<String> getEmail() {
    return email;
  }

  /**
   * Sets email.
   *
   * @param email1 the email 1
   */
  public void setEmail(final List<String> email1) {
    this.email = email1;
  }


  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "CommunityEmail{" + "email=" + email + '}';
  }
}
