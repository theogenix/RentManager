# RentManager

4th year academic project

Java web application to manage clients, their rents and vehicles associated.

![image](https://user-images.githubusercontent.com/123560349/233851707-e81aa34d-4a90-434d-83db-75cbd9a712e2.png)

## About this project 

This project's purpose is to create a web application to manage customers renting vehicles . On the website, clients, vehicles and rents are displayed. 
![image](https://user-images.githubusercontent.com/123560349/233852004-a8e7fd9e-5c25-4e02-9cf4-7f838370fabd.png)
The project's admin can add, remove and edit a customer. The customer's details can also be displayed with his informations (such as email, surname, lastname), his reservations and vehicles he has rent.
Project's admin can also do the same for vehicles and rents.

## Constraints
These constraints have been implemented to optimize the web application:

Clients who are under 18 years of age cannot be created. This constraint is in place to ensure that the system complies with legal requirements related to the creation of accounts for minors.

Clients cannot be created if the email address provided is already in use by another user. This constraint helps to prevent duplicate accounts being created with the same email address, which could cause confusion and inefficiencies in the system.

The first and last names of a client must be at least 3 characters long. This constraint is in place to ensure that the names provided for clients are complete and accurate, which can help to prevent errors in the system.

A car cannot be reserved twice on the same day by any user. This constraint helps to prevent scheduling conflicts and ensures that cars are available to other users who may need them.

A car cannot be reserved for 30 consecutive days without a break. This constraint is in place to prevent cars from being tied up for an extended period of time, which can lead to inefficiencies and lost opportunities for other users who may need the car.

A car must have a model and a manufacturer, and the number of seats must be between 2 and 9. This constraint ensures that all necessary information is provided about the cars in the system, and helps to ensure that the cars are appropriate for the needs of users.

If a client or a vehicle is deleted, then the associated reservations should also be deleted.


## How to launch the web application

1. Open the terminal/console and navigate to the project directory.
2. Type the command "mvn tomcat7:run" and press Enter to start the server.
3. Wait for the server to start up, then open your web browser.
4. In the address bar, type "http://localhost:8080/rentmanager/home" and press Enter.
5. The application homepage should now be displayed in your browser.

Note: Make sure you have Maven and Tomcat installed on your system before running the application. You can download them from their official websites if you don't have them already.




.


