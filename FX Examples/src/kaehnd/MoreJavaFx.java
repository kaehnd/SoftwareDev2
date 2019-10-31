package kaehnd;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MoreJavaFx extends Application {

    private Button ok;
    private Label label;
    private TextField firstName;
    private TextField lastName;


    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage){
        stage.setTitle("More Java FX");

        createContents();

        VBox pane = new VBox();

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        HBox innerPane = new HBox(firstName, lastName);
        innerPane.setSpacing(10.0);
        innerPane.setPadding(new Insets(10, 10,10,10));
        pane.getChildren().addAll(innerPane, label, ok);
        ok.setAlignment(Pos.CENTER);

        stage.show();

    }

    private void createContents(){
        ok = new Button("OK");
        ok.setOnAction(this::handle);
        label = new Label();


        firstName = new TextField();
        lastName = new TextField();
        lastName.setOnAction(this::handle);


    }

    private void handle(ActionEvent event){
        if(!(firstName.getText().isEmpty() || lastName.getText().isEmpty())){
            label.setText("Hello, " + firstName.getText() + " " + lastName.getText() + "!");
        }

    }
}
