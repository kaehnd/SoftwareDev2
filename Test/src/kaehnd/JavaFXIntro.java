package kaehnd;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXIntro extends Application {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 100;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Yoh, doing drugs is lit");
        Button button = new Button("Push Me!!");
        Pane pane = new Pane();

        Scene scene = new Scene(label, WIDTH, HEIGHT);


        label.setAlignment(Pos.TOP_CENTER);
        label.setFont(new Font(16));
        button.setAlignment(Pos.CENTER);
        stage.setTitle("Drugssss");
        stage.setScene(scene);
        stage.show();

    }
}