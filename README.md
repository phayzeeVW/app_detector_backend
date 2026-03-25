# Game Detector 

Game Detector is a Jakarta EE and Spring Boot-based application designed to monitor active windows on a Windows system, identify running applications (specifically games), and track session durations. It features a modern web interface built with Vaadin.

##  Features

-   **Automatic Detection:** Scans active desktop windows every 5 seconds to identify newly opened or closed applications.
-   **Session Tracking:** Automatically records the start and end times of application sessions.
-   **Persistent Storage:** Saves detected applications and their usage history to a MySQL database using Spring Data JPA.
-   **Web Dashboard:**
    -   **Sessions View:** A searchable grid of all recorded sessions, showing start/stop times and application details.
    -   **Applications View:** A management grid to view detected applications and toggle session tracking for specific paths.
-   **JNA Integration:** Uses Java Native Access (JNA) to interface with the Windows API for low-level window management.

##  Tech Stack

-   **Backend:** Java 21, Spring Boot 3.5.x, Spring Data JPA.
-   **Frontend:** Vaadin 24
-   **Database:** MySQL (Production/Dev)
-   **Native Interop:** JNA (Java Native Access).
-   **Utilities:** Lombok, SLF4J/Logback.

##  Prerequisites

-   **Java SDK 21**
-   **Maven 3.9+** (or use the included `./mvnw`)
-   **MySQL Server** (Running on `localhost:3306`)
-   **Windows OS** (Required for native window detection features)

## Configuration

The application uses standard Spring profiles. Update `src/main/resources/application.yml` or `application-dev.yml` with your database credentials:
