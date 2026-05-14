package com.byteentropy.iso8583_iso20022_mapper_core.service;

import com.byteentropy.iso8583_iso20022_mapper_core.mapper.RegionalMapper;
import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BridgeOrchestratorTest {

    private BridgeOrchestrator orchestrator;
    private RegionalMapper mockMapper;

    @BeforeEach
    void setUp() {
        // Create a mock instance of the abstract base class
        mockMapper = mock(RegionalMapper.class);
        // Inject the mock into the orchestrator
        orchestrator = new BridgeOrchestrator(List.of(mockMapper));
    }

    @Test
    void shouldCallMapperWhenRegionIsSupported() {
        // Arrange
        BridgeContext ctx = new BridgeContext("TXN-123", "SG_FAST", "RAW_PAYLOAD", Map.of());
        when(mockMapper.supports("SG_FAST")).thenReturn(true);
        when(mockMapper.process(ctx)).thenReturn("<Document>Success</Document>");

        // Act
        String result = orchestrator.processTransformation(ctx);

        // Assert
        assertEquals("<Document>Success</Document>", result);
        verify(mockMapper, times(1)).process(ctx);
    }

    @Test
    void shouldThrowExceptionWhenRegionNotSupported() {
        // Arrange
        BridgeContext ctx = new BridgeContext("TXN-456", "INVALID_REGION", "RAW_PAYLOAD", Map.of());
        when(mockMapper.supports(anyString())).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orchestrator.processTransformation(ctx);
        });

        assertTrue(exception.getMessage().contains("Unsupported region"));
        // Ensure process() was NEVER called for an unsupported region
        verify(mockMapper, never()).process(any());
    }
}