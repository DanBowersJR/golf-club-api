# ⛳ Golf Club API  

A RESTful API for managing **Golf Club Members** and **Tournaments**, built with Spring Boot, MySQL, JPA, and Docker.  

This project demonstrates object-relational mapping (ORM), search endpoints, and containerized deployment with Docker.  

---

## 🚀 Features  

### Members  
- Add new members  
- Get members by ID  
- Search members by:  
  - Name  
  - Email  
  - Phone number  
  - Start date of membership  
  - Duration of membership  

### Tournaments  
- Create new tournaments  
- Get tournaments by ID  
- Search tournaments by:  
  - Start date  
  - End date  
  - Location  
- Add members to tournaments  
- List all members in a tournament  

---

## 📖 API Endpoints  

### Members  

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/members` | Create a new member |
| `GET`  | `/members` | Get all members |
| `GET`  | `/members/{id}` | Get member by ID |
| `GET`  | `/members/search?name={name}` | Search members by name |
| `GET`  | `/members/search?phone={phone}` | Search members by phone |
| `GET`  | `/members/search?startDate={date}` | Search members by membership start date |

---

### Tournaments  

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/tournaments` | Create a new tournament |
| `GET`  | `/tournaments` | Get all tournaments |
| `GET`  | `/tournaments/{id}` | Get tournament by ID |
| `POST` | `/tournaments/{id}/members` | Add a member to a tournament |
| `GET`  | `/tournaments/{id}/members` | List all members in a tournament |
| `GET`  | `/tournaments/search?location={location}` | Search tournaments by location |

---

## 🐳 Running with Docker  

### 1. Build and Start Containers  
```bash
docker-compose up --build
