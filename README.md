📝 OTJ Logger – Hackathon Prototype
🌟 Overview

This project is a Spring Boot backend prototype built during a hackathon to improve how Off-the-Job (OTJ) learning is logged before being submitted to OneFile.

The goal is to:

⏱️ reduce the time it takes to log OTJ
📊 improve consistency and data quality
🤝 reduce rework for coaches

This is not a replacement for OneFile, but a cleaner way to capture OTJ data before it reaches it.

🛠️ Tech Stack
☕ Java 17
🌱 Spring Boot
🌐 Spring Web
🗄️ Spring Data JPA
⚡ H2 Database (in-memory)

▶️ Running the Application
Prerequisites
Java 17+
Git

Steps
git clone <repo-url>
cd otj-logger
./mvnw spring-boot:run

The app will start at:
http://localhost:8081/h2-console/

# Run backend (quick steps for a VS Code / FE dev)

Prereqs:
- Install JDK 17\+ (match `pom.xml` Java version).
- Install Maven or use the bundled wrapper if present.

Quick run (from repo root):
```bash
git clone https://github.com/YWS01/winter-hackathon-back-end.git
cd winter-hackathon-back-end
# build
mvn -DskipTests package
# run (or use the VS Code task)
mvn spring-boot:run
# or run packaged jar
java -jar target/*.jar