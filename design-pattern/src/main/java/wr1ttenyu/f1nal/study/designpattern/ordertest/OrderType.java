package wr1ttenyu.f1nal.study.designpattern.ordertest;

public enum OrderType {

    TICKET_CHARGE("TICKET_CHARGE"),

    M_PLUS_ORDER("TICKET_CHARGE");

    private String type;

    OrderType(String type) {
        this.type = type;
    }
}
