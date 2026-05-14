package com.byteentropy.iso8583_iso20022_mapper_core.api;

import com.byteentropy.iso8583_iso20022_mapper_core.model.BridgeContext;
import com.byteentropy.iso8583_iso20022_mapper_core.service.BridgeOrchestrator;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/bridge")
public class BridgeController {
    private final BridgeOrchestrator orchestrator;

    public BridgeController(BridgeOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping(value = "/transform", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> transform(@Valid @RequestBody BridgeContext context) {
        return ResponseEntity.ok(orchestrator.processTransformation(context));
    }
}