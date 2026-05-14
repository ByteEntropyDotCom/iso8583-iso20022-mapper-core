package com.byteentropy.iso8583_iso20022_mapper_core.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record BridgeContext(
    @NotBlank(message = "Transaction ID is required")
    String transactionId,
    
    @NotBlank(message = "Source region is required")
    String sourceRegion,
    
    @NotBlank(message = "Raw payload cannot be empty")
    String rawPayload,
    
    @NotNull
    Map<String, Object> metadata
) {}