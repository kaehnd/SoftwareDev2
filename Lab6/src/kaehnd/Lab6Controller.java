/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab6: Web Tester
 * Name: Daniel Kaehn
 * Created: 1/17/2019
 */

package kaehnd;

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controls interaction between Lab6.fxml, GUI, and source code
 */
public class Lab6Controller implements Initializable {

    private WebsiteTester tester;

    @FXML
    private TextField urlField;

    @FXML
    private TextField timeOutField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField portField;

    @FXML
    private TextField downloadTimeField;

    @FXML
    private TextField hostField;

    @FXML
    private TextArea webpage;

    /**
     * To be run on application startup
     * @param url unused
     * @param resourceBundle unused
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tester = new WebsiteTester();
        timeOutField.setText(tester.getTimeout());
    }

    @FXML
    private void analyze(){
        try {
            //Analyze website
            tester.openURL(urlField.getText());
            tester.openConnection();
            tester.downloadText();
            sizeField.setText(String.valueOf(tester.getSize()));
            downloadTimeField.setText(String.valueOf(tester.getDownloadTime()));
            hostField.setText(tester.getHostname());
            portField.setText(String.valueOf(tester.getPort()));
            webpage.setText(tester.getContent());

            DataOutputStream stream = new DataOutputStream(new FileOutputStream(new File("j.bin")));
            stream.writeChars("DFRdaf");
            stream.write(1);
            stream.writeDouble(5.5);
            stream.writeBoolean(true);

            //Handle exceptions
        } catch (MalformedURLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The URL entered in the text box is invalid");
            a.setHeaderText("URL Error");
            a.setTitle("Error Dialog");
            a.showAndWait();

        } catch (SocketTimeoutException e){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Timeout Dialog");
            a.setHeaderText("Wait longer?");
            a.setContentText("There has been a timeout reaching the site. " +
                    "Click OK to extend the timeout period?");
            Optional<ButtonType> confirm = a.showAndWait();

            if(confirm.isPresent() && confirm.get() != ButtonType.CANCEL){
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Set timeout");
                dialog.setHeaderText("Set extended timeout");
                dialog.setContentText("Desired timeout:");
                Optional<String> ok = dialog.showAndWait();
                ok.ifPresent(this::handleResetTimeout);
            }

        } catch (UnknownHostException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error: Unable to reach the host " + urlField.getText());
            a.setHeaderText("Host Error");
            a.setTitle("Error Dialog");
            a.showAndWait();

        } catch (IOException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error: File not found on the server,\n" + urlField.getText());
            a.setHeaderText("File Error");
            a.setTitle("Error Dialog");
            a.showAndWait();
        }
    }

    private void handleResetTimeout(String s){
        tester.setTimeout(s);
        timeOutField.setText(s);
        analyze();
    }

    @FXML
    private void setTimeout(){
        try {
            tester.setTimeout(timeOutField.getText());
        } catch (NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Timeout must be greater than or equal to 0");
            a.setHeaderText("Invalid Timeout Error");
            a.setTitle("Error Dialog");
            a.showAndWait();
        }
    }
}
