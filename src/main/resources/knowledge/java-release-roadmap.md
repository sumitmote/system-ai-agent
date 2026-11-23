# Java language and platform release roadmap

Java releases are cumulative: a newer JDK includes supported features from earlier releases. Language level, runtime, and API availability are related but distinct. This project targets Java 21 so examples can run on a widely supported LTS release; use a matching JDK to compile examples that require newer language features.

## Selected milestones

- Java 8: lambdas, method references, streams, `Optional`, default interface methods, `java.time`, and `CompletableFuture`.
- Java 9: modules, JShell, collection factory methods, and private interface methods.
- Java 10: local-variable type inference with `var`.
- Java 11: standard HTTP client, single-file source launch, and `var` in lambda parameters.
- Java 14: switch expressions and helpful NullPointerException messages.
- Java 15: text blocks.
- Java 16: records, pattern matching for `instanceof`, and `Stream.toList()`.
- Java 17: sealed classes.
- Java 21: virtual threads, record patterns, pattern matching for `switch`, and sequenced collections.
- Java 25: compact source files and instance main methods, module import declarations, and flexible constructor bodies.

## How to study each release

For each version, learn the release date and support status, identify finalized features separately from preview or incubator features, compare the feature to the older idiom, and compile a minimal example with that JDK. Preview features can change between releases and require explicit compiler and runtime flags; do not treat them as stable APIs.

Java 22 through 24 introduced or finalized updates across the Foreign Function & Memory API, Stream Gatherers, the Class-File API, and language previews. Verify Java 25 and 26 details against their final release notes and JEPs, and keep Java 27 material current as that release develops. Do not infer finalized features from previews or proposals. Ask the chatbot for a version-specific explanation and request official JEP links when details depend on a particular release.
