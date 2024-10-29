# Registration Form Application

This JavaFX project is a Registration Form Application that allows users to input their first name, last name, email, date of birth, and zip code. Each input field includes validation to ensure data accuracy, and users receive immediate feedback if any errors are detected. Once all validations pass, users can submit their data successfully.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [Project Structure](#project-structure)
4. [Author](#author)

## Project Overview

This application is a simple but interactive JavaFX-based form, primarily aimed at demonstrating user input validation and UI layout design in Java. It is structured around a `RegistrationForm` class that handles the UI, input fields, validations, and user interactions.

## Features

- **Text Fields** for First Name, Last Name, Email, Date of Birth, and Zip Code.
- **Validation** on each input field:
    - **First Name and Last Name:** Alphabetic and 2-25 characters long.
    - **Email:** Must be in the format `username@farmingdale.edu`.
    - **Date of Birth:** Follows `MM/DD/YYYY` format.
    - **Zip Code:** Exactly 5 digits.
- **Error Messages** that appear below each field if invalid data is entered.
- **Add Button** that enables only when all fields pass validation.
- **Alert Notification** upon successful registration.

## Project Structure

    registration-form-app/
    ├── src/
    │   └── main/
    │       └── java/
    │           └── org/education/regexproject/
    │               └── RegistrationForm.java  # Main class for the JavaFX application
    ├── README.md                             # Project README file
    └── pom.xml                               # Maven configuration file

## Author

- Nav Singh [GitHub](https://github.com/nav7FSC)

