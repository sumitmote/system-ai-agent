# Angular Chat UI

This Angular 21 app provides a chat screen for the Spring endpoint `POST /api/chat`.

## Prerequisites

- Node.js 22+
- Spring Boot backend running on `http://127.0.0.1:8080`

## Install

```bash
npm install
```

## Run the UI

```bash
npm start
```

Open `http://localhost:4200`.

## How API calls work

- The app sends `{ "message": "..." }` to `/api/chat`.
- `proxy.conf.json` forwards `/api/*` to `http://127.0.0.1:8080` in development.
- Assistant replies are rendered with markdown formatting (lists, code blocks, headings, and quotes).

## Test

```bash
npm test -- --run
```

## Build

```bash
npm run build
```
