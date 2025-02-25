# Amazon Automation Task 🚀  

This project automates **product searching, filtering, adding to cart, and checkout** on **Amazon Egypt** (`https://www.amazon.eg/`) using **Selenium-Java**.

---

## **📋 Project Overview**
This automation script performs the following steps:
1. **Open** [Amazon Egypt](https://www.amazon.eg/) and log in.
2. **Open** the **"All"** menu from the left sidebar.
3. **Click** on **"Video Games"** → **"All Video Games"**.
4. **Apply Filters**:
   - ✅ Free Shipping  
   - 🆕 Condition: **New**  
5. **Sort by** **Price: High to Low**.
6. **Add all products** below **15,000 EGP** to the cart.
   - If no products are below **15k EGP**, navigate to the next page and continue.
7. **Verify** all selected products are **in the cart**.
8. **Proceed to checkout**:
   - 📍 **Add an address**  
   - 💰 **Select Cash on Delivery**  
9. **Verify** the **total price** matches the expected amount (including shipping fees if applicable).

---

## **🛠️ Tech Stack**
- **Java** (Programming Language)
- **Selenium WebDriver** (UI Automation)
- **TestNG / JUnit** (Test Framework)
- **Maven** (Dependency Management)
- **Extent Reports / Allure Reports** (Test Reporting)
- **WebDriverManager** (Automatic Browser Driver Handling)

---

## **📂 Project Structure**
src
 ├── main
 │   ├── java
 │   │   ├──com.amazonautomation
 │   │   │   ├── pages          # Page Object Model (POM) for UI elements
 │   │   │   ├── utilities          # Helper classes (WebDriver setup, screenshot, logging)
 │   
 ├── test
 │   ├── java
 │   │   ├── Base          # BaseTest for setup and teardown
 │   │   ├── Testcases        # Running methods for testcases
 │
 ├── pom.xml                # Dependencies (Selenium, TestNG, RestAssured)
 ├── config.properties      # External config (URLs, credentials)
 ├── README.md              # Setup instructions for evaluators




---

## **📝 Setup & Installation**
### **1️⃣ Prerequisites**
- Install **Java 8+**
- Install **Maven**
- Install **IntelliJ IDEA / Eclipse**
- Install **Google Chrome / Firefox**
- Add **WebDriverManager** for browser automation

### **2️⃣ Clone the Repository**
```bash
git clone https://github.com/MahHakim98/amazon-task.git
cd amazon-task


### **3️⃣ Install Dependencies
mvn clean install


### **4️⃣ Run the Tests
### **🔹 Using TestNG
mvn test