import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI  extends Application{

    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) {
        WavAttempts wav = new WavAttempts();
        Pane pane = new Pane();
        Button button = new Button("Play");
        button.setOnAction(e -> wav.play());
        pane.getChildren().add(button);
        stage.setScene(new Scene(pane, 500, 500));
        stage.show();
    }
}

