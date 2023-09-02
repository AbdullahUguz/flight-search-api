# Flight Search Api

It is the backend api of flight search application.

## Technologies Used

- Spring Boot
- Jwt (for authentication)
- Swagger (for documentation)
- PostgreSQL
- Docker
- Mock Api (with Postman)

## About the Project

The Flight Search API is an application that lists flights based on the departure location, arrival location, departure date, and return date.
If no return date is provided, it indicates a one-way flight; if a return date is provided, it indicates a round-trip flight.
Additionally, it performs CRUD (Create, Read, Update, Delete) operations for airports and flights.
Also, it sends requests to the mock API I have set up using Postman every night at 12 o'clock to update and store the latest flights in the database.

## Installation

1. Install Docker on your system if it's not already installed.
2. Download or clone the project source code.
3. In the terminal, start the PostgreSQL database on Docker using the following command:
 ```bash
 docker run --name my-postgres-db -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres
 ```
4. After creating the database, please enter the database-related information into the application properties.
5. Then start the application on http://localhost:8080/swagger-ui.html#/

## Usage
- To use this application, you need to first create a user in the system.
- After, log in with the user you have added. When you log in, it will return a token to you.
- In the Swagger interface shown below for adding an airport, it's important to mention that a token should be entered in the designated field.
This token will be used for authentication in all other requests as well.

![token](https://github.com/AbdullahUguz/day_off_schedule_frontend/assets/73312086/8fcfa648-cb26-40c1-ac6a-55864807eaaa)

When adding a city, if the city you are trying to add is not found in the created cache within the City class, you will receive a "City not found" error.

## Swagger Screen
![swaggerscreen](https://github.com/AbdullahUguz/day_off_schedule_frontend/assets/73312086/4b26ff5f-1cea-4e2f-be48-24d0b8cf76eb)


