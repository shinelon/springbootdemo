package com.spring.enums;

public enum OrderSeq {

    ASC("DESC"),
    DESC("DESC");

    private final String seq;

    private OrderSeq(String seq) {
        this.seq = seq;
    }

    public String getSeq() {
        return seq;
    }
}
