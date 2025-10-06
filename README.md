# Spring Boot Itinerary Service

A simple flight booking application built with **Spring Boot, Thymeleaf, JPA, and H2**.  
Users can view available flights, see prices in different currencies, and reserve flights.

---

## Features

- List available flights
- Display prices in original currency and USD
- Book flights with first and last name
- Prevent double-booking
- Simple responsive UI with Bootstrap

---

## Technologies

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- H2 in-memory database
- Bootstrap 3
- JUnit 5 & Mockito for testing

---

## Setup

### 1. Clone the repo:

```bash
git clone https://github.com/your-username/itinerary-service.git
cd itinerary-service
```

### 2. Build and run:

```bash
mvn clean install
mvn spring-boot:run
```

Open the application in your browser: [http://localhost:8080/](http://localhost:8080/)

> H2 console is available at http://localhost:8080/h2-console (optional, for debugging)


![Demo](docs/demo.gif)