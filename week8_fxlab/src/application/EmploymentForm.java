package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class EmploymentForm extends GridPane {

    public EmploymentForm() {
        setPadding(new Insets(20, 20, 20, 20));
        setVgap(10);
        setHgap(10);
        setStyle("-fx-border-color: red");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label positionLabel = new Label("Position Applied For:");
        TextField positionField = new TextField();

        Button sendButton = new Button("Send Application");

        add(nameLabel, 0, 0);
        add(nameField, 1, 0);
        add(emailLabel, 0, 1);
        add(emailField, 1, 1);
        add(positionLabel, 0, 2);
        add(positionField, 1, 2);
        add(sendButton, 1, 3);

        sendButton.setOnAction(e -> System.out.println("Application Sent"));
    }
}
