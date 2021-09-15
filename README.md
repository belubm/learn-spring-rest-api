# Rest API Project - Examples with CRUD Operations

## Seting up

### mysql DB creation and setup
 - Login to mysql shell using root:
 	- command: **mysql -u root -p**
	- when prompted provide your root password
 - Create database:
	- command: **create database spring_db;**
 - Create a mysql Local User (recommended), it's bad practice to use root user:
	- command: **create user 'belu'@'localhost' identified by '@pa55word';**
	- 'belu' can be replaced by the your personal user name
	- '@pa55word' can be replaced with your personal password
 - Grant user privileges to the created database:	
	- command: **grant all privileges on spring_db.* to 'belu'@'localhost';**
	- use your own username replacing 'belu' with it like this: 'your_user_name'@'localhost'
	- command: **flush privileges;**
	- the above command is used for changes to take place immediately 
	- command: **exit**
 - Login to mysql shell using the newly created user:
	- command: **mysql -u belu -p**
	- when prompted provide your user password 
	- command **show databases;**
	- you should see spring_db as a database available for use

***Note***
*If database name,  username and password used are the same as in the above example, you can run the aplication from your favorite IDE or using the command: mvn spring-boot:run*
*Otherwise please replace in the application.properties file the database name, username and password with your own and then run the application*

### application.properties:
	spring.datasource.url=jdbc:mysql://localhost:3306/**your_database_name**
	spring.datasource.username=**your_user_name**
	spring.datasource.password=**your_password**
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
	spring.jpa.hibernate.ddl-auto=update
