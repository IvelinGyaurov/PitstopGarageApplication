**PitStop Garage**

Auto repair shop management system. SoftUni Spring Advanced – individual project.

Repository: https://github.com/IvelinGyaurov/PitstopGarageApplication

**Technology stack**

Java 17, Spring Boot 3.4.0, Maven, Spring MVC, Thymeleaf, Spring Security, Spring Data JPA, MySQL, Jakarta Validation, Lombok.

Second application in development: Spring Boot REST service for parts, separate MySQL database, OpenFeign from the main app.

**Supported features**

User registration and login, role-based access (USER, MECHANIC, ADMIN), vehicle and repair domain model, web pages for register, login, and home.

Planned: car management, repair workflow, mechanic and admin panels, profile edit, parts service integration, scheduling, caching, tests.

**Functionalities**

Valid domain actions (login, registration, and profile-only tasks do not count):

Main application: add car, remove own car, request service repair, cancel pending repair, mechanic accepts repair, mechanic completes repair, add used part to repair, admin promotes user to mechanic.

Parts microservice: admin adds or updates a part, mechanic reserves a part for a repair.

**Integrations**

MySQL for the main application (pitstop_garage).

PitStop Garage Parts – internal REST microservice with its own database (pitstop_garage_parts), called from the main app via OpenFeign.

**Run locally**

Start MySQL, set credentials in application.properties, run mvnw spring-boot:run, open http://localhost:8080.

**Author**

Ivelin Gyaurov
