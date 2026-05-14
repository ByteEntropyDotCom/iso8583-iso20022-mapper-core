package com.byteentropy.iso8583_iso20022_mapper_core.mapper.impl;

import com.byteentropy.iso8583_iso20022_mapper_core.mapper.RegionalMapper;
import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import com.byteentropy.iso8583_iso20022_mapper_core.model.Iso8583Fields;
import com.byteentropy.iso8583_iso20022_mapper_core.model.PaymentDefaults;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SgFastMapper extends RegionalMapper {
    private final XmlMapper xmlMapper;

    public SgFastMapper(@Qualifier("isoXmlMapper") XmlMapper xmlMapper) {
        this.xmlMapper = xmlMapper;
    }

    @Override
    public boolean supports(String region) {
        return "SG_FAST".equalsIgnoreCase(region);
    }

    @Override
    protected String mapToIso20022(BridgeContext context, String sanitizedPayload) {
        return constructXml(xmlMapper, 
            context.transactionId(), 
            safeGet(context, Iso8583Fields.STAN, PaymentDefaults.SG_UNKNOWN_STAN), 
            safeGet(context, Iso8583Fields.CURRENCY, PaymentDefaults.SG_CURRENCY),
            safeGet(context, Iso8583Fields.AMOUNT, PaymentDefaults.DEFAULT_AMOUNT)
        );
    }
}