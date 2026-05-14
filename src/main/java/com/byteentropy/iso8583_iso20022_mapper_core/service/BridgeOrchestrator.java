package com.byteentropy.iso8583_iso20022_mapper_core.service;

import com.byteentropy.iso8583_iso20022_mapper_core.mapper.RegionalMapper;
import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Orchestrator manages the strategy selection.
 * It selects the correct mapper based on the sourceRegion.
 */
@Service
public class BridgeOrchestrator {

    private final List<RegionalMapper> mappers;

    public BridgeOrchestrator(List<RegionalMapper> mappers) {
        this.mappers = mappers;
    }

    /**
     * Finds the mapper that supports the requested region and processes the context.
     */
    public String processTransformation(BridgeContext context) {
        // 1. Find the implementation that supports this specific region
        RegionalMapper selectedMapper = mappers.stream()
                .filter(mapper -> mapper.supports(context.sourceRegion()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported region: " + context.sourceRegion()));

        // 2. Execute the public template method in RegionalMapper
        return selectedMapper.process(context);
    }
}