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
        - [Test Documentations](#test-documentations)
            - [Test cases](#test-cases-file)
        - [Test Suite Structure](#test-suite-structure)
            - [Data driven testing utils](#data-driven-utils)
            - [PetKonnect Execution File and Pages](#petkonnect-execution-pages)
        - [License](#license)


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

### Steps to Setup the Project
1. In our system create new project as a maven project.
2. Add all necessary dependencies from maven repository or you can copy from [pom.xml](./pom.xml) file.
3. Install TestNG from marketplace (if eclipse IDE then go to help > Eclipse marketplace).
4. Whenever add or update anything in pom.xml need to do update our project (in eclipse IDE right click on project > maven > update project > force update > ok).
5. Then you create and run class in src > test > java. To run project right click in java file and run as a TestNG test.
6. If not able to see TestNG test then right click on project > properties > java build path > maven dependencies > select TestNG > Apply close.

## Test Documentations
### Test Cases File
The test cases for this project are documented in an Excel file located in the [Test cases](./Test%20cases/) directory. The file is named [PetKonnect.xlsx](https://docs.google.com/spreadsheets/d/1wReWlif0YtDsXZrp8YtyjB5bcknm1MUj0-0p_vtL2BY/edit?gid=1113167546#gid=1113167546). In this excel file executing test results and by apache poi writing a actual result.

Note : As of now executing test results in local excel file but I will make execution in google sheet document.

Test Cases Format
Each test case is described with the following fields:

- **Test Scenario ID:** Unique identifier for the test scenario.
- **Test Scenarios:** Each page test functionalities.
- **Test Case ID:** Unique identifier for the test case.
- **Test Case Description:** Detailed description of the test case.
- **Test Steps:** Step-by-step instructions to execute the test case.
- **Test Input Data:** Input data for required webpage modules.
- **Expected Result:** The expected outcome of the test case.
- **Actual Result:** The actual outcome after executing the test case (to be filled during execution).
- **Result:** Pass/Fail status of the test case (to be filled during execution).

## Test Suite Structure
### Data Driven Utils
Data-driven testing is a methodology in which test data is stored outside of the test scripts, often in an external file like an Excel spreadsheet, database, or CSV file. In this project, we use an Excel file [PetKonnect.xlsx](./Test%20cases) to store the test data with help of [ReadFileUtils.java](./src/test/java/AutomationUtils/ReadFileUtils.java) file for reading & writing test results. This allows us to separate the test logic from the test data and makes it easier to manage and update test cases.
#### Implementation Details
To implement data-driven testing in this project, we use Apache POI, a Java library for reading and writing Microsoft Office documents. The steps involved include:

1. **Reading Data from Excel:** Before the test execution, the test data is read from the PetKonnect.xlsx file. This involves opening the Excel file, navigating to the correct sheet, and reading the values of the cells.

2. **Executing Test Steps:** The test data is used to perform the test steps, which can include actions like clicking buttons, entering text in fields, and verifying results on the web page.

3. **Writing Results to Excel:** After the test execution, the actual results and the status (Pass/Fail) are written back to the Excel file. This involves opening the Excel file in write mode and updating the relevant cells with the results.

### PetKonnect Execution Pages
The project contains various [Java files](./src/test/java/Pages), each consisting of methods to perform specific test cases. This modular approach allows for better organization, reusability, and maintainability of the test scripts.
- Created instance in [PetKonnect.java](./src/test/java/ExecutionTestCases/) file to invoke java files all methods.
- These file contains all test cases execution methods, by simply running this file we can perform all test cases in one shot.
- To implement test cases, we created various test methods on various webpage modules. As mention below:
### Homepage
[Homepage](./src/test/java/Pages/Homepage.java) class file which consist step by step testing methods flow includes:
1. Navigation of homepage link by validating page title.
2. Validation of visibility of logo and navigation of logo.
3. All images and anchor links present on homepage.
4. Search products by entering valid and invalid search keywords.
### Sign up
[SignUp](./src/test/java/Pages/SignUpPage.java) class file which consist step by step testing methods flow includes:
1. Navigation of sign up page link by validating page title.
2. Validating creation of account by entering valid and invalid values in inputs.
### Log in
[Login](./src/test/java/Pages/LoginPage.java) class file which consist step by step testing methods flow includes:
1. Navigation of login page link by validating page title.
2. Validating login the account by valid and invalid inputs credentials.
3. After successfully logged in to account able to logout.
### Products page
[Products page](./src/test/java/Pages/ProductsPage.java) class file which consist step by step testing methods flow includes:
1. Navigation of all products page link by validating page title.
2. Validation of display products by applying various filters and able to reset applied filters.
### Add to cart
[Products page](./src/test/java/Pages/AddtocartPage.java) class file which consist step by step testing methods flow includes:
1. Select specific product and able to add in cart.
2. Check flow of cart product to checkout upto the payment page.
3. Validation of able to remove product from cart.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.
