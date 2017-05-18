package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
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

}
