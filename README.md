# PriorPlan

<div align="center">
  <img src="src/main/resources/BackgroundImage/LoginIon.png" alt="PriorPlan Logo" width="150"/>
  <h3>The Ultimate To-Do List with Priority Management</h3>
  <p>Get your tasks done with ease and efficiency!</p>
</div>

<div align="center">
  <img src="https://skillicons.dev/icons?i=java,spring,postgresql,maven" />
</div>

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Database Configuration](#database-configuration)
7. [Project Structure](#project-structure)
8. [Available Screens](#Available-Screens)
9. [Developers](#developers)
10. [License](#license)
11. [Contact](#contact)

## Overview
PriorPlan is a desktop application built with Java Swing and Spring Boot that helps users manage and organize their daily tasks efficiently. The application provides a user-friendly interface for creating, updating, and tracking tasks with different priority levels and statuses.

With PriorPlan, you can prioritize your tasks, set due dates, track task progress, and mark tasks as completed. The application is designed to help you stay organized and focused on what matters most.

## Features
- **User Authentication**: Secure login and signup functionality
- **Task Management**:
  - Add new tasks with title, description, due date, and priority
  - Update existing tasks
  - Delete tasks
  - Mark tasks as completed
- **Task Prioritization**: Set task priority as HIGH, MEDIUM, or LOW
- **Task Status Tracking**: Track task status (NOT_STARTED, IN_PROGRESS, COMPLETED)
- **Due Date Management**: Set and track due dates for tasks
- **User-Friendly Interface**: Intuitive and easy-to-use Swing-based UI
- **Data Persistence**: All tasks are stored in a PostgreSQL database

## Technologies Used
- **Java 21**: Core programming language
- **Spring Boot 3.4.4**: Application framework
- **Spring Data JPA**: Data access and persistence
- **PostgreSQL**: Database for storing user and task data
- **Hibernate**: ORM for database operations
- **Java Swing**: GUI framework for desktop application
- **JCalendar**: Date picker component
- **Lombok**: Reduces boilerplate code
- **Maven**: Build automation and dependency management

## Installation
### Prerequisites
- Java Development Kit (JDK) 21 or higher
- PostgreSQL database server
- Maven (or use the included Maven Wrapper)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/syedsadiquh/PriorPlan.git
   cd PriorPlan
   ```

2. Configure the database (see [Database Configuration](#database-configuration) section)

3. Build the project:
   ```bash
   # Using Maven
   mvn clean install

   # Or using Maven Wrapper
   ./mvnw clean install
   ```

## Usage
1. Run the application:
   ```bash
   # Using Maven
   mvn spring-boot:run

   # Or using the generated JAR file
   java -jar target/priorplan-0.0.1-SNAPSHOT.jar
   ```

2. The application will start with a login screen
3. Create a new account or log in with existing credentials
4. Use the home page to navigate to different features:
   - Add Task: Create new tasks
   - Manage Task: View, update, delete, or complete existing tasks
   - About: View information about the application
   - Developer Details: View information about the developers

## Database Configuration
The application uses PostgreSQL as its database. You need to:

1. Install PostgreSQL if not already installed
2. Create a database named `priorplan`
3. Configure the database connection in `src/main/resources/application.yml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/priorplan
       username: your_username
       password: your_password
       driver-class-name: org.postgresql.Driver
   ```

The application will automatically create the necessary tables when it first runs.

## Project Structure
The project follows a standard Spring Boot application structure:
- `src/main/java/com/syedsadiquh/priorplan/`: Root package
  - `models/`: Data models and entities
  - `repository/`: Spring Data JPA repositories
  - `dao/`: Data Access Objects
  - `ui/`: Swing UI components
  - `globals/`: Global variables and constants
  - `PriorPlanApplication.java`: Main application class

## Available Screens
The application includes several screens:
- Login and Signup pages
- Home page with navigation to all features
- Task Adding page for creating new tasks
- Task Manager for viewing and managing existing tasks
- About page with application information
- Developer Details page

## Developers
- **Syed Sadiqu Hussain** - Backend
  - Email: [syedsadiquh@gmail.com](mailto:syedsadiquh@gmail.com)
  - GitHub: [syedsadiquh](https://github.com/syedsadiquh)
  - LinkedIn: [syedsadiquh](https://www.linkedin.com/in/syedsadiquh)

- **Mohanty Hitesh Rabindranath** - Frontend
  - Email: [mohantyhitesh4495@gmail.com](mailto:mohantyhitesh4495@gmail.com)
  - GitHub: [Mohanty-Hitesh-4495](https://github.com/Mohanty-Hitesh-4495)

## License
This project is licensed under the Apache License, Version 2.0. See the [LICENSE](https://github.com/syedsadiquh/PriorPlan/blob/main/LICENSE) file for more details.

## Contact
For any questions or feedback please reach out to:

- **Email**: [syedsadiquh@gmail.com](mailto:syedsadiquh@gmail.com)
- **GitHub Profile**: [syedsadiquh](https://github.com/syedsadiquh)
- **LinkedIn**: [syedsadiquh](https://www.linkedin.com/in/syedsadiquh)

Feel free to open an issue on GitHub or contact us if you have any queries or suggestions.
