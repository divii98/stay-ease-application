# StayEase - Hotel Room Booking API
StayEase is a RESTful API service built using Spring Boot to streamline the room booking process for a hotel management aggregator application. This service uses MySQL for data persistence, JWT for stateless authentication, and role-based access control.
## Key Features

#### User Management
* User Registration & Login
  * Users can register by providing their email, password, first name, last name, and role.
  * Passwords are securely hashed using BCrypt.
  * Role defaults to CUSTOMER if not specified.
  * JWT tokens are generated upon successful registration or login.
#### Hotel Management
* Public Features
  * Anyone can browse available hotels.
* Admin Features
  * Create and delete hotels.
* Hotel Manager Features
  * Update hotel details.
#### Booking Management
* Customers can book a room (one room per request).
* Hotel Managers can cancel bookings.
* Booking only allowed if rooms are available.

## Technologies Used
* Spring Boot (for RESTful APIs)
* Spring Security (for JWT)
* MySQL (for persistence)
* JPA/Hibernate (for ORM)
* BCrypt (for password hashing)
* Maven (for project management)
* JUnit (for unit testing)

## API Endpoints

| S.No. | Access   | Method | Endpoint                          | Description                         |
|-------|----------|--------|-----------------------------------|-------------------------------------|
| 1     | Everyone | POST   | /stay-ease/v1/user/register       | Register a new user.                |
| 2     | Everyone | POST   | /stay-ease/v1/user/login          | Login user with credentials.        |
| 3     | Everyone | POST   | /stay-ease/v1/auth/token          | Generate JWT token.                 |
| 4     | Admin    | POST   | /stay-ease/v1/hotel/add           | Add new hotel.                      |
| 5     | Admin    | DELETE | /stay-ease/v1/hotel/{id}          | Delete existing hotel.              |
| 6     | Manager  | PUT    | /stay-ease/v1/hotel/{id}          | Update details of a specific hotel. |
| 7     | Manager  | GET    | /stay-ease/v1/hotel/bookings      | Get list of all existing bookings.  |
| 8     | Manager  | DELETE | /stay-ease/v1/hotel/bookings/{id} | Cancel existing booking.            |
| 9     | Customer | POST   | /stay-ease/v1/hotel/6/book        | Book a specific hotel.              |


## Pre-requisites
1. Java 21+
2. Gradle (or use the Gradle wrapper provided in the project)
3. SQL

## Installation
##### 1. Clone the repository:
```bash
git clone https://github.com/divii98/stay-ease-application
cd your-repo-name
```

#### 2. Configure Database Connection for local run:
Make sure to set up your database (e.g., MySQL) and update the database configurations in application.properties.
```
spring.datasource.url=jdbc:mysql://localhost:3306/stay_ease
spring.datasource.username=root
spring.datasource.password=password
```

##### 4. Build and Run the Service:

Use Gradle to build and run the application
``
./gradlew bootRun
``

##### 5. Access the Application:

By default, the application will run at for local:
> http://localhost:8080

## Postman Collections

For testing the API endpoints, you can use the Postman collection provided in the link below:

[Stay Ease Service API Collection](https://www.postman.com/apicollections-7830/apicollections/collection/8ix384m/stay-ease-apis)

Set Stay_Ease_Render environment for running application deployed in render.
