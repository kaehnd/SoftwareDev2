/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Homework 6: SongDatabase
 * Name: Daniel Kaehn
 * Created: 1/16/2019
 */
package kaehnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * Controls interaction with FXML file SongDatabase.fxml
 */
public class SongDatabaseController implements Initializable {

    @FXML
    private Button okButton;

    @FXML
    private TextArea outputField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField artistField;

    @FXML
    private TextField yearField;

    private Path filePath;
    private File file;

    /**
     * Instantiates filepath and sets other parameters at application startup
     * @param url not used
     * @param resourceBundle not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setDefaultButton(true);
        filePath = Paths.get("src");
        file = new File(filePath + System.getProperty("file.separator") + "Database.txt");
        okButton.setDisable(true);
    }

    @FXML
    private void onOk(){
        String title = titleField.getText();
        String artist = artistField.getText();
        String year = yearField.getText();
        String record = "\"" + title + "\" , " + artist + ", " + year;
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(record);
            outputField.appendText(record + "\n");
        } catch (IOException exception) {
            System.err.println("File Write Error: ");
            exception.printStackTrace();
        }
        onClear();
    }

    @FXML
    private void onKeyPress(){
        if(!(titleField.getText().isEmpty() ||
                artistField.getText().isEmpty() || yearField.getText().isEmpty())) {
            okButton.setDisable(false);
        } else {
            okButton.setDisable(true);
        }
    }

    @FXML
    private void onClear(){
        titleField.clear();
        artistField.clear();
        yearField.clear();
        okButton.setDisable(true);
    }
}
