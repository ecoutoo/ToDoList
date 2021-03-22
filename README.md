Coverage: 83.9%
# ToDoList

ToDoList is a full stack application with complete CRUD functionality on the two models: Lists and Items. Lists are the main to do lists and Items are the individual tasks in each to do list. The font end is user acceptance tested with Selenium. The back end is tested with Unit testing and integration testing.

Diagrams and presentation can be found in the "documentation" folder.

## Getting Started

To run the program you need to clone it to your local machine. Then navigate to the directory "todolist" and open up a terminal inside that directory. Make sure you have Java and MySQL installed (see Prerequisites). To run the program type "todolist-0.0.1-SNAPSHOT.jar" inside the console.

If you wish to do further devlopment and testing you will need to compile the build the project using maven once changes have been made. This can be done by navigating to "todolist" opening the console and running "mvn clean package". Your new built program will now showup in the "todolist\target" for you to run as previously described. 

Also see "Running"

### Prerequisites

Versions listed are the versions known to work, other versions of these prerequisites might work but are not guaranteeded to do so.

Java 1.8-1.14 (https://www.java.com/en/download/help/download_options.html)

MySQL 8.0 (https://dev.mysql.com/doc/refman/8.0/en/windows-installation.html)

Java IDE - Eclipse (https://www.eclipse.org/downloads/packages/installer) - Or any other java IDE you prefer

Selenium

JUnit

Package dependencies can be found in the "pom.xml" file

### Running

Step 1: Run the "todolist-0.0.1-SNAPSHOT.jar" jar file to initiate the backend and spring-boot repository

Step 2: Run the front end located in "WebApp" folder to initiate the front end of the application

## Running the tests

The automated tests run when the program is being packaged by maven, if you wish to run them in Eclipse on your package explorer navigate to "todolist" then right click "src/test/java" and select coverage as JUnit test. This will run the automated testes inside the IDE and highlight tested/untested code and report back an test successes/errors/failures.

### Unit Tests 

There are 6 unit tests covering the following classes: ItemSerivce, ItemMapper, ItemController, ToDoListService, ToDoListMapper, ToDoListController

### Integration Tests

There is 1 integration test covering the class: ItemService

### User Acceptance Tests

There are 3 user acceptance tests covering the following CRUDS: Create List, ReadAllLists, Delete List

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* Spring Boot

## Authors

* **Vicente Conte Couto** [vicentecontecouto](https://github.com/ecoutoo)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Thanks to "Calvin Harris" for making nice tracks to code to.
