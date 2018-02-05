package com.domain;

public class province {
    private String provincenumber;

    private String provincename;

    public String getProvincenumber() {
        return provincenumber;
    }

    public void setProvincenumber(String provincenumber) {
        this.provincenumber = provincenumber == null ? null : provincenumber.trim();
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }
}