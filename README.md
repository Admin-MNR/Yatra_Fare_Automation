# ✈️ Yatra Fare Comparison Automation

This project automates the process of comparing the **lowest flight prices** for two consecutive months on [Yatra.com](https://www.yatra.com), using **Java** and **Selenium WebDriver**.

---

## 🚀 Features

- Opens the flight date picker and navigates through the calendar
- Extracts the **lowest available fare** (₹) from:
  - Current month
  - Next month
- Compares both fares and prints the **cheaper option**
- Handles dynamic popups and date loading
- Code is modular and easy to maintain

---

## 🛠 Tech Stack

- **Language:** Java  
- **Automation Tool:** Selenium WebDriver  
- **Browser Driver:** ChromeDriver  
- **Waits:** WebDriverWait for dynamic elements

---

## 📚 Learning Highlights

- DOM traversal using **relative XPath**
- WebElement **chaining** for nested elements
- Handling unexpected **pop-ups or modals**
- **Parsing dynamic text** (e.g., ₹ values, `aria-label` attributes)
- Printing structured output to console

---

## 🔧 How to Run

1. Install Chrome and ChromeDriver
2. Open the project in your Java IDE (Eclipse / IntelliJ)
3. Update the path to `chromedriver.exe` if needed
4. Run the main class file

---
