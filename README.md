# MusicStream FullStack Integration

MusicStream is a comprehensive web application for managing local music files, featuring an Angular frontend with reactive state management (NgRx) and a robust Spring Boot backend.

## 🚀 Technologies

### Frontend
- **Angular 17+**: Component-based architecture with Lazy Loading.
- **NgRx**: Centralized state management (Actions, Reducers, Effects, Selectors).
- **Vanilla CSS**: Premium UI design with dark mode and glassmorphism.

### Backend
- **Spring Boot 3+**: Layered architecture (Controller, Service, Repository).
- **PostgreSQL**: Relational database for track metadata and audio data.
- **Maven**: Dependency management and build tool.

### DevOps
- **Docker**: Containerized environment for easy deployment.
- **GitHub Actions**: Automated CI/CD for build and verification.

## 📦 Features

- **Full CRUD**: Add, List, Edit, and Delete tracks.
- **Audio Streaming**: Real-time audio playback directly from the backend.
- **Reactive UI**: Immediate feedback for all actions via NgRx.
- **Search & Filter**: Find tracks by title, artist, or category.

## 🛠 Setup & Launch

### Prerequisites
- Docker & Docker Compose
- Node.js & NPM (for local development)
- Java 17+ & Maven (for local development)

### Quick Start with Docker
```bash
docker-compose up --build
```
The app will be available at `http://localhost`.

### Local Development
1. **Database**: Ensure PostgreSQL is running and update `application.properties`.
2. **Backend**:
   ```bash
   cd backend
   mvn spring-boot:run
   ```
3. **Frontend**:
   ```bash
   cd frontend
   npm install
   npm start
   ```

## 📐 Architecture

The project follows a clean, layered architecture:

- **Frontend**: `Core` (services/models) | `Features` (UI components) | `Store` (NgRx state).
- **Backend**: `Controller` (REST endpoints) | `Service` (Business logic) | `Model` (Entities).


---
## 📐 diagramme de classe

<img width="323" height="194" alt="diag class de musicstream" src="https://github.com/user-attachments/assets/bbd76fd7-cc6f-4d98-bce0-477f4d7474f0" />




*Projet individuel - Nada Zirari - 2026*
