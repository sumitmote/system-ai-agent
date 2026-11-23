# Spring Boot learning guide

Spring Boot builds on the Spring Framework to make stand-alone, production-oriented applications easier to create. Its main ideas are convention over configuration, auto-configuration based on the classpath and application properties, and starter dependencies that provide coherent dependency sets.

## Application startup

`@SpringBootApplication` combines component scanning, Spring Boot configuration, and auto-configuration. `SpringApplication.run` creates the application context and starts the embedded web server. Beans are normally registered through component annotations or `@Bean` methods and injected through constructors.

## Configuration and profiles

Use `application.yml` or `application.properties` for defaults, environment variables for deployment-specific values, and `@ConfigurationProperties` for typed groups of application settings. Profiles such as `local` and `prod` allow environment-specific configuration. Do not commit credentials; supply secrets through environment variables or a secret manager.

## Common feature areas to study

- Web: Spring MVC controllers, request validation, exception handling, filters, and REST API design.
- Data: Spring Data repositories, transactions, schema migrations, JDBC, and database testing.
- Security: authentication, authorization, OAuth 2.0, and secure configuration.
- Operations: Actuator health and metrics, structured logging, observability, and graceful shutdown.
- Testing: unit tests, `@WebMvcTest`, `@SpringBootTest`, test slices, and Testcontainers.
- Packaging: executable JARs, Docker images, externalized configuration, and deployment.

Explore one feature at a time: ask for a concept explanation, request a small example, then ask for a test and an exercise. Confirm version-specific behavior against the official documentation for the Spring Boot version you are using.
