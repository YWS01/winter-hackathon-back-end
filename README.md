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

### Development H2 Console (demo only)

Open the H2 web console at: `http://localhost:8081/h2-console/`

- JDBC URL: `jdbc:h2:~/otjlogger`
- User Name: `sa`
- Password: (leave blank)

> Note: These credentials are for local development 
> 

### Swagger UI

You can explore the API using Swagger UI when the application is running locally:

- Swagger UI: http://localhost:8081/swagger-ui/index.html#/

Open the link in your browser (change the port if your server uses a different one).
