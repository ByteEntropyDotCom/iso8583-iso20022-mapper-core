package com.byteentropy.iso8583_iso20022_mapper_core.model.iso20022;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record CreditTransferTransactionInformation(
    @JacksonXmlProperty(localName = "PmtId")
    PaymentIdentification paymentId,
    
    @JacksonXmlProperty(localName = "InstdAmt")
    Amount instructedAmount
) {}