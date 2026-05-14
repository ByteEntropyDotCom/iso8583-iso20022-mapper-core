package com.byteentropy.iso8583_iso20022_mapper_core.mapper.impl;

import com.byteentropy.iso8583_iso20022_mapper_core.mapper.RegionalMapper;
import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import com.byteentropy.iso8583_iso20022_mapper_core.model.Iso8583Fields;
import com.byteentropy.iso8583_iso20022_mapper_core.model.PaymentDefaults;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UsFedNowMapper extends RegionalMapper {
    private final XmlMapper xmlMapper;

    public UsFedNowMapper(@Qualifier("isoXmlMapper") XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public boolean supports(String region) {
        return "US_FEDNOW".equalsIgnoreCase(region);
    }

    @Override
    protected String mapToIso20022(BridgeContext context, String sanitizedPayload) {
        return constructXml(xmlMapper, 
            context.transactionId(), 
            PaymentDefaults.US_PREFIX.val() + context.transactionId(), 
            PaymentDefaults.US_CURRENCY.val(),
            safeGet(context, Iso8583Fields.AMOUNT, PaymentDefaults.DEFAULT_AMOUNT)
        );
    }
}