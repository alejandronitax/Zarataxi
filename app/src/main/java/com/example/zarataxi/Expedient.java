package com.example.zarataxi;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

public class Expedient {

    private String originDirection = "";
    private String endDirection = "";
    private String Hour = "";
    private String Date = "";
    private Boolean pets = false;
    private Boolean adaptedTaxi = false;
    private Boolean minivan = false;
    private String paymentType = "pago en efectivo" ;
    private Double money;

    public String getOriginDirection() {
        return originDirection;
    }

    public void setOriginDirection(String originDirection) {
        this.originDirection = originDirection;
    }

    public String getEndDirection() {
        return endDirection;
    }

    public void setEndDirection(String endDirection) {
        this.endDirection = endDirection;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Boolean getPets() {
        return pets;
    }

    public void setPets(Boolean pets) {
        this.pets = pets;
    }

    public Boolean getAdaptedTaxi() {
        return adaptedTaxi;
    }

    public void setAdaptedTaxi(Boolean adaptedTaxi) {
        this.adaptedTaxi = adaptedTaxi;
    }

    public Boolean getMinivan() {
        return minivan;
    }

    public void setMinivan(Boolean minivan) {
        this.minivan = minivan;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

}
