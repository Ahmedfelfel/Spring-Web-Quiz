# Quiz System

A monolithic Spring Boot application for managing questions and conducting quizzes. This system allows users to manage a question bank and create dynamic quizzes based on categories.

## Features

- **Question Management**: Full CRUD operations for questions.
- **Dynamic Quiz Creation**: Generate quizzes with a specified number of questions from a particular category.
- **Quiz Participation**: Retrieve quizzes without showing the correct answers to users.
- **Quiz Submission**: Submit answers and receive a calculated score.

## Technologies Used

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## Prerequisites

- JDK 21 or higher
- PostgreSQL database
- Maven 3.x

## Database Configuration

Update the `src/main/resources/application.properties` file with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/quizSystem
spring.datasource.username=your_username
spring.datasource.password=your_password
```

The application is configured to automatically update the database schema (`spring.jpa.hibernate.ddl-auto=update`).

## Getting Started

1.  **Clone the repository**:
    ```bash
    git clone <repository-url>
    cd "Quiz System"
    ```

2.  **Build the project**:
    ```bash
    mvn clean install
    ```

3.  **Run the application**:
    ```bash
    mvn spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Question Controller (`/question`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| GET | `/question/allQuestions` | Retrieve all questions. |
| GET | `/question/category/{category}` | Retrieve questions by category. |
| GET | `/question/question/{id}` | Retrieve a question by its ID. |
| POST | `/question/question` | Add a new question (Request Body: `Question` object). |
| PUT | `/question/question` | Update an existing question (Request Body: `Question` object). |
| DELETE | `/question/question/{id}` | Delete a question by ID. |

### Quiz Controller (`/quiz`)

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/quiz/quiz` | Create a quiz (Params: `title`, `category`, `NoQues`). |
| GET | `/quiz/quiz/{quizId}` | Get a quiz by ID (returns questions without answers). |
| POST | `/quiz/submit/{quizId}` | Submit answers for a quiz (Request Body: List of `AnswerDto`). |

## Project Structure

```text
Quiz System/
├── src/
│   ├── main/
│   │   ├── java/com/felfel/quizsystem/
│   │   │   ├── controller/      # API Controllers
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── model/           # Entity Models
│   │   │   ├── repository/      # JPA Repositories
│   │   │   └── service/         # Business Logic
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Unit and Integration Tests
├── pom.xml                      # Maven dependencies
└── README.md                    # Project documentation
```

## License

This project is licensed under the MIT License.
