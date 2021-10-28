package com.safety.safetynet.dto;

import java.util.List;

public class FireStationCoverage {
    List<PersonInfos> personInfosList;
    long child;
    long adult;

    public FireStationCoverage(List<PersonInfos> personInfosList, long child, long adult) {
        this.personInfosList = personInfosList;
        this.child = child;
        this.adult = adult;
    }

    public FireStationCoverage() {
    }

    public List<PersonInfos> getPersonInfosList() {
        return personInfosList;
    }

    public void setPersonInfosList(List<PersonInfos> personInfosList) {
        this.personInfosList = personInfosList;
    }

    public long getChild() {
        return child;
    }

    public void setChild(long child) {
        this.child = child;
    }

    public long getAdult() {
        return adult;
    }

    public void setAdult(long adult) {
        this.adult = adult;
    }

    @Override
    public String toString() {
        return "FireStationCoverage{" +
                "personInfosList=" + personInfosList.toString() +
                ", child=" + child +
                ", adult=" + adult +
                '}';
    }

}
