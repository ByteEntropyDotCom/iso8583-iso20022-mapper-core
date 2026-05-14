package com.byteentropy.iso8583_iso20022_mapper_core.model;

public enum PaymentDefaults {
    DEFAULT_AMOUNT("0.00"),
    UK_CURRENCY("GBP"),
    SG_CURRENCY("SGD"),
    US_CURRENCY("USD"),
    UK_PREFIX("UK-FPS-"),
    US_PREFIX("FEDNOW-"),
    SG_UNKNOWN_STAN("SG_UNKN");

    private final String value;
    PaymentDefaults(String value) { this.value = value; }
    public String val() { return value; }
}