package com.domain;

public class commodityWithBLOBs extends commodity {
    private String commCatalog;

    private String preface;

    public String getCommCatalog() {
        return commCatalog;
    }

    public void setCommCatalog(String commCatalog) {
        this.commCatalog = commCatalog == null ? null : commCatalog.trim();
    }

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface == null ? null : preface.trim();
    }
}