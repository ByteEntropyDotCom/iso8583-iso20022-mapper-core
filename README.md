# ISO 8583 to ISO 20022 Mapper Core

A high-performance, asynchronous bridge service designed to transform legacy **ISO 8583** financial messages into modern **ISO 20022** (XML) formats.

Built with **Java 21**, **Spring Boot 3**, and **Virtual Threads**, this service is optimized for high-concurrency payment orchestration environments.

## 🏗️ Architecture

The project utilizes the **Strategy Design Pattern** to handle regional payment variations (e.g., UK FPS, Singapore FAST, US FedNow) through a centralized orchestrator.

### Key Features
- **Virtual Threads (Project Loom):** High-throughput processing without blocking OS threads.
- **Strategy Pattern:** Decoupled regional mappers for easy extensibility and scalability.
- **Immutable Models:** Leverages Java Records for clean XML and JSON data structures.
- **Strict Content Negotiation:** Seamlessly handles JSON input and XML output.

## 🛠️ Tech Stack
- **Runtime:** Java 21 (Eclipse Temurin)
- **Framework:** Spring Boot 3.x
- **Parsing:** Jackson (JSON & XML Dataformat)
- **Testing:** JUnit 5 + Mockito
- **Containerization:** Docker (multi-stage builds)

## 🚦 Getting Started

### Prerequisites
- JDK 21
- Maven 3.9+
- Docker (optional)

### Build and Run

```bash
# Clone the repository
git clone https://github.com/your-repo/iso8583-iso20022-mapper-core.git
cd iso8583-iso20022-mapper-core

# Compile and test
mvn clean verify

# Run locally
mvn spring-boot:run
```


## 🧪 API Usage
Transform Payload
Endpoint: POST /api/v1/bridge/transform
Headers:

Content-Type: application/json
Accept: application/xml

### Example Request
```
JSON{
  "transactionId": "TXN-998877",
  "sourceRegion": "UK_FPS",
  "rawPayload": "0200...",
  "metadata": {
    "F4_AMOUNT": "1500.00",
    "F49_CURRENCY": "GBP"
  }
}
```

### Example Response

```
XML<Document>
  <GrpHdr>
    <MsgId>TXN-998877</MsgId>
    <CreDtTm>2026-05-14T09:26:40Z</CreDtTm>
  </GrpHdr>
  <CdtTrfTxInf>
    <PmtId>
      <EndToEndId>UK-FPS-TXN-998877</EndToEndId>
    </PmtId>
    <InstdAmt Ccy="GBP">1500.00</InstdAmt>
  </CdtTrfTxInf>
</Document>
```

## 🐳 Dockerization
Build the production-ready image:

```Bashdocker build -t iso8583-mapper-core .```

## 📄 License
This project is licensed under the MIT License.