# Library CRUD Application

This is a simple CRUD application for managing a library of books. It is built using Java Spring Boot and PostgreSQL, and containerized with Docker.

## Features

1. **Create Book**: Add a new book with title, author, and ISBN.
2. **Read Books**: Retrieve the list of all books.
3. **Update Book**: Update details of an existing book.
4. **Delete Book**: Remove a book from the list.
5. **Search Books**: Search for books by title or author.
6. **Pagination**: Implement pagination for the list of books.
7. **Sorting**: Sort books by title, author, or ISBN.
8. **Validation**: Validate input data before creating or updating a book.

## Endpoints

Here are the available endpoints and example curl commands to interact with the application:

1. Create a Book

        curl -X POST "http://localhost:8080/api/books" -H "Content-Type: application/json" -d '{
          "title": "The Catcher in the Rye",
          "author": "J.D. Salinger",
          "isbn": "9780316769488"
        }'

2. Get a Book by ID
    
        curl -X GET "http://localhost:8080/api/books/1"

3. Get All Books (with Pagination and Sorting)

        curl -X GET "http://localhost:8080/api/books?page=0&size=10&sortBy=title&sortDir=asc"

4. Update a Book

        curl -X PUT "http://localhost:8080/api/books/1" -H "Content-Type: application/json" -d '{
          "title": "The Catcher in the Rye",
          "author": "J.D. Salinger",
          "isbn": "9780316769488"
        }'

5. Delete a Book

       curl -X DELETE "http://localhost:8080/api/books/1"

6. Search Books by Title or Author
    
        curl -X GET "http://localhost:8080/api/books/search?keyword=Salinger"

## Configuration

The application is configured using environment variables for the database connection. These are set in the docker-compose.yml file:

    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/books
      - SPRING_DATASOURCE_USERNAME=dev
      - SPRING_DATASOURCE_PASSWORD=1234


## Stopping the Application

To stop the application, run:

    docker-compose down

This will stop and remove the containers created by Docker Compose.
Cleanup

To remove all containers, networks, and volumes created by Docker Compose, run:

    docker-compose down -v
