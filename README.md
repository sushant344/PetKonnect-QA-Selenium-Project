# PetKonnect Selenium Automation Project
## Introduction
This project is an automated testing suite for [PetKonnect eCommerce application](https://www.petkonnect.in/) using Selenium WebDriver. The goal is to ensure the system testing of the application such as regression testing, sanity testing, functional testing through a series of automated tests. 

<img src="https://mma.prnewswire.com/media/1842248/PetKonnect_Logo.jpg?p=twitter" height="150" width="400" title="Petkonnect webpage">

## Table of Contents
- [PetKonnect Selenium Automation Project](#petkonnect-selenium-automation-project)
    - [Table of Contents](#table-of-contents)
        - [Introduction](#introduction)
        - [Project structure](#project-structure)
        - [Setup and Installation](#setup-and-installation)


## Project Structure
```bash
petkonnect-qa-selenium-project/
│
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │       └── AutomationUtils/
│       │           ├── Readfileutils.java/        # to read and write data from excel file
│       │       └── ExecutionTestCases/
│       │           ├── PetKonnect.java/           # main consolidated java to run all test cases
│       │       └── Pages/
│       │           ├── Addtocartpage.java/        # page contain methods to test add to cart
│       │           ├── Homepage.java/             # page contain methods to test homepage
│       │           ├── Loginpage.java/            # page contain methods to test login
│       │           ├── Productpage.java/          # page contain methods to test product
│       │           ├── SignUppage.java/           # page contain methods to test sign up
│       └── resources/
├── Test cases/
│   ├── PetKonnect.xlsx/
├── pom.xml                           # Maven POM File
├── README.md                         # Project Documentation
└── .gitignore                        # Git Ignore File

```

## Setup and Installation
### Prerequisites
- [Java JDK 11 or higher](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)
- [Selenium 4.20 or higher](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
- Maven 3.6 or higher
- [TestNG 7.10 or higher](https://mvnrepository.com/artifact/org.testng/testng)
- [Apache poi 5.2 or higher](https://mvnrepository.com/artifact/org.apache.poi/poi)
- Google Chrome

### Steps to setup the project
1. In your system create new project as a maven project.
2. Add all necessary dependencies from maven repository or you can copy from [pom.xml](./pom.xml) file.
