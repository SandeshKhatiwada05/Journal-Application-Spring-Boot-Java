# 📓 Journal Application (Spring Boot Backend)

A backend-only journal management system built with Spring Boot. Features user roles, authentication, and journal CRUD operations. Tested via Postman.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Hibernate & JPA
- MySQL
- Postman (for testing)

---

## 📁 Project Structure

```
📁 journal-application/
├── 📁 config/                  # Security configuration
│   └── SecurityConfig.java
├── 📁 controller/              # REST controllers
│   ├── AdminController.java
│   ├── JournalController.java
│   ├── PublicController.java
│   └── UserController.java
├── 📁 entity/                  # Entity classes
│   ├── JournalEntity.java
│   └── UserEntity.java
├── 📁 repository/              # Spring Data JPA repositories
│   ├── JournalRepository.java
│   └── UserRepository.java
├── 📁 service/                 # Business logic
│   ├── JournalService.java
│   ├── UserService.java
│   └── UserDetailsServiceImpl.java
├── application.properties      # App configuration
├── JournalApplication.java     # Main class
└── README.md
```

---

## 🔐 Authentication & Roles

- JWT-based authentication
- User roles: `USER`, `ADMIN`
- Role-based access control for endpoints

---

## 📮 API Testing

- All APIs were tested using **Postman**
- Public, User, Admin endpoints are separated
- Sample requests include login, register, create journal, view journals, etc.

---

## 🚀 Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/SandeshKhatiwada05/Journal-Application-Spring-Boot-Java.git
   ```
2. Configure `application.properties` for your MySQL DB
3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Use Postman to interact with APIs

---

## ✍️ Author

Sandesh Khatiwada

---


