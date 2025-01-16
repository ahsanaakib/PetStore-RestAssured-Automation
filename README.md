# PetStore-RestAssured-Automation

This project contains automated test cases for the [Swagger Petstore API](https://petstore.swagger.io/#/). It includes modules for testing `User` and `Store` functionalities using TestNG and RestAssured.

---
## 🛠️ Framework Features

| Feature                          | Description                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| **User Module**                  | Test cases for user creation, retrieval, updating, and deletion.            |
| **Store Module**                 | Test cases for managing store orders (creating, retrieving, and deleting).  |
| **Data-Driven Testing**          | Supports data-driven tests using external files.                            |
| **Dynamic Data Generation**      | Leverages `Faker` library for generating random test data.                  |
| **Reporting**                    | Integrates with Extent Reports for detailed test execution reports.         |
| **Schema Validation**            | Validates API responses against predefined JSON schemas.                    |

---

## ⚙️ Prerequisites

| Software/Tool       | Version                | Description                                      |
|----------------------|------------------------|--------------------------------------------------|
| **Java**            | 8 or higher           | Programming language for writing tests.          |
| **Maven**           | Latest version        | Dependency management and build tool.            |
| **TestNG**          | Latest version        | Test framework for organizing and executing tests. |
| **RestAssured**     | Latest version        | API testing library for HTTP operations.         |
| **Extent Reports**  | Latest version        | Reporting library for test results.              |
| **Faker**           | Latest version        | Library for generating random test data.         |

---

## 🔄 How to Run the Tests 

### 1️⃣Running Tests Using `run.bat` (Windows Users)  

For convenience, use the provided **`run.bat`** script to run tests:  

1. **Double-click** the `run.bat` file in your project root directory to execute it.  

   **OR**  

2. Open **Command Prompt** or **Git Bash**:  

   1. Navigate to your project root directory:  
      ```bash
      cd path\to\your\project
      ```  
      *(In Git Bash, use `cd /c/path/to/your/project`.)*  

   2. Run the script by typing:  
      ```bash
      run.bat
      ```  

This script automates the Maven test execution. Ensure the following prerequisites:  

1. **Maven** and **Java** are installed and available in your system’s **PATH**.  
2. You run the script from the **project root directory**.  

---

### 2️⃣Running Tests from an IDE (e.g., Eclipse or IntelliJ IDEA)  

1. **Open your project in your preferred IDE** (e.g., Eclipse, IntelliJ IDEA).  
2. Navigate to the `testng.xml` file in your project explorer.  
3. Right-click on the XML file and select:  
   - **Run As** -> **TestNG Suite** (Eclipse)  
   - **Run 'TestNG Suite'** (IntelliJ IDEA)  
4. The IDE will execute the tests defined in the selected XML file.  

---

## 🛠️ Jenkins Integration  

This project supports Jenkins for Continuous Integration and Continuous Delivery (CI/CD). By configuring Jenkins, you can automate the triggering of test executions every time changes are pushed to the repository.  

### Steps to Integrate Jenkins:  

1. **Install Maven** on your Jenkins server and configure it in the system settings.  
2. Create a new Jenkins job:  
   - Set it to a **Maven project**.  
   - In the **Build** section, provide the Maven goal:  
     ```bash
     clean test
     ```  
3. **Link the Repository**:  
   - Add the GitHub repository URL in the job configuration.  
   - Provide credentials if required.  
4. **Post-Build Actions**:  
   - Archive the `ExtentReport.html` file located in the `test-output` directory for review.  
5. **Trigger Builds Automatically**:  
   - Use a webhook or a schedule to trigger builds whenever changes are committed.  

This setup ensures consistent test execution, maintaining code quality and stability throughout the development lifecycle.  
