package com.safety.safetynet.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The type Fire station.
 */
@Entity
public class MedicalRecord {
  /**
   * Id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;

  /**
   * Address.
   */
  private String address;
  /**
   * StationNumber.
   */
  private int station;

  /**
   * Instantiates a new Fire station.
   *
   * @param address1 the address 1
   * @param station1 the station 1
   */
  public MedicalRecord(final String address1, final int station1) {
    this.address = address1;
    this.station = station1;
  }

  /**
   * Instantiates a new Fire station.
   *
   * @param id1      the id 1
   * @param address1 the address 1
   * @param station1 the station 1
   */
  public MedicalRecord(final int id1, final String address1,
                       final int station1) {
    this.id = id1;
    this.address = address1;
    this.station = station1;
  }

  /**
   * Instantiates a new Fire station.
   */
  public MedicalRecord() {

  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id1 the id 1
   */
  public void setId(final int id1) {
    this.id = id1;
  }

  /**
   * Gets address.
   *
   * @return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets address.
   *
   * @param address1 the address 1
   */
  public void setAddress(final String address1) {
    this.address = address1;
  }

  /**
   * Gets station.
   *
   * @return the station
   */
  public int getStation() {
    return station;
  }

  /**
   * Sets station.
   *
   * @param station1 the station 1
   */
  public void setStation(final int station1) {
    this.station = station1;
  }

  /**
   * ToString method.
   *
   * @return String
   */
  @Override
  public String toString() {
    return "FireStation{" + "address='" + address
        + '\'' + ", station=" + station + '}';
  }
}
