# phlogiston
front-end created by https://github.com/Chernikov-Daniil
back-end created by https://github.com/SterkhovAV

Web-app for counting water and steam properties by IAWSP-97 methodic.

Stack of technoligies: Spring Boot 2.7.5, Spring Security, Hibernate, Spring JPA, Liquibase
Build by Gradle

## before start
- jdk 1.8+
- PostgreSQL
- change in "application.properties"

       spring.datasource.url=your DB address
       spring.datasource.username=your login
       spring.datasource.password=your password

If you don't want to change base-login-password:
    - create local database and user;
    - start SQL Shell (login to database "postgres" under login "postgres" and your password)

       CREATE DATABASE phlogiston;
       CREATE USER phlogiston_user WITH PASSWORD 'password';
       GRANT ALL PRIVILEGES ON DATABASE phlogiston to phlogiston_user;
       \c phlogiston;
       CREATE SCHEMA phlogiston;
       GRANT ALL PRIVILEGES ON SCHEMA phlogiston to phlogiston_user;

now database and user created!

## problems solving
 Data base clear instruction:
    start SQL Shell (login to database "postgres" under login "postgres" and your password)

        DROP DATABASE phlogiston;
        CREATE DATABASE phlogiston;
        GRANT ALL PRIVILEGES ON DATABASE phlogiston to phlogiston_user;
        \c phlogiston;
        CREATE SCHEMA phlogiston;
        GRANT ALL PRIVILEGES ON SCHEMA phlogiston to phlogiston_user;

## API
Base address - http://localhost:7080/ (you can change it in "application.properties")

### Authentication and users API 


#### Authentication of user http://localhost:7080/auth/login (POST)
with params "username" and "password"
    http://localhost:7080/auth/login?username=admin&password=admin

result:
 - success 


#### Registration of new user http://localhost:7080/auth/registration (POST)
Request:

       {
       "username": "user1",
       "password": "admin",
       "email": "user2@lsdsdsdweo11l.ru",
       "lastName": "user2",
       "firstName": "user2",
       }

Full list of params that can be added (some of them can be nullable(?))

        username: String (max size 30)
        password: String 
        email: String (max size 45)
        lastName: String (max size 50)
        firstName: String (max size 50)
        middleName: String? (max size 50)
        organisation: String? (max size 70)
        position: String? (max size 70)
        phone: String? (max size 50)

Success answer (with 200 HTTP Status):

    {"message": "User successfully saved, activation link sent to your email"} 

Error answer is something like this (with 400 HTTP Status):

    {
    "messages": [
        "User with this name exists",
        "User with this email exists"
        ]
    }

!!After registration user is not active and must be activated through activation link that was sent to email.

#### Activation of new user http://localhost:7080/auth/activate?activation_code=generated_UUID (GET)

#### Get authenticated user info http://localhost:7080/auth/get-user (GET)


### Count API
#### Count one phase params - http://localhost:7080/onePhase/calc (POST)
Request:

       { 
           "initialParams":{"PRESSURE":10,"TEMPERATURE":500},
           "requestParams": [
               "SPECIFIC_VOLUME",
               "DENSITY"
           ]
       }

Now, as initial params you can use only temperature and pressure.
!! Temperature in K !!
!! Pressure in MPa !!

You can add to request this params if it needable:

       SPECIFIC_VOLUME, 
       DENSITY, 
       SPECIFIC_ENTROPY, 
       SPECIFIC_ENTHALPY, 
       SPECIFIC_INTERNAL_ENERGY,
       SPECIFIC_HEAT_CAPACITY_P, 
       SPECIFIC_HEAT_CAPACITY_V

Answer:

       {
           "initialParams": {
               "PRESSURE": 10.0,
               "TEMPERATURE": 500.0
           },
           "countResult": {
               "SPECIFIC_VOLUME": 0.01972474672402399,
               "DENSITY": 50.697735894476054
           }
       }

#### Save result (for authorized user) - http://localhost:7080/onePhase/save (POST)
Request:

       {
           "initialParams": {
               "PRESSURE": 10.0,
               "TEMPERATURE": 500.0
           },
           "countResult": {
               "SPECIFIC_VOLUME": 0.01972474672402399,
               "DENSITY": 50.697735894476054
           }
       }

Answer will be 200 HTTP status with "Saved" message

#### Get results (for authorized user) - http://localhost:7080/onePhase/get-user-results (GET)

Answer will be empty list or results list








