package com.nest5.businessClient;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DAILY_SALE.
 */
public class DailySale {

    private Long id;
    private long syncId;
    private Integer isDelivery;
    private Integer isTogo;
    private Integer tip;
    private Integer number;
    private String method;
    private Double received;
    private Double discount;
    private java.util.Date date;

    public DailySale() {
    }

    public DailySale(Long id) {
        this.id = id;
    }

    public DailySale(Long id, long syncId, Integer isDelivery, Integer isTogo, Integer tip, Integer number, String method, Double received, Double discount, java.util.Date date) {
        this.id = id;
        this.syncId = syncId;
        this.isDelivery = isDelivery;
        this.isTogo = isTogo;
        this.tip = tip;
        this.number = number;
        this.method = method;
        this.received = received;
        this.discount = discount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSyncId() {
        return syncId;
    }

    public void setSyncId(long syncId) {
        this.syncId = syncId;
    }

    public Integer getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Integer isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Integer getIsTogo() {
        return isTogo;
    }

    public void setIsTogo(Integer isTogo) {
        this.isTogo = isTogo;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

}
