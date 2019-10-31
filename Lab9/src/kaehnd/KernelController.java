/*
 * Course: CS1021 - 091
 * Term: Winter 2018
 * Lab 9: Image Manipulator
 * Name: Daniel Kaehn
 * Created: 2/6/2019
 */
package kaehnd;
;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for kernel window for blur and sharpen effects
 */
public class KernelController implements Initializable {


    private Lab9Controller lab9Controller;

    private ArrayList<TextField> kernelFields;

    private final double[] blurDefault = {0, 1, 0, 1, 5, 1, 0, 1, 0};
    private final double[] sharpenDefault = {0, -1, 0, -1, 5, -1, 0, -1, 0};


    @FXML
    private TextField kernelField0;
    @FXML
    private TextField kernelField1;
    @FXML
    private TextField kernelField2;
    @FXML
    private TextField kernelField3;
    @FXML
    private TextField kernelField4;
    @FXML
    private TextField kernelField5;
    @FXML
    private TextField kernelField6;
    @FXML
    private TextField kernelField7;
    @FXML
    private TextField kernelField8;




    /**
     * Runs on on controller instantiation before window is shown
     * @param url not used
     * @param resourceBundle not used
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kernelFields = new ArrayList<>();
        kernelFields.add(kernelField0);
        kernelFields.add(kernelField1);
        kernelFields.add(kernelField2);
        kernelFields.add(kernelField3);
        kernelFields.add(kernelField4);
        kernelFields.add(kernelField5);
        kernelFields.add(kernelField6);
        kernelFields.add(kernelField7);
        kernelFields.add(kernelField8);
    }

    public void setLab9Controller(Lab9Controller lab9Controller) {
        this.lab9Controller = lab9Controller;
    }

    @FXML
    private void blur(ActionEvent e){
        for(int i = 0; i < kernelFields.size(); i++){
            kernelFields.get(i).setText(String.valueOf(blurDefault[i]));
        }
    }

    @FXML
    private void sharpen(ActionEvent e){
        for(int i = 0; i < kernelFields.size(); i++){
            kernelFields.get(i).setText(String.valueOf(sharpenDefault[i]));
        }
    }

    @FXML
    private void apply(ActionEvent e){
      lab9Controller.convolveFromFields(kernelFields);
    }


}
