package com.domain;

public class city {
    private String citynumber;

    private String cityname;

    private String provicenum;

    public String getCitynumber() {
        return citynumber;
    }

    public void setCitynumber(String citynumber) {
        this.citynumber = citynumber == null ? null : citynumber.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getProvicenum() {
        return provicenum;
    }

    public void setProvicenum(String provicenum) {
        this.provicenum = provicenum == null ? null : provicenum.trim();
    }
}