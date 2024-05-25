# Spring Boot Book Management Project

This project is a Spring Boot application for managing books, enabling the creation, reading, updating, and deletion of books via a RESTful API.

## Prerequisites

Before getting started, ensure you have the following tools installed on your machine:
- **Java Development Kit (JDK)**
- **Maven**:
- 
## Database Configuration
spring.h2.console.path=/h2-console

## Swagger Configuration
springdoc.swagger-ui.path=/swagger-ui.html


## Clone the Repository
git clone 

## Build the Project:
mvn clean install

## Run the Application
mvn spring-boot:run

## Accessing the API
### Create a Book
URL: POST http://localhost:8081/books/AddBook
Body: JSON
{
  "author": "Author Name",
  "title": "Book Title",
  "isbn": "9781234567897",
  "publicationYear": "2024-01-01"
}

### GeAllBook
URL: GET http://localhost:8081/books/GetAllBooks?page=0&size=10
### updatebook
URL: PUT http://localhost:8081/books/updatebook/{id}
{
  "author": "Updated Author",
  "title": "Updated Title",
  "isbn": "9789876543211",
  "publicationYear": "2020-01-01"
}
### deletebook
URL: DELETE http://localhost:8081/books/deletebook/{id}
### GetbookbyId
URL: GET http://localhost:8080/books/GetbookbyId/{id}




