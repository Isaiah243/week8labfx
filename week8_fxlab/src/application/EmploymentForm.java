package application;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmploymentForm extends GridPane {

    private final String URL = "jdbc:mysql://localhost:3306/week9_LabWork_isaiah";
    private final String USER = "root";
    private final String PASSWORD = "password123";
    
    private TextField nameField;
    private TextField emailField;
    private TextField positionField;
    private TextArea outputArea;

    public EmploymentForm() {
   
        setPadding(new Insets(20));
        setVgap(10);
        setHgap(10);
        setStyle("-fx-border-color: red");

        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label positionLabel = new Label("Position Applied For:");
        positionField = new TextField();

        Button submitButton = new Button("Submit");
        Button readButton = new Button("Read");

        outputArea = new TextArea();
        outputArea.setPrefHeight(150);

        add(nameLabel, 0, 0);
        add(nameField, 1, 0);
        add(emailLabel, 0, 1);
        add(emailField, 1, 1);
        add(positionLabel, 0, 2);
        add(positionField, 1, 2);
        add(submitButton, 0, 3);
        add(readButton, 1, 3);
        add(outputArea, 0, 4, 2, 1);

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    insertRecord();
                } catch (Exception ex) {
                    outputArea.setText("Error inserting record: " + ex.getMessage());
                }
            }
        });

        readButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    readRecords();
                } catch (Exception ex) {
                    outputArea.setText("Error reading records: " + ex.getMessage());
                }
            }
        });
    }

    private void insertRecord() {
        String name = nameField.getText();
        String email = emailField.getText();
        String position = positionField.getText();

        if (name.isEmpty()) {
            outputArea.setText("Please enter a name.");
            return;
        }

        if (email.isEmpty()) {
            outputArea.setText("Please enter an email.");
            return;
        }

        if (position.isEmpty()) {
            outputArea.setText("Please enter the position you are applying for.");
            return;
        }

        String sql = "INSERT INTO EmploymentForm (name, email, position) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, position);

            stmt.executeUpdate();

            outputArea.setText("Record inserted successfully!");

            nameField.clear();
            emailField.clear();
            positionField.clear();

        } catch (SQLException ex) {
            outputArea.setText("Database error while inserting: " + ex.getMessage());
        } catch (Exception ex) {
            outputArea.setText("Unexpected error: " + ex.getMessage());
        }
    }

    private void readRecords() {
        String sql = "SELECT * FROM EmploymentForm";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("Name: ").append(rs.getString("name"))
                  .append(", Email: ").append(rs.getString("email"))
                  .append(", Position: ").append(rs.getString("position"))
                  .append("\n");
            }

            if (sb.length() == 0) {
                outputArea.setText("No records found.");
            } else {
                outputArea.setText(sb.toString());
            }

        } catch (SQLException ex) {
            outputArea.setText("Database error while reading: " + ex.getMessage());
        } catch (Exception ex) {
            outputArea.setText("Unexpected error: " + ex.getMessage());
        }
    }
}
