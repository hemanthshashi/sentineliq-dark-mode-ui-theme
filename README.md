# Tool-84 — Dark Mode UI Theme

## Project Overview

Tool-84 — Dark Mode UI Theme is a full-stack web application developed as part of the CampusPe Internship Program.

The project allows users to manage software tools through REST APIs. The backend is built using Spring Boot, the database is managed using MySQL through XAMPP, and APIs are tested using Postman.

As a Java Developer 1, the main focus of this project is backend development, REST API creation, database integration, authentication, testing, and documentation.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot |
| Database | MySQL |
| Database Tool | XAMPP |
| API Testing | Postman |
| Build Tool | Maven |
| IDE | VS Code |
| Version Control | Git and GitHub |

---

## Architecture Diagram

```text
+------------------+
|    Postman       |
| API Testing Tool |
+--------+---------+
         |
         | HTTP Requests
         v
+---------------------------+
|     Spring Boot Backend   |
|                           |
| Controllers               |
| Services                  |
| Repositories              |
| Security / JWT Auth       |
+------------+--------------+
             |
             | JPA / Hibernate
             v
+---------------------------+
|        MySQL Database     |
|       Managed by XAMPP    |
+---------------------------+

##Project Structure
sentineliq-dark-mode-ui-theme/
│
├── README.md
│
├── backend/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/internship/tool/
│       │   │       ├── controller/
│       │   │       ├── service/
│       │   │       ├── repository/
│       │   │       ├── dto/
│       │   │       ├── config/
│       │   │       └── entity/
│       │   │
│       │   └── resources/
│       │       └── application.yml
│       │
│       └── test/
│
├── frontend/
│
└── docker-compose.yml


##FEATURES
User authentication using JWT.
REST API development using Spring Boot.
Tool management CRUD operations.
MySQL database integration.
Search, filter, pagination, and sorting APIs.
API testing using Postman.
Controller, Service, Repository layered architecture.
Environment-based configuration support.

## Prerequisites

Before running this project, install the following:

| Software | Purpose |
|---|---|
| Java 17 | To run the Spring Boot backend |
| Maven | To build and manage backend dependencies |
| VS Code | Code editor |
| XAMPP | To run MySQL database locally |
| Postman | To test REST APIs |
| Git | Version control |
| MySQL | Database used by backend |


##Database Setup Using XAMPP
1.Open XAMPP Control Panel.
2.Start Apache and MySQL.
3.Open phpMyAdmin:
   http://localhost/phpmyadmin
4.Create a database:
   CREATE DATABASE tool_db;
5.Make sure the database name matches the backend configuration.



## Backend Setup Steps

### 1. Clone the Repository

```bash
git clone https://github.com/hemanthshashi/sentineliq-dark-mode-ui-theme.git

2.Move to Backend Folder
cd sentineliq-dark-mode-ui-theme/backend

3.Configure Database
Open this file:src/main/resources/application.yml

Use this Configuration:
server:
  port: 8080

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tool_db
    username: root
    password:
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

4.Build the Project
-->  mvn clean instal    

5. Run the Backend
-->  mvn spring-boot:run


Backend Runs at:http://localhost:8080


Table version:

```md
## Backend Setup Steps

| Step | Action | Command / File |
|---|---|---|
| 1 | Clone the repository | `git clone https://github.com/hemanthshashi/sentineliq-dark-mode-ui-theme.git` |
| 2 | Move to backend folder | `cd sentineliq-dark-mode-ui-theme/backend` |
| 3 | Open database config file | `src/main/resources/application.yml` |
| 4 | Build the project | `mvn clean install` |
| 5 | Run the backend | `mvn spring-boot:run` |
| 6 | Backend URL | `http://localhost:8080` |


## .env Reference Table

| Variable Name | Example Value | Description |
|---|---|---|
| `SERVER_PORT` | `8080` | Backend server port |
| `DB_URL` | `jdbc:mysql://localhost:3306/tool_db` | MySQL database connection URL |
| `DB_USERNAME` | `root` | MySQL username |
| `DB_PASSWORD` | empty or your password | MySQL password |
| `JWT_SECRET` | `mySecretKey123456789` | Secret key used to generate JWT token |
| `MAIL_HOST` | `smtp.gmail.com` | SMTP host for email service |
| `MAIL_PORT` | `587` | SMTP port used for email |
| `MAIL_USERNAME` | `your-email@gmail.com` | Sender email address |
| `MAIL_PASSWORD` | `your-app-password` | Gmail app password |
| `FRONTEND_URL` | `http://localhost:5173` | Frontend application URL |


## Example application.yml Using Environment Variables

```yml
server:
  port: ${SERVER_PORT:8080}

spring:
  datasource:
    url: ${DB_URL:jdbc:mysql://localhost:3306/tool_db}
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:}
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

jwt:
  secret: ${JWT_SECRET:mySecretKey123456789}

frontend:
  url: ${FRONTEND_URL:http://localhost:5173}



  ## Important API Endpoints

### Authentication API

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/login` | Login user and generate JWT token |

---

### Tool APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tools` | Get all tools |
| GET | `/api/tools/all` | Get all tools using alternate URL |
| GET | `/api/tools/{id}` | Get tool by ID |
| POST | `/api/tools` | Create a new tool |
| PUT | `/api/tools/{id}` | Update existing tool |
| DELETE | `/api/tools/{id}` | Delete tool |
| GET | `/api/tools/search` | Search, filter, sort, and paginate tools |


## Sample API Request

### Create Tool

**URL:**

```http
POST http://localhost:8080/api/tools


{
  "name": "ChatGPT",
  "category": "AI Tool",
  "description": "AI assistant",
  "websiteUrl": "https://chat.openai.com",
  "logoUrl": "",
  "active": true
}

{
  "id": 1,
  "name": "SJBIT",
  "category": "Education",
  "description": "Jai Sri Gurudev",
  "websiteUrl": "https://sjbit.com",
  "logoUrl": "",
  "active": true,
  "createdAt": "2026-05-05T11:31:06",
  "updatedAt": "2026-05-05T11:31:06"
}

## API Testing Using Postman

Postman is used to test all backend REST APIs before connecting them with the frontend.

### Testing Covered

| Test Case | Description |
|---|---|
| Login API Testing | Checked whether user login returns a valid JWT token |
| JWT Token Verification | Tested protected APIs using Bearer Token |
| Create Tool API | Tested adding a new tool using POST request |
| Get All Tools API | Verified whether all tools are fetched from the database |
| Get Tool By ID API | Tested fetching a single tool using its ID |
| Update Tool API | Tested updating existing tool details |
| Delete Tool API | Tested deleting a tool by ID |
| Search and Pagination API | Tested search, sorting, and pagination features |
| Error Response Testing | Checked response for invalid IDs and wrong inputs |

---

## How to Test Protected APIs

1. Send login request:

```http
POST http://localhost:8080/auth/login
2.Copy the JWT token from the response.
3.Open the required API request in Postman.
4.Go to the Authorization tab.
5.Select Bearer Token.
6.Paste the copied JWT token.
7.Send the request and verify the response.



## Common Commands

| Command | Purpose |
|---|---|
| `mvn clean install` | Clean, build, and install the project dependencies |
| `mvn spring-boot:run` | Run the Spring Boot backend application |
| `mvn clean` | Clean the target folder and remove old build files |
| `mvn test` | Run backend test cases |
| `git status` | Check changed files before commit |
| `git add .` | Add all changed files to staging |
| `git commit -m "message"` | Commit the changes with a message |
| `git push origin main` | Push code to GitHub main branch |


## Expected Output

After running the backend successfully, the terminal should show that the Spring Boot application has started.

```text
Tomcat started on port 8080
Started ToolApplication


Author

Hemanth A S
Role: Java Developer 1
Company: CampusPe Internship Program

Conclusion

This project demonstrates backend development using Java, Spring Boot, REST APIs, MySQL, JWT authentication, and Postman API testing.

The project follows a structured backend architecture with Controller, Service, Repository, DTO, and Entity layers. It also includes database integration, API testing, and documentation, making it suitable for internship demo and evaluation.