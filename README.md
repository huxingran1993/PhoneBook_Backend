# PhoneBook_backend
Add more practical back end functions for the full-stack web application in : https://github.com/huxingran1993/PhoneBook

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

# Docker
- Dockerize PhoneBook_backend to run it in an isolated environment, a.k.a. container.
- A container is a standardized unit of software that assembles code, runtime, dependencies, settings, and initialization in a single package that you can run reliably from one computing environment to another. 
- Image ID of PhoneBook_backend: c9e78c22f4ed
- DockHub: https://hub.docker.com/repository/docker/huxingran/phone_book_backend/general

# Jenkins
- Install Jenkins Blue Ocean:\
\
docker pull jenkinsci/blueocean \
\
docker run -u root --rm -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock jenkinsci/blueocean
