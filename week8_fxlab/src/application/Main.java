package application;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();

       
        pane.setTop(new CustomHorizonalTop("Top"));

        HBox bottomBox = new HBox();
        bottomBox.setStyle("-fx-border-color: red");
        bottomBox.setSpacing(10);
        bottomBox.getChildren().add(new Label("Today's Date: " + LocalDate.now()));
        pane.setBottom(bottomBox);

        pane.setLeft(new CustomPane("Left"));
        pane.setRight(new CustomPane("Right"));

        pane.setCenter(new EmploymentForm());

        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("FX Project Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
