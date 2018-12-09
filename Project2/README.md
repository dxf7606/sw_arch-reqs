# SWEN-440:  Project 2
Fall 2018

## Online Ordering System

### Overview:

The system consists of three main components:  a command line order 
entry module, a central processing module and a data storage module. 
 
 - The order entry module handles all communication with the user
 - The data storage module handles all communication with the underlying 
   file system.  
 - The central processing system coordinates the activities across 
   the system taking requests from the order entry module and
   interacting with the data storage layer to fulfill the request.
   
## Architecture
Previously, much of the controller code centered around reading directories and parsing files into objects. With the switch in storage mechanism from text files to the SQLite database, we have been able to abstract that mapping behavior into the `DataManager` class. `Controller` is now only responsible for conveying information between the presentation and data layers.
   
## Development
The **Online Ordering System** was developed using the Java language.  The
developers used IntelliJ IDEA, atom, and vsCode as their principle
development tools.  Development work was done in both Ubuntu and Windows 10.
However, the customer has explicitly asked that the final
product run on Windows 10.

### Coding Style
The management team _fired_ the last set of architects and developers because they could
not agree on architectural direction or coding styles and conventions.  This
inability has led to a slightly brittle system that is at risk of falling behind its
primary competitor, that South American company, Amazon.   

The management team strongly believes that consistency in coding will yield a better result
and a better final grade.

### Dependencies
 - Java 8 : Refactoring work done over the life of the ordering system has
 resulted in dependencies on newer versions of Java.  Currently, streams, lambda expressions, and diamond operators are used.  
 
    The customer is open to upgrading to newer Java versions if the architecture
    team identifies functionality that would enhance functionality of the
    system or readability of the resulting code.
 - ORMLite and SQLite3: The database for the ordering system is located in the project root in the `online_ordering_system.sqlite3` file. It is accessed programmatically through the [ORMLite](http://ormlite.com/) ORM.
 - Lombok Library: Lombok annotations are used in places to auto-generate getters and
 setters.  
 - JUnit Library:  Included but never used.

### Building
The development team chose Maven as their build tool.  Maven 3.1.0 and 3.3.9
have both been successfully used.  

With Maven installed, running **mvn clean install** from the root install directory should 
successfully build the program.  It is expected that every build will also execute the test
lifecycle ensuring that unit tests all still pass.

### Testing
The development team pushed developing unit tests to the
 end of their development cycle.  As is often the case, this was a poor
 decision.  Time was never allocated and no tests were written.
 
The stakeholders have high hopes that the new _Crack Teams_ taking over
the project have a better sense of the importance of unit testing
and deliver code with good unit tests and high coverage.  

## Running the Online Ordering System
Instructions provided require that you be using the industry-standard [IntelliJ](https://www.jetbrains.com/idea/) IDE. The program can still be run from the command-line or using another IDE such as NetBeans or Eclipse, but those may require manual configuration of the classpath and are not covered here.
1. Run `mvn clean install` to install dependencies
1. Right-click  `menutest.java` located in the `org.rit.swen440` package and click "Run".
1. If you wish to view the contents of the database, open `online_ordering_system.sqlite3` in your preferred SQLite viewer or add `Project2/online_ordering_system.sqlite3` as a data source in the tab on the right-hand side.

**It should be noted that a large part of the compensation for working on
this project is dependent on the management team being able to evaluate it.
A key part of evaluation will be installing, building and running the 
system you produce.
Additionally, explaining architecture choices going forward is essential.**
