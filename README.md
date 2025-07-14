# 🗳️ Poll Management API

A Spring Boot 3.x RESTful API for creating polls, casting votes, and viewing poll results. Built using Java 17, JPA, H2, and OpenAPI (Swagger).

---

## 🚀 Features

- Create a new poll with question, options, and expiry
- Cast a vote for a specific option in a poll
- View poll results (vote counts)
- Input validation using `jakarta.validation`
- Consistent API responses via `ApiResponse<T>`
- Swagger UI for API testing and documentation
- JUnit + MockMvc test coverage

---

## 🛠️ Tech Stack

| Layer        | Technology              |
|-------------|--------------------------|
| Language     | Java 17                 |
| Framework    | Spring Boot 3.x         |
| Build Tool   | Gradle                  |
| Persistence  | Spring Data JPA         |
| Database     | H2 (in-memory)          |
| API Docs     | springdoc-openapi (Swagger) |
| Testing      | JUnit 5 + Mockito + MockMvc |

---

## 🧾 API Endpoints

### ✅ Create Poll

```http
POST /api/polls

Request Body:
{
  "question": "Your favorite language?",
  "options": ["Java", "Python", "Go"],
  "expiryDateTime": "2025-07-20T23:59:59"
}
Reponse: 
{
  "status": 201,
  "message": "Poll created successfully",
  "data": {
    "id": 1,
    "question": "...",
    "options": [...],
    "expiryDateTime": "..."
  }
}

### ✅ Cast Vote

```http
POST /api/polls/vote

Request Body:
{
  "pollId": 1,
  "optionId": 2,
  "userId": "user123"
}

Response:
{
  "status": 200,
  "message": "Vote recorded",
  "data": "Success"
}

### ✅ View Results

```http
GET /api/polls/{pollId}/results

Response:
{
  "status": 200,
  "message": "Poll results fetched",
  "data": {
    "pollId": 1,
    "question": "...",
    "expiryDateTime": "...",
    "results": {
      "Java": 10,
      "Python": 5
    }
  }
}

▶️ Getting Started

**Prerequisites**:
  1. Java 17+
  2. Gradle (or use ./gradlew)

Run the App
1. Open the terminal
2. run the command : ./gradlew clean build
3. then run command : ./gradlew bootRun
4. Torun the test cases run the command : ./gradlew test


📑 Swagger/OpenAPI
Run the app and visit:
👉 http://localhost:8080/swagger-ui/index.html

📁 Project Structure

src
├── main
│   ├── java/com.assignment.pollapi
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── repository
│   │   ├── service
|   |   |     └── impl         
│   │   └── config
│   └── resources
│       └── application.properties
└── test
    └── java/com.assignment.pollapi
        └── controller

🙋 Author
Intizar Malik
Sr. Backend Developer (Java)
Email: intizarmalik525@gmail.com
LinkedIn: https://www.linkedin.com/in/intizar-malik-a82730154/
















