
E-commerce-for-DB-demonstration
## Overview
## Database Overview

This is a simple e-commerce website which demonstrates the knowledge of database implementation through different use cases.
It was developed over 2 weeks for a Database Class project and was a team effort.

Running the Application:
This application is a Java EE application built with Apache Maven and is typically hosted on an
Apache Tomcat server.
Prerequisites:
• You need to have Apache Maven installed. You can download it from
https://maven.apache.org/
• You need to have Apache Tomcat installed. You can download it from
https://tomcat.apache.org/
Instructions:
1. Run the following command in the `<Path>/TCSS545-project` directory of the project:
‘mvn clean package’
This command will create an `OnlineShopping-0.0.1-SNAPSHOT.war` file in the
`<Path>/TCSS545-project/target` directory.
2. Copy the generated `.war` file into the `<apache_tomcat_directory>/webapps/`.
3. Example: `/apache-tomcat-10.1.192/webapps/OnlineShopping-0.0.1-SNAPSHOT.war`.
4. Restart the Tomcat server.
5. Access the login page of the application by navigating to
[http://localhost:8080/OnlineShopping-0.0.1-
SNAPSHOT/login.jsp](http://localhost:8080/OnlineShopping-0.0.1-
SNAPSHOT/login.jsp) in your web browser.
These steps will deploy the application to Tomcat and make it accessible via the provided URL.

### ItemMaster Table
- itemID : INT
  - Unique identifier for the item (integer type)
- name : VARCHAR(255)
  - Name of the item (string type, maximum 255 characters)
- price : DECIMAL(10, 2)
  - Price of the item (decimal type, 10 digits in total, 2 digits after the decimal point)

### Inventory Table
- inventoryID : INT
  - Unique identifier for the inventory (integer type)
- itemID : INT
  - Identifier for the item (integer type)
- stockQuantity : INT
  - Quantity of the item in stock (integer type)

### Customers Table
- customerID : INT
  - Unique identifier for the customer (integer type)
- firstName : VARCHAR(50)
  - Customer's first name (string type, maximum 50 characters)
- lastName : VARCHAR(50)
  - Customer's last name (string type, maximum 50 characters)
- address : VARCHAR(255)
  - Customer's address (string type, maximum 255 characters)
- email : VARCHAR(100)
  - Customer's email address (string type, maximum 100 characters)
- password : VARCHAR(100)
  - Customer's password (string type, maximum 100 characters)

### Orders Table
- orderID : INT
  - Unique identifier for the order (integer type)
- date : DATE
  - Date of the order (date type)
- quantity : INT
  - Quantity of the order (integer type)
- customerID : INT
  - Identifier for the customer (integer type)
- orderStatus : VARCHAR(50)
  - Status of the order (string type, maximum 50 characters)

### OrderItems Table
- orderItemID : INT
  - Unique identifier for the order item (integer type)
- orderID : INT
  - Identifier for the order (integer type)
- itemID : INT
  - Identifier for the item (integer type)
- quantity : INT
  - Quantity of the item in the order (integer type)
## Requirement
## Usage
### Setup DB
- Setting backend
  1. Launch a DB in AWS RDS
  2. Connect to the DB using /DBsetting/conncectDB.sh
  3. Set up the DB using /DBsetting/setupDB.sql
## Features
