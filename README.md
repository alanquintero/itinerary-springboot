## Welcome to the Itinerary project

### Description
Itinerary WEB APP is a simple java web application that keeps a record of itineraries.

Functionality:

* Show the list of available flights
* You can Book a Flight! Just click on the Book button to fill the information
* To Book a Flight you need to enter your First name, Last name and click on Book button
* If a flight is still available you will receive a message in the screen with the confirmation
* If a flight is no longer available (because someone else already booked it) then try to be faster the next time!
* If a flight is not available anymore then it will disappear from the list of available flights when you reload the page

### How to Run it
#### Database
1. Import to MySQL Workbench the file **itinerryDb.sql** located in **src/main/resources**

2. Modify the file **application.properties** with your Database credentials. **application.properties** location: **src/main/resources**

Example:

* spring.datasource.url=jdbc:mysql://**127.0.0.1:3306/itinerary**
* spring.datasource.username=**root**
* spring.datasource.password=**root**

### Run it
1. Import the project to your favorite IDE

2. Open the **StartWebApplication.java** file located in: **src/main/java/itinerary/task**

3. Run **StartWebApplication** file as a **Java application**

4. Open your browser and go to: **http://localhost:8080/**

5. Have fun!

### Technologies
* Java 8
* Spring Boot 2
* JPA
* JavaScript
* HMTL5
* CSS3
* Bootstrap
* MySQL


