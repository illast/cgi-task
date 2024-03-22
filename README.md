

## Technologies
- Java 21
- Spring Boot 3.2.3
- Vue.js 3.2.13
- H2 Database 2.2.224
- The Movie Database (TMDB)

## Installation
- Clone the repository.
- Wait for Maven to install dependencies.
- Run backend/src/main/java/com/example/demo/**DemoApplication.java**.
- In the terminal, navigate to the frontend folder.
- Install dependencies with `npm install`.
- Execute `npm run serve`.
- Navigate to http://localhost:8081 in your web browser.

## Endpoints

### User
- **GET /api/users**: Retrieves a list of all users.
- **POST /api/users**: Adds a new user.
- **GET /api/users/{userId}/films**: Retrieves films associated with a specific user.
- **POST /api/users/{userId}/films**: Associates a film with a specific user.

### Film
- **GET /api/films**: Retrieves a paginated list of films with optional filtering.
- **POST /api/films**: Adds a new film.
- **GET /api/films/generate**: Generates films from an external API and adds them.
- **GET /api/films/{id}**: Retrieves details of a specific film by its ID.

## What was done
- Listing of all films.
- Filtering functionality based on genre, location, rating, and language.
- Accessing another API to generate films requires navigating to a specific endpoint in the address bar.
- Clicking on any film image redirects you to its dedicated page.

## What wasn't done
- Everything else due to lack of time. =)
