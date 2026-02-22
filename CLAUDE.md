# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build
./gradlew build                    # Build all modules
./gradlew :app:build               # Build Android app only
./gradlew :server:build            # Build server only

# Run
./gradlew :app:installDebug        # Install Android app on device/emulator
./gradlew :server:run              # Run Ktor server (localhost:8080)

# Test
./gradlew test                     # Run all tests
./gradlew :app:testDebugUnitTest   # Run app unit tests
./gradlew :app:testDebugUnitTest --tests "com.achtien.codingtemplate.SomeTest"  # Single test
```

## Project Structure

Multi-module Kotlin project with an Android app and a Ktor backend server.

**Root project:** `NicksProject` (Gradle 9.1.0, Kotlin 2.3.10, AGP 9.0.1)

### Modules and dependency flow

```
:app (Android Application) → :repository (Android Library) → :networking (Android Library)
:server (Ktor JVM Server, standalone)
```

- **:app** — Jetpack Compose UI, MVVM with `NicksViewModel`, Koin DI. Entry: `MainActivity` → `NicksView` composable. Koin initialized in `App.kt`.
- **:repository** — Data access layer, depends on `:networking` via API configuration.
- **:networking** — Ktor HTTP client (`Api` class) with KotlinX serialization and content negotiation. Android engine.
- **:server** — Ktor Server (Netty) on port 8080. SQLite via SQLDelight, JWT auth, bcrypt. Main class: `org.achtien.wall.ApplicationKt`. Feed subscription REST API with CORS enabled.

### Key conventions

- **DI:** Koin throughout. Modules defined in each layer's `di/` package, composed in `App.kt`.
- **Serialization:** KotlinX Serialization everywhere (both client and server).
- **Networking:** Ktor 3.4.0 for both client (Android) and server.
- **JVM Toolchain:** 17 for module compilation; 21 for Kotlin toolchain resolution (Foojay).
- **Android targets:** minSdk 26, compileSdk/targetSdk 36, Compose BOM 2026.02.00.
- **Version catalog:** `gradle/libs.versions.toml` for centralized dependency management.
- **Typesafe project accessors** enabled (`projects.networking`, `projects.repository`).

### Server API (protected routes require JWT)

- `GET /feeds` — List all feeds
- `GET /feeds/my-subscriptions` — User's subscriptions
- `POST /feeds/{feedId}/subscribe` — Subscribe
- `DELETE /feeds/{feedId}/unsubscribe` — Unsubscribe
- `PUT /feeds/{feedId}/settings` — Update subscription settings

Note: `Security.kt` (JWT config) and `AuthRoutes.kt` are referenced but not yet committed. The `:server` module is not yet added to `settings.gradle.kts`.
