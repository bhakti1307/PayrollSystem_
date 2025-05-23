# PayrollSystem_

<!-- my folder structure 
│── src/
│   │── data/
│   │   │── employees.txt
│   │── models/
│   │   │── Employee.java
│   │   │── Payroll.java
│   │── services/
│   │   │── FileHandler.java
│   │── MainMenu.java

// how to run this project
STEP 1 : cd src
STEP 2 : javac models/*.java services/*.java MainMenu.java
STEP 3 : java MainMenu.java -->
Employee Payroll System

📌 Project Description

The Employee Payroll System is a Java-based console application that manages employee details, calculates their monthly wage payments, and records deductions for tax and national insurance. It also generates pay slips for individual employees, including gross pay, tax deductions, and net pay.

🚀 Features

✅ Add employee details (ID, Name, Salary, Tax Rate, NI Rate)✅ Calculate monthly wage payments, tax deductions, and national insurance✅ Record and track monthly payments✅ Generate and display pay slips for employees✅ Save employee records persistently using a file

🛠️ Installation & Setup

1️⃣ Prerequisites

Ensure you have the following installed:

Java Development Kit (JDK) 11 or later

Any Java IDE (Eclipse, IntelliJ IDEA, VS Code) or run via terminal

2️⃣ Clone the Repository

    git clone https://github.com/your-repo-url.git
    cd src

3️⃣ Compile the Code

    javac -d bin models/*.java services/*.java MainMenu.java

4️⃣ Run the Application

    java MainMenu.java

📖 How to Use

1️⃣ Add an Employee

Enter employee details (ID, Name, Salary, Tax Rate, NI Rate).

2️⃣ Display Pay Slip

Enter the employee's ID to view their gross pay, tax deductions, net pay, and payment history.

3️⃣ Record Monthly Payment

Select an employee by ID and record their monthly wage payment.

4️⃣ Save and Exit

Saves all employee records to a file and exits the program.
