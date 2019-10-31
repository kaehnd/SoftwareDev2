package kaehnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordHandler implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField infoField;
    @FXML
    private Button okButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDefaultButton(true);
    }

    @FXML
    private void cancelHandler(){
        usernameField.setText("");
        passwordField.setText("");
        infoField.setText("");
    }

    @FXML
    private void okHandler(){
        if(!(usernameField.getText().isEmpty() || passwordField.getText().isEmpty())){
            infoField.setText("Hello, " + usernameField.getText() + "\nYour password is " + passwordField.getText());
        }
    }


}
