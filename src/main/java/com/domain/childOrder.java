package com.domain;

public class childOrder {
    private Integer id;

    private Integer orderid;

    private Integer comid;

    private Integer commnum;

    private String comprice;

    private String total;

    private String comname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public Integer getCommnum() {
        return commnum;
    }

    public void setCommnum(Integer commnum) {
        this.commnum = commnum;
    }

    public String  getComprice() {
        return comprice;
    }

    public void setComprice(String  comprice) {
        this.comprice = comprice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname == null ? null : comname.trim();
    }
}