package com.domain;

public class address {
    private Integer id;

    private String name;

    private String province;

    private String city;

    private String area;

    private String address;

    private String username;

    private String userphone;

    private String provincenum;

    private String citynumber;

    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getProvincenum() {
        return provincenum;
    }

    public void setProvincenum(String provincenum) {
        this.provincenum = provincenum == null ? null : provincenum.trim();
    }

    public String getCitynumber() {
        return citynumber;
    }

    public void setCitynumber(String citynumber) {
        this.citynumber = citynumber == null ? null : citynumber.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}