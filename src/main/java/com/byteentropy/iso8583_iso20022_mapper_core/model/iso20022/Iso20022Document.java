package com.byteentropy.iso8583_iso20022_mapper_core.model.iso20022;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Document")
public record Iso20022Document(
    @JacksonXmlProperty(localName = "GrpHdr")
    GroupHeader groupHeader,
    
    @JacksonXmlProperty(localName = "CdtTrfTxInf")
    CreditTransferTransactionInformation transactionInfo
) {}