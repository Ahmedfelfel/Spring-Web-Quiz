<h2>📦 Project A: Monolithic Architecture</h2>
File: README.md (Place in the root of the Monolithic Project)

<h3>📝 Quiz System API (Monolithic)</h3>
A robust, monolithic RESTful backend service designed for creating, managing, and taking quizzes. Built with Java and Spring Boot, this application utilizes a layered architecture to ensure separation of concerns, data integrity, and secure scoring logic.

<img width="3999" height="2199" alt="image" src="https://github.com/user-attachments/assets/58b07a19-e6fc-4801-96c0-de553b809cdf" />


✨ Features
Question Management: Full CRUD capabilities for adding, viewing, and removing questions.

Dynamic Quiz Creation: Automatically generates quizzes by fetching random questions from specific categories.

Secure Quiz Taking: Retrieves questions using DTOs to hide correct answers from the client during the quiz.

Automated Scoring: Calculates results on the server side by comparing user submissions against the database.

Categorization: Questions and quizzes are organized by topics (e.g., Java, Python).

🛠️ Technology Stack
Core: Java 17, Spring Boot

Database: PostgreSQL (via Spring Data JPA)

Build Tool: Maven

Architecture: Layered (Controller, Service, Repository, Model, DTO)

## 📂 Project Structure

The project adheres to a standard layered architecture organized within `com.felfel.quizsystem`:

```text
src/main/java/com/felfel/quizsystem
├── 🎮 controller   # Handles HTTP requests & API endpoints
│    ├── QuizController
│    └── QuestionController
├── 🧠 service      # Core business logic & calculations
├── 💾 repository   # Data access layer (Spring Data JPA)
├── 🗂️ model        # Database entities (Quiz, Question)
└── 📦 dto          # Data Transfer Objects (Request/Response shapes) 
```
🚀 API Endpoints
Quiz Operations
Method	Endpoint	Description
POST	/quiz/create	Creates a new quiz with a random set of questions based on category.
GET	/quiz/get/{id}	Fetches a quiz by ID (returns questions without answers).
POST	/quiz/submit/{id}	Submits user answers and returns the calculated score.


Question Operations
Method	Endpoint	Description
GET	/question/allQuestions	Retrieves all questions available in the database.
POST	/question/add	Adds a new question to the bank.


⚙️ How to Run
Clone the repository.

Configure Database: Update application.properties with your PostgreSQL credentials.

Build: mvn clean install

Run: mvn spring-boot:run
<br>
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
<br>
<h2>📦 Project B: Microservices Architecture</h2>
Below are the README files for the distributed version of the system.

<h3>1. 📂 Service Registry (Eureka)</h3>

🌐 Service Registry (Eureka Server)
The central discovery server for the Quiz Microservices ecosystem. It allows services (Question, Quiz, Gateway) to register themselves and discover each other dynamically without hardcoded URLs.

⚙️ Configuration
The server runs on port 8761. Key configurations in application.properties:

Properties

server.port=8761
# Prevent the registry from registering with itself
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
🚀 How to Run
Prerequisites: JDK 17+, Maven.

Run Command:

Bash

mvn spring-boot:run
Access Dashboard: Open http://localhost:8761 in your browser to view registered services.

<h3>2. 📂 API Gateway</h3>


🚪 API Gateway Service
The single entry point for the microservices architecture. It routes external requests to the appropriate microservices (Quiz or Question) using dynamic routing via Eureka.

<img width="3999" height="2615" alt="image" src="https://github.com/user-attachments/assets/b4cf79cf-ebab-47c0-ba89-fc7982b4ea4d" />



✨ Features
Dynamic Routing: Automatically creates routes based on services registered in Eureka.

Service Discovery: Integrates with Netflix Eureka to find service instances.

Load Balancing: Distributes traffic across service instances (Client-side load balancing).

⚙️ Configuration
Port: 8765

Discovery:

Properties

spring.cloud.gateway.server.webflux.discovery.locator.enabled=true
spring.cloud.gateway.server.webflux.discovery.locator.lower-case-service-id=true
🚀 How to Run
Ensure Service Registry is running on port 8761.

Run Command: mvn spring-boot:run

Usage: Access services via the gateway:

http://localhost:8765/quiz-service/**

http://localhost:8765/question-service/**

<h3>3. 📂 Question Service</h3>

❓ Question Service
A specialized microservice responsible for the "Question Bank". It handles the storage, retrieval, and randomization of questions. It serves as the data provider for the Quiz Service.

🏗️ Architecture
Controller: Exposes endpoints for CRUD and generation logic.

Service: Handles randomization logic and score calculation.

Repository: manages Question entities in the database.

🔌 API Endpoints
Method	Path	Description
GET	/question/allQuestions	Get all questions.
GET	/question/category/{cat}	Get questions by category.
POST	/question/add	Add a new question.
GET	/question/generate	Returns a list of random IDs (internal use).
POST	/question/score	Calculates score based on answers.


🚀 How to Run
Ensure Service Registry is running.

Create a PostgreSQL database for this service.

Update application.properties with DB credentials.

Run: mvn spring-boot:run

<h3>4. 📂 Quiz Service</h3>

📝 Quiz Service
The core orchestration service for the application. It manages the lifecycle of a "Quiz" by communicating with the Question Service using OpenFeign.

<img width="3999" height="3199" alt="image" src="https://github.com/user-attachments/assets/b826d287-af87-4cb9-afae-bed4fa638ee8" />

🔄 How It Works
Create Quiz: Accepts a category and number of questions -> Calls Question Service via Feign to get random IDs -> Saves Quiz entity (Title + IDs).

Take Quiz: Fetches Quiz -> Calls Question Service with IDs -> Receives Questions -> Maps to QuestionDto (hides answers) -> Returns to user.

Submit Quiz: Receives User Answers -> Sends to Question Service -> Returns Score.

🔌 API Endpoints
Method	Path	Description
POST	/quiz/quiz	Create a quiz (Body: title, category, noQues).
GET	/quiz/quiz/{id}	Get quiz questions (Client facing).
POST	/quiz/submit/{id}	Submit answers and get score.


🚀 Usage Example (Create Quiz)
Bash

curl --location 'http://localhost:8090/quiz/quiz' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Java Basics",
    "category": "java",
    "noQues": 5
}'
⚙️ Setup
Ensure Service Registry and Question Service are running.

Configure PostgreSQL connection in application.properties.

Run: mvn spring-boot:run
