package com.byteentropy.iso8583_iso20022_mapper_core.model;

/**
 * Standard ISO 8583 Field definitions.
 * These constants act as the keys for our metadata map.
 */
public final class Iso8583Fields {
    private Iso8583Fields() {}

    public static final String MTI = "MTI";               
    public static final String PAN = "F2_PAN";            
    public static final String AMOUNT = "F4_AMOUNT";      
    public static final String TRANSMISSION_DT = "F7_DT"; 
    public static final String STAN = "F11_STAN";         
    public static final String CURRENCY = "F49_CURRENCY"; 
}