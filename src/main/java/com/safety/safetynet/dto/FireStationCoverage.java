package com.safety.safetynet.dto;

import java.util.List;

public class FireStationCoverage {
    List<PersonInfos> personInfosList;
    int child;
    int adult;

    public FireStationCoverage(List<PersonInfos> personInfosList, int child, int adult) {
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

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
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
