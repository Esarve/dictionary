package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.*;

public class Controller {
    @FXML
    private GridPane b2eLayout;

    @FXML
    private GridPane e2bLayout;

    @FXML
    private RadioButton b2eMode;

    @FXML
    private RadioButton e2bMode;

    @FXML
    private TextField input;

    @FXML
    private TextField output;

    @FXML
    void radioButtonAction(ActionEvent event) {
        if (b2eMode.isSelected()){
            e2bLayout.setVisible(false);
            b2eLayout.setVisible(true);
        }
        else{
            b2eLayout.setVisible(false);
            e2bLayout.setVisible(true);
        }

    }

    @FXML
    void searchAction(ActionEvent event) {
        String input= this.input.getText().toLowerCase();
        String output = new Main().findData(input);
        this.output.setText(output);
    }

}
