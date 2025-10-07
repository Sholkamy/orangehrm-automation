# 🚀 OrangeHRM Web Automation Framework

An automated testing framework for the **OrangeHRM** web application built using  
**Selenium WebDriver**, **Cucumber (BDD)**, and **Maven** 🧩.  
The project follows the **Page Object Model (POM)** design pattern to ensure clean,  
maintainable, and scalable test architecture.

---

## ✨ Key Features
- 🔑 Login automation with valid credentials  
- ⚙️ Admin module interaction (add, search, delete users)  
- 📊 Dynamic record count verification (before & after operations)  
- 🧱 Page Object Model for reusability and clarity  
- ✅ Assertions handled in the test layer following clean coding principles  

---

## 🧠 Tech Stack
| Tool | Purpose |
|------|----------|
| 🧩 Selenium WebDriver | Browser automation |
| 🌿 Cucumber | BDD test framework |
| ☕ Java | Programming language |
| 🔧 Maven | Build & dependency management |
| 🧱 JUnit / TestNG | Test runner |
| 🧰 IntelliJ IDEA | Development environment |

---

## 🧭 Project Flow
```mermaid
graph TD
A[Login Page] --> B[Dashboard]
B --> C[Admin Module]
C --> D[Add New User]
D --> E[Verify Record Increased]
E --> F[Search for New User]
F --> G[Delete User]
G --> H[Verify Record Decreased]
