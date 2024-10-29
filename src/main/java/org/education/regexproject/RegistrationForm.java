package org.education.regexproject;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistrationForm extends Application {

    @FXML
    private TextField firstNameField, lastNameField, emailField, dobField, zipCodeField;
    @FXML
    private Label firstNameError, lastNameError, emailError, dobError, zipError;
    @FXML
    private Button addButton;

    @Override
    public void start(Stage primaryStage) {
        // Create title label
        Label titleLabel = new Label("Registration Page");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #333;");

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
        // Set button style here
        addButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-background-radius: 20px; " +
                        "-fx-padding: 10px 20px;"
        );
        addButton.setOnAction(event -> handleAddButtonClick());

        // Create layout
        VBox vbox = new VBox(10,
                titleLabel, // Add title label to the layout
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
        Scene scene = new Scene(vbox, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration Form");
        primaryStage.show();

        // Add focus listeners for validation
        addListeners();
    }

    private void addListeners() {
        firstNameField.focusedProperty().addListener((observable, oldValue, newValue) -> validateFirstName());
        lastNameField.focusedProperty().addListener((observable, oldValue, newValue) -> validateLastName());
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> validateEmail());
        dobField.focusedProperty().addListener((observable, oldValue, newValue) -> validateDOB());
        zipCodeField.focusedProperty().addListener((observable, oldValue, newValue) -> validateZipCode());
    }

    private void validateFirstName() {
        String input = firstNameField.getText();
        if (input.matches("^[A-Za-z]{2,25}$")) {
            firstNameError.setText("");
        } else {
            firstNameError.setText("Invalid First Name");
        }
        enableAddButton();
    }

    private void validateLastName() {
        String input = lastNameField.getText();
        if (input.matches("^[A-Za-z]{2,25}$")) {
            lastNameError.setText("");
        } else {
            lastNameError.setText("Invalid Last Name");
        }
        enableAddButton();
    }

    private void validateEmail() {
        String input = emailField.getText();
        if (input.matches("^[a-zA-Z0-9._%+-]+@farmingdale.edu$")) {
            emailError.setText("");
        } else {
            emailError.setText("Invalid Email");
        }
        enableAddButton();
    }

    private void validateDOB() {
        String input = dobField.getText();
        if (input.matches("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(19|20)\\d\\d$")) {
            dobError.setText("");
        } else {
            dobError.setText("Invalid Date of Birth");
        }
        enableAddButton();
    }

    private void validateZipCode() {
        String input = zipCodeField.getText();
        if (input.matches("^\\d{5}$")) {
            zipError.setText("");
        } else {
            zipError.setText("Invalid Zip Code");
        }
        enableAddButton();
    }

    private void enableAddButton() {
        addButton.setDisable(
                !firstNameError.getText().isEmpty() ||
                        !lastNameError.getText().isEmpty() ||
                        !emailError.getText().isEmpty() ||
                        !dobError.getText().isEmpty() ||
                        !zipError.getText().isEmpty()
        );
    }

    @FXML
    private void handleAddButtonClick() {
        System.out.println("Registration Successful!");
        // Implement navigation to a new UI here if needed
    }

    public static void main(String[] args) {
        launch(args);
    }
}