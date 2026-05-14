package com.byteentropy.iso8583_iso20022_mapper_core.mapper.impl;

import com.byteentropy.iso8583_iso20022_mapper_core.mapper.RegionalMapper;
import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import com.byteentropy.iso8583_iso20022_mapper_core.model.Iso8583Fields;
import com.byteentropy.iso8583_iso20022_mapper_core.model.PaymentDefaults;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UkFpsMapper extends RegionalMapper {
    private final XmlMapper xmlMapper;

    public UkFpsMapper(@Qualifier("isoXmlMapper") XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public boolean supports(String region) {
        return "UK_FPS".equalsIgnoreCase(region);
    }

    @Override
    protected String mapToIso20022(BridgeContext context, String sanitizedPayload) {
        return constructXml(xmlMapper, 
            context.transactionId(), 
            PaymentDefaults.UK_PREFIX.val() + context.transactionId(), 
            safeGet(context, Iso8583Fields.CURRENCY, PaymentDefaults.UK_CURRENCY),
            safeGet(context, Iso8583Fields.AMOUNT, PaymentDefaults.DEFAULT_AMOUNT)
        );
    }
}