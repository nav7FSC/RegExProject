package org.education.regexproject;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class represents a registration form application built using JavaFX.
 * It allows users to input their first name, last name, email, date of birth,
 * and zip code with validation for each field. The user can submit their data
 * if all validations pass.
 */
public class RegistrationForm extends Application {

    @FXML
    private TextField firstNameField, lastNameField, emailField, dobField, zipCodeField;
    @FXML
    private Label firstNameError, lastNameError, emailError, dobError, zipError;
    @FXML
    private Button addButton;

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage the primary stage for this application
     * @throws Exception if any error occurs during startup
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create title label
        Label titleLabel = new Label("Registration Page");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white; -fx-padding: 10px;");

        // Create text fields
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        dobField = new TextField();
        dobField.setPromptText("Date of Birth (MM/DD/YYYY)");

        zipCodeField = new TextField();
        zipCodeField.setPromptText("Zip Code");

        // Create error labels
        firstNameError = new Label();
        lastNameError = new Label();
        emailError = new Label();
        dobError = new Label();
        zipError = new Label();

        // Create add button
        addButton = new Button("Add");
        addButton.setDisable(true);
        // Set button style
        addButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-padding: 10px 20px;"
        );

        // Add action handler
        addButton.setOnAction(event -> handleAddButtonClick());

        // Create layout
        VBox vbox = new VBox(10,
                titleLabel,
                firstNameField, firstNameError,
                lastNameField, lastNameError,
                emailField, emailError,
                dobField, dobError,
                zipCodeField, zipError,
                addButton
        );

        // Centering the elements and adding padding
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-alignment: center;");

        // Setting up scene with increased dimensions
        Scene scene = new Scene(vbox, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration Form");
        primaryStage.show();

        // Add focus and text change listeners for validation
        addListeners();
    }

    /**
     * Adds listeners to the input fields to validate user input
     * when the fields lose focus or when the zip code changes.
     */
    private void addListeners() {
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> validateFirstName());
        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> validateLastName());
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> validateEmail());
        dobField.focusedProperty().addListener((observable, oldValue, newValue) -> validateDOB());
        zipCodeField.focusedProperty().addListener((observable, oldValue, newValue) -> validateZipCode());

        // Add a listener for text changes in zip code field
        zipCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateZipCode(); // Validate zip code
            enableAddButton();  // Enable the add button based on current validations
        });
    }

    /**
     * Validates the first name input for the registration form.
     * The name must be alphabetic and between 2 and 25 characters long.
     */
    private void validateFirstName() {
        String input = firstNameField.getText();
        if (input.matches("^[A-Za-z]{2,25}$")) {
            firstNameError.setText("");
        } else {
            firstNameError.setText("Invalid First Name");
        }
        enableAddButton();
    }

    /**
     * Validates the last name input for the registration form.
     * The name must be alphabetic and between 2 and 25 characters long.
     */
    private void validateLastName() {
        String input = lastNameField.getText();
        if (input.matches("^[A-Za-z]{2,25}$")) {
            lastNameError.setText("");
        } else {
            lastNameError.setText("Invalid Last Name");
        }
        enableAddButton();
    }

    /**
     * Validates the email input for the registration form.
     * The email must match the specified domain (farmingdale.edu).
     */
    private void validateEmail() {
        String input = emailField.getText();
        if (input.matches("^[a-zA-Z0-9._%+-]+@farmingdale.edu$")) {
            emailError.setText("");
        } else {
            emailError.setText("Invalid Email");
        }
        enableAddButton();
    }

    /**
     * Validates the date of birth input for the registration form.
     * The date must follow the MM/DD/YYYY format and be a valid date.
     */
    private void validateDOB() {
        String input = dobField.getText();
        if (input.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$")) {
            dobError.setText("");
        } else {
            dobError.setText("Invalid Date of Birth");
        }
        enableAddButton();
    }

    /**
     * Validates the zip code input for the registration form.
     * The zip code must consist of exactly 5 digits.
     */
    private void validateZipCode() {
        String input = zipCodeField.getText();
        if (input.matches("^\\d{5}$")) {
            zipError.setText("");
        } else {
            zipError.setText("Invalid Zip Code");
        }
    }

    /**
     * Enables or disables the add button based on the validity of the input fields.
     * The button is enabled only if all error messages are empty.
     */
    private void enableAddButton() {
        addButton.setDisable(
                !firstNameError.getText().isEmpty() ||
                        !lastNameError.getText().isEmpty() ||
                        !emailError.getText().isEmpty() ||
                        !dobError.getText().isEmpty() ||
                        !zipError.getText().isEmpty() ||
                        zipCodeField.getText().length() != 5 // Ensure zip code has 5 digits
        );
    }

    /**
     * Handles the action when the add button is clicked.
     * Displays an alert if registration is successful.
     */
    @FXML
    private void handleAddButtonClick() {
        // Check if the button is enabled before proceeding
        if (!addButton.isDisabled()) {
            // Create an alert for successful registration
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully registered!");

            // Show the alert and wait for the user to close it
            alert.showAndWait();
        } else {
            System.out.println("Please fix the errors before submitting.");
        }
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}