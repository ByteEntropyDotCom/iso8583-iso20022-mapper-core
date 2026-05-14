package com.byteentropy.iso8583_iso20022_mapper_core.model.iso20022;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText; // Corrected path

/**
 * Represents an ISO 20022 ActiveOrHistoricCurrencyAndAmount structure.
 * Example: <InstdAmt Ccy="USD">100.00</InstdAmt>
 */
public record Amount(
    @JacksonXmlProperty(isAttribute = true, localName = "Ccy")
    String currency,
    
    @JacksonXmlText
    String value
) {}