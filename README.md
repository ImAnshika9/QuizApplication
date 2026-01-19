# Quiz Application (Java Swing)

A desktop-based quiz assessment system built using Java Swing and AWT.  
This application allows candidates to enter their details, read instructions, take an MCQ quiz with a timer, and view detailed results.

---

## 🚀 Features

- Candidate Details Form (Name, Email, Roll)
- Instructions Page with UX design
- MCQ-based Quiz (10 Questions)
- Timer per question (15 seconds)
- Skip Question feature
- 50-50 Lifeline (usable once)
- Score and Percentage Calculation
- PASS / FAIL Evaluation
- Result Summary Page with:
  - User Details
  - Score
  - Percentage
  - Status (PASS / FAIL)
  - Retake Test Button
  - Save Result Report (TXT)
  - Close App Button
- Clean and Interactive UI with Swing

---

## 🧩 Tech Stack

- **Language:** Java
- **UI:** Java Swing, AWT
- **IDE:** Eclipse (recommended, but any Java IDE works)

---

## 📂 Project Structure

QuizApplication/
├─ src/
│ ├─ HomePage.java
│ ├─ InstructionsPage.java
│ ├─ QuizApplication.java
│ ├─ ResultPage.java
│ └─ UserData.java
├─ .project
├─ .classpath
└─ README.md


---

## 🖥️ Demo Flow

1. **Home Page:** User enters name, email, and roll number
2. **Instructions Page:** Displays quiz rules
3. **Quiz Page:** User answers timed MCQs
4. **Result Page:** Displays score, percentage & status
5. **Report Saving:** Saves results in `result_report.txt`

---

## 📦 How to Run

### **Method 1: Using IDE (Eclipse / IntelliJ)**

1. Clone or download the repository
2. Open in your IDE
3. Locate `HomePage.java`
4. Run the file → GUI will launch

### **Method 2: Using Terminal**
```
java HomePage
```

## 📁 Result Report Example

`result_report.txt` file contains:

```
===== TEST RESULT REPORT =====
Name: John Doe
Email: john@example.com
Roll: CS101
Score: 7/10
Percentage: 70%
Status: PASS
Date: 2024-01-19T12:34:56
--------------------------------------
```
---

## 🎯 Future Enhancements (If you want to extend)

- Load questions from JSON / Database
- Store results in MySQL
- Admin panel to view performance
- PDF result generation
- User login system
- Dark mode UI
---

## License
This project is licensed under the MIT License.

## Author
Anshika Tiwari
