package com.byteentropy.iso8583_iso20022_mapper_core.mapper;

import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import com.byteentropy.iso8583_iso20022_mapper_core.model.PaymentDefaults;
import com.byteentropy.iso8583_iso20022_mapper_core.model.iso20022.*;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class RegionalMapper {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 1. Mandatory for Orchestrator to identify the region
    public abstract boolean supports(String region);

    // 2. This is what the Orchestrator calls!
    public String process(BridgeContext context) {
        logger.info("Executing on thread: {} | TXN: {}", Thread.currentThread(), context.transactionId());
        return mapToIso20022(context, context.rawPayload());
    }

    // 3. To be implemented by UkFpsMapper, SgFastMapper, etc.
    protected abstract String mapToIso20022(BridgeContext context, String sanitizedPayload);

    // Helper to handle nulls and casting safely
    protected String safeGet(BridgeContext context, String field, PaymentDefaults defaultValue) {
        Object val = context.metadata().get(field);
        return (val != null) ? val.toString() : defaultValue.val();
    }

    // Shared XML generator
    protected String constructXml(XmlMapper mapper, String txId, String endToEndId, String currency, String amount) {
        try {
            GroupHeader hdr = new GroupHeader(txId, ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
            Iso20022Document doc = new Iso20022Document(hdr, 
                new CreditTransferTransactionInformation(new PaymentIdentification(endToEndId), new Amount(currency, amount)));
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(doc);
        } catch (Exception e) {
            throw new RuntimeException("XML Mapping Error: " + e.getMessage());
        }
    }
}