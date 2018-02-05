package com.domain;

public class commodity {
    private Integer id;

    private String commName;

    private String commPrice;

    private String author;

    private String press;

    private String pubData;

    private String isbn;

    private String commLabel;

    private String pictureName;

    private String score;

    private String parentCatalogNum;

    private String childCatalogNum;

    private Integer status;

    private Integer store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName == null ? null : commName.trim();
    }

    public String getCommPrice() {
        return commPrice;
    }

    public void setCommPrice(String commPrice) {
        this.commPrice = commPrice == null ? null : commPrice.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press == null ? null : press.trim();
    }

    public String getPubData() {
        return pubData;
    }

    public void setPubData(String pubData) {
        this.pubData = pubData == null ? null : pubData.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public String getCommLabel() {
        return commLabel;
    }

    public void setCommLabel(String commLabel) {
        this.commLabel = commLabel == null ? null : commLabel.trim();
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getParentCatalogNum() {
        return parentCatalogNum;
    }

    public void setParentCatalogNum(String parentCatalogNum) {
        this.parentCatalogNum = parentCatalogNum == null ? null : parentCatalogNum.trim();
    }

    public String getChildCatalogNum() {
        return childCatalogNum;
    }

    public void setChildCatalogNum(String childCatalogNum) {
        this.childCatalogNum = childCatalogNum == null ? null : childCatalogNum.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }
}