package com.domain;

public class area {
    private String areanumber;

    private String areaname;

    private Integer citynumber;

    public String getAreanumber() {
        return areanumber;
    }

    public void setAreanumber(String areanumber) {
        this.areanumber = areanumber == null ? null : areanumber.trim();
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    public Integer getCitynumber() {
        return citynumber;
    }

    public void setCitynumber(Integer citynumber) {
        this.citynumber = citynumber;
    }
}