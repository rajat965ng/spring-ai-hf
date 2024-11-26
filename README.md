# Spring AI HF

A Spring Boot application for building a **Retrieval-Augmented Generation (RAG)** pipeline using Hugging Face models. The project integrates pgvector for vector search, Tika for document ingestion, and OpenTelemetry for observability. It enables intelligent querying and chatbot interactions.

## Repository
GitHub: [https://github.com/rajat965ng/spring-ai-hf.git](https://github.com/rajat965ng/spring-ai-hf.git)

---

## Features
- **Document Ingestion**: Parse and index documents using Apache Tika and pgvector.
- **Vector Search**: Perform similarity-based searches with pgvector and HNSW indexing.
- **RAG Pipeline**: Retrieve relevant content to enhance chatbot context.
- **Observability**: Includes OpenTelemetry and Jaeger for request tracing.
- **Swagger Integration**: API documentation for easy testing and usage.

---

## Requirements
- Docker
- JDK 17 or later
- Maven
- PostgreSQL (with `pgvector` extension)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/rajat965ng/spring-ai-hf.git
cd spring-ai-hf
```

### 2. Configure Environment Variables
Create a `.env` file with the following:
```dotenv
POSTGRES_USER=postgres
POSTGRES_PW=postgres
POSTGRES_DB=vectordb
PGADMIN_MAIL=your@email.com
PGADMIN_PW=postgres
```

### 3. Start Services Using Docker
Run the following command to spin up PostgreSQL, pgAdmin, and Jaeger services:
```bash
docker-compose up -d
```

### 4. Execute SQL Script
Run the `pgvector.sql` file to create the database schema:
```bash
psql -U postgres -d vectordb -f pgvector.sql
```

### 5. Update Application Properties
Edit the `application.properties` file:
```properties
spring.ai.huggingface.chat.api-key=<YOUR_HF_API_KEY>
```

---

## Building and Running the Application

### 1. Build the Project
```bash
mvn clean install
```

### 2. Run the Application
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`.

---

## API Endpoints

### **1. Index Documents**
- **URL**: `POST /index`
- **Description**: Ingests documents from the provided URLs into the vector store.
- **Request Parameter**: `urls` (list of URLs)
- **Response**: HTTP 201 (Created)

### **2. Chat**
- **URL**: `POST /chat`
- **Description**: Generates context-aware responses based on user queries and indexed content.
- **Request Parameter**: `input` (string)
- **Response**: Contextual chatbot answer in JSON.

---

## Properties Explained

### **PostgreSQL and pgvector Configuration**
- `spring.datasource.url`: JDBC URL to connect to PostgreSQL.
- `spring.datasource.username`: PostgreSQL username.
- `spring.datasource.password`: PostgreSQL password.
- `spring.ai.vectorstore.pgvector.index-type`: Sets the pgvector index type (`HNSW` is used for approximate nearest neighbor searches).
- `spring.ai.vectorstore.pgvector.distance-type`: Distance metric used for similarity search (e.g., `COSINE_DISTANCE`).
- `spring.ai.vectorstore.pgvector.dimensions`: Dimensionality of the vector embeddings (e.g., 384).
- `spring.ai.vectorstore.pgvector.table-name`: Name of the vector store table (`vector_store` in this case).
- `spring.ai.vectorstore.pgvector.schema-validation`: Enables schema validation.
- `spring.ai.vectorstore.pgvector.initialize-schema`: Creates the schema during startup.

### **Embedding and Model Configuration**
- `spring.ai.embedding.transformer.onnx.modelUri`: URI of the ONNX model used for generating embeddings.
- `spring.ai.embedding.transformer.tokenizer.uri`: URI of the tokenizer configuration for the model.

### **Hugging Face Configuration**
- `spring.ai.huggingface.chat.api-key`: API key for authenticating Hugging Face requests.
- `spring.ai.huggingface.chat.url`: URL of the Hugging Face model endpoint.

### **Tracing and Observability**
- `management.endpoints.web.exposure.include=*`: Exposes all Actuator endpoints.
- `management.endpoints.health.show-details=always`: Shows detailed health endpoint responses.
- `management.tracing.sampling.probability=1`: Sets tracing sampling rate to 100%.
- `management.opentelemetry.resource-attributes.service.name`: Defines the service name for OpenTelemetry traces.
- `management.otlp.tracing.endpoint`: OTLP endpoint for sending trace data.

### **OpenAPI Configuration**
- `springdoc.swagger-ui.path`: Configures the path to access the Swagger UI.

### **Virtual Threads**
- `spring.threads.virtual.enabled=true`: Enables the use of virtual threads in Spring for scalability.

---

## Observability
- **Swagger**: Visit `http://localhost:8080/swagger-ui.html` for API documentation.
- **Jaeger UI**: View request traces at `http://localhost:16686`.

---

## Project Structure

```
src/
├── main/
│   ├── java/com/hf/app/
│   │   ├── controller/  # REST Controllers
│   │   ├── service/     # Business Logic
│   │   └── Application.java
│   └── resources/
│       ├── application.properties  # Configurations
│       └── pgvector.sql            # Database Schema
└── test/                           # Unit Tests
```

---

## Key Technologies
- **Spring Boot**: Framework for building web applications.
- **Hugging Face**: Provides NLP models for chatbot interactions.
- **pgvector**: PostgreSQL extension for vector search.
- **Apache Tika**: Document ingestion and parsing.
- **OpenTelemetry**: Distributed tracing for observability.

---

## Author
**Rajat Nigam**  
GitHub: [https://github.com/rajat965ng](https://github.com/rajat965ng)

---