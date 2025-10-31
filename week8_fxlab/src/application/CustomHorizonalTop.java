package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomHorizonalTop extends VBox {

    public CustomHorizonalTop(String title) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Text label = new Text("My Favorite Sport:");

        ImageView imageView = new ImageView(new Image("file:resources/sport.jpg"));
        imageView.setFitWidth(120);
        imageView.setFitHeight(80);

        hbox.getChildren().addAll(label, imageView);
        getChildren().add(hbox);
        setStyle("-fx-border-color: red");
    }
}
