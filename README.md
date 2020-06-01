# PhoneBook_backend
Add more functions for the back end

# Swagger
- Add Swagger for Controllers
- Go to the url with Swagger UI: http://localhost:8080/swagger-ui.html#/

# JWT
- User can signup new account, or login with username & password.
- By User’s role (admin, moderator, user), we authorize the User to access resources (role-based Authorization)
- Spring Security will manage cors, csrf, session, rules for protected resources, authentication & authorization along with exception handler.
- The database we will use is MongoDB which can be accessed by the help of Spring Data MongoDB.

# Testing
- Implement Unit Test and integrate unit test tool between JUnit and Mockito.
- Implement Integration testing the APIs in the controller with @WebMvcTest
