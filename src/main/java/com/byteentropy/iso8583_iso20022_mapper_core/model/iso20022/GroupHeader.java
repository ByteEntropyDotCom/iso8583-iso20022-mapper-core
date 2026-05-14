package com.byteentropy.iso8583_iso20022_mapper_core.model.iso20022;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record GroupHeader(
    @JacksonXmlProperty(localName = "MsgId")
    String messageId,
    
    @JacksonXmlProperty(localName = "CreDtTm")
    String creationDateTime
) {}