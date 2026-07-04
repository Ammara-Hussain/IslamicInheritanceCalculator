# 🕌 Islamic Inheritance (Faraid) Calculation System
A high-precision automated application engineered to compute legal shares of inheritance based on Islamic jurisprudence (Sharia law). This software implements a modular architecture using **JavaFX** for an intuitive graphical interface, alongside strict structural design patterns to decouple computational logic from user interactions.

<img width="895" height="520" alt="image" src="https://github.com/user-attachments/assets/d6229b82-5c8f-43a1-9b5c-d48f33f26d87" />
<img width="899" height="499" alt="image" src="https://github.com/user-attachments/assets/813da660-06e2-4cd9-a610-30718be88527" />
<img width="902" height="482" alt="image" src="https://github.com/user-attachments/assets/d97590d3-da3b-40b5-845a-6cde3fad34c2" />
<img width="901" height="518" alt="image" src="https://github.com/user-attachments/assets/df977b4a-5495-4691-945e-ce0b7b911a9f" />
<img width="902" height="340" alt="image" src="https://github.com/user-attachments/assets/f4baadea-cfca-41c5-b7ba-b79f27de5105" />
<img width="898" height="483" alt="image" src="https://github.com/user-attachments/assets/8032a4bb-1950-495b-be9e-7bbf3e2471f3" />
<img width="896" height="487" alt="image" src="https://github.com/user-attachments/assets/7563a378-16ec-418b-a627-cffbbe2be456" />
<img width="898" height="225" alt="image" src="https://github.com/user-attachments/assets/76ce4a3c-a0ea-4ede-8dbc-6258ac4dcd43" />

## Project Overview

The **Automated Islamic Inheritance Calculation System** is a desktop application developed as a **Software Design & Architecture** semester project.

The application simplifies the complex process of Islamic inheritance calculation by automatically determining:

- Eligible heirs
- Fixed Quranic shares
- Residual shares
- Excluded heirs
- Net distributable estate after deductions

The system follows inheritance rules derived from:

- **Surah An-Nisa (Ayat 11)**
- **Surah An-Nisa (Ayat 12)**
- Classical Islamic inheritance principles

The objective of the project is to minimize manual calculation errors while providing transparency through Quranic references associated with each inheritance share.

# Project Demonstration

A complete walkthrough of the application is available on LinkedIn.

The demonstration explains:

- Estate calculation
- Deduction of debts
- Funeral expenses
- Bequest handling
- Eligible heir determination
- Share calculation
- Exclusion rules
- Quranic references

---

# Features

### Estate Management

- Enter total assets
- Outstanding debts
- Funeral expenses
- Bequest (Wasiyyah)

Automatically computes:

> Net Estate Available for Distribution

---

### Heir Management

Supports adding multiple heirs including:

- Father
- Mother
- Husband
- Wife
- Son
- Daughter
- Grandparents
- Grandchildren
- Full Brother
- Full Sister
  
---

### Automatic Inheritance Calculation

The system automatically:

- Determines eligible heirs
- Applies Quranic fixed shares
- Calculates residuary shares
- Handles exclusion (Hajb)
- Prevents invalid inheritance combinations

---

### Quranic References

Each calculated share is linked with its corresponding Quranic reference, allowing users to understand the legal basis of the distribution.

---

### User-Friendly JavaFX Interface

- Modern GUI
- Responsive forms
- Simple navigation
- Dynamic heir selection
- Instant calculations

---

# Software Architecture

The project follows modular software design principles.

```
src
│
├── handler
├── logic
├── model
├── strategy
├── ui
└── resources
```

### Major Modules

### Model

Contains all domain entities.

Examples:

- Person
- Estate
- Heir
- Share
- Inheritance Rules

---

### Logic

Implements the inheritance calculation engine.

Responsible for:

- Estate calculation
- Rule evaluation
- Share distribution
- Validation

---

### Strategy

Implements calculation strategies using Object-Oriented Design Principles.

This improves:

- Maintainability
- Scalability
- Code reusability

---

### UI

JavaFX graphical interface developed using:

- FXML
- CSS
- JavaFX Controls

---

### Handler

Responsible for communication between:

- User Interface
- Business Logic

---

# Technologies Used

- Java
- JavaFX
- FXML
- CSS
- Maven
- Object-Oriented Programming
- Software Design & Architecture

---

# Object-Oriented Concepts Applied

- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Interfaces

---

# Design Principles

The project emphasizes:

- Separation of Concerns
- Modular Design
- Layered Architecture
- Strategy Pattern
- Clean Code Practices

---

# Installation

## Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/IslamicInheritanceCalculator.git
```

Open using IntelliJ IDEA.

Run

```
Main.java
```

---

# Requirements

- Java 17+
- JavaFX SDK
- Maven
- IntelliJ IDEA (Recommended)

---

# Future Enhancements

- Database integration
- User authentication
- PDF inheritance reports
- Arabic language support
- Multi-school (Madhab) calculation support
- Cloud synchronization
- Web-based version

---

# Educational Purpose

This project was developed for academic and educational purposes as part of the **Software Design & Architecture** course.

It demonstrates the practical application of software engineering principles to solve a real-world problem through automation.

---

# Authors

**Ammara Hussain**

Software Engineering Student

University of Engineering and Technology (UET) Taxila

---

# Acknowledgements

Special thanks to our course instructor and the Department of Software Engineering, UET Taxila, for their guidance throughout the development of this project.

---

# Collaborator

Muhammad Usman 

---

# License

This project is intended for educational and research purposes.
---
