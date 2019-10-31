package kaehnd;



//todo
/**
 * ASK JONES ABOUT QUIZ QUESTION 1!!!!! todo todo todo todo todo todo todo todo todo todo todo todo todo
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ListnerExample extends Application {

    public static int HEIGHT = 100;
    private static int WIDTH = 300;

    Label label;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("DO DRUGS!");

        button.setOnAction(e -> {
            if(label.getEffect() == null){
                label.setEffect(new BoxBlur());
            } else {
                label.setEffect(null);

            }
        });

        label = new Label("I will do drugs soon");
        label.setStyle("-fx-font-size: 20;");
        label.setStyle(label.getStyle() + "-fx-text-fill: blue;");
        VBox pane = new VBox(button, label);
        Scene scene = new Scene(label, WIDTH, HEIGHT);
        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.show();

    }

    public class Drugs {

    }

    private void handler1(ActionEvent actionEvent) {
        if(label.getEffect() == null){
                label.setEffect(new BoxBlur());
            } else {
                label.setEffect(null);
            }
    }

//    public class ButtonListner implements EventHandler<ActionEvent>{
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            if(label.getEffect() == null){
//                label.setEffect(new BoxBlur());
//            } else {
//                label.setEffect(null);
//            }
//        }
//    }
}
