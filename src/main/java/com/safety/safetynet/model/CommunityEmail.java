package com.safety.safetynet.model;

import java.util.List;

public class CommunityEmail {
    List<String> email;

    public CommunityEmail(List<String> email) {
        this.email = email;
    }

    public CommunityEmail() {
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
