# Spring AI learning chatbot

A local Retrieval-Augmented Generation (RAG) chat application using Spring Boot, Spring AI, Ollama, and PostgreSQL with pgvector. It starts with learning notes about Spring Boot and Java release milestones; add Markdown files under `src/main/resources/knowledge` to expand the material.

## Requirements

- JDK 21 or newer
- Maven 3.9+
- Docker Desktop with Docker Compose
- Ollama installed and running

## Run locally

In PowerShell, from this project directory:

```powershell
docker compose up -d
ollama pull llama3.2
ollama pull nomic-embed-text
mvn spring-boot:run
```

The app listens on `http://localhost:8080`. On startup, it loads the Markdown files in `src/main/resources/knowledge` into pgvector. Each source file is replaced on startup, so editing or adding a file refreshes its indexed copy. The first run downloads the configured models and can take several minutes.

Ask a question:

```powershell
Invoke-RestMethod -Method Post `
  -Uri http://localhost:8080/api/chat `
  -ContentType 'application/json' `
  -Body '{"message":"Explain Spring Boot auto-configuration and give me an exercise."}'
```

Check application health at `http://localhost:8080/actuator/health`.

## Angular frontend (chat UI)

The workspace now includes an Angular 21 app under `ui/`.

In a new PowerShell terminal:

```powershell
Set-Location ui
npm install
npm start
```

Open `http://localhost:4200` and send messages from the web UI.

`ui/proxy.conf.json` proxies `/api/*` requests to `http://127.0.0.1:8080`, so you can run frontend and backend separately in development.

## Flow diagrams

- End-to-end chat request and response flow: `docs/chat-rag-flow.svg`
- Knowledge ingestion into pgvector: `docs/knowledge-feed-flow.svg`
- Secure confidential-data architecture: `docs/secure-rag-architecture.svg`

If your editor supports SVG preview, open these files directly for a visual walkthrough.

## Configuration

Defaults are in `src/main/resources/application.yml`. Override them with environment variables:

| Variable | Default | Purpose |
|---|---|---|
| `OLLAMA_BASE_URL` | `http://localhost:11434` | Ollama server |
| `OLLAMA_CHAT_MODEL` | `llama3.2` | Chat model |
| `OLLAMA_EMBEDDING_MODEL` | `nomic-embed-text` | Embedding model (768 dimensions) |
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://127.0.0.1:5433/springaidemo` | PostgreSQL JDBC URL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Database user |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Database password |

Use an embedding model with 768 dimensions unless you also change the pgvector dimension setting and recreate or migrate the vector table. The Compose database credentials are for local development only.

## Learning Java 8–27 and Spring Boot

The app's application code targets Java 21; it cannot execute every Java language level in one JVM. For features newer than 21, install the corresponding JDK and compile a separate example with that release. Keep Java 26 and 27 material aligned with official release notes and distinguish finalized features from previews and proposals.

Use the chatbot as a tutor: ask for an explanation at your level, a comparison with an older approach, a runnable example, and then an exercise with hints. The indexed Markdown files are a starter knowledge base, not a complete replacement for official Spring and Java documentation. Add or revise those files to build out lessons, then restart the app to re-index them.
