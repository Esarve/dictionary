package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.event.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

    private double xOffset = 375;
    private double yOffset = 0;



    @FXML
    private VBox tPanel;

    @FXML
    private JFXTextField input;

    @FXML
    private JFXTextField output;

    @FXML
    private VBox addPanel;

    @FXML
    private JFXTextField addWord1;

    @FXML
    private JFXTextField addWord2;

    @FXML
    private VBox mPanel;

    @FXML
    private JFXTextField inputEditWord;

    @FXML
    private JFXTextField displayCurrentWord;

    @FXML
    private JFXTextField inputNewWord;

    @FXML
    private VBox dPanel;

    @FXML
    private JFXTextField inputWord;

    @FXML
    private JFXTextField displayWord;

    @FXML
    private Label title;

    @FXML
    private AnchorPane ap;



    private String inputTXT;
    private String outputTXT;
    // Checks if the word is Bengali or not

    private boolean isBangali(String check){
        int c = check.codePointAt(0);
        return c >= 0x0985 && c <= 0x09FB;
    }

    // This method is for searching the current word
    // The SEARCH button uses this method
    // Will be removed most probably
    @FXML
    void searchAction(ActionEvent event) {
        if (isBangali(input.getText())) {
            System.out.println("Running B2E mode");
            this.inputTXT = this.input.getText().toLowerCase();
            this.outputTXT = new Main().findDataB2E(inputTXT);
            this.output.setText(outputTXT);
        }
        else {
            System.out.println("Running E2B mode");
            this.inputTXT = this.input.getText();
            System.out.println(inputTXT);
            this.outputTXT = new Main().findDataE2B(inputTXT);
            this.output.setText(outputTXT);
        }
    }

    // For radio buttons. Will be removed sooon
    private void clearInputFields(){
        addWord1.setText("");
        addWord2.setText("");
    }

    // For radio buttons. Will be removed sooon
    private void clearTextField(){
        input.setText("");
        output.setText("");
    }

    // This method will search for the translation of the word inputted in Modification tab.
    // It uses the Main's FIND FROM DB "findDataE2b" and "findDataB2E" method
    @FXML
    void dynamicSearchWord(KeyEvent event) {
        try{
            this.inputTXT=inputEditWord.getText();
            if(isBangali(inputTXT)){
                System.out.println("Running B2E mode");
                this.outputTXT=new Main().findDataB2E(inputTXT);
                displayCurrentWord.setText(outputTXT);

            }else{
                System.out.println("Running E2B mode");
                this.outputTXT=new Main().findDataE2B(inputTXT);
                displayCurrentWord.setText(outputTXT);
            }
        }catch (Exception e){
            displayCurrentWord.setText("");
        }
    }

    @FXML
    void dynamicSearchWordDel(KeyEvent event) {
        try{
            this.inputTXT=inputWord.getText();
            if(isBangali(inputTXT)){
                System.out.println("Running B2E mode");
                this.outputTXT=new Main().findDataB2E(inputTXT);
                displayWord.setText(outputTXT);
            }else{
                System.out.println("Running E2B mode");
                this.outputTXT=new Main().findDataE2B(inputTXT);
                this.displayWord.setText(outputTXT);
            }
        }catch (Exception e){
            this.displayWord.setText("");
        }
    }

    @FXML
    void deleteWord(ActionEvent event) {
        System.out.println("Delete ran");
        this.inputTXT=inputWord.getText().toLowerCase();
        if (isBangali(inputTXT)){
            new Main().deleteFromDB_B(inputTXT);
        }else {
            new Main().deleteFromDB_E(inputTXT);
        }

    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if (isBangali(addWord1.getText())) {
            System.out.println("Running B2E mode");
            this.inputTXT=addWord1.getText();
            this.outputTXT=addWord2.getText().toLowerCase();
            new Main().add2DB_B2E(inputTXT,outputTXT);
        }else {
            System.out.println("Running E2B mode");
            this.inputTXT=addWord1.getText().toLowerCase();
            this.outputTXT=addWord2.getText();
            new Main().add2DB_E2B(inputTXT,outputTXT);
        }
    }

    @FXML
    void switchPanelToTrans(ActionEvent event) {
        tPanel.setVisible(true);
        addPanel.setVisible(false);
        mPanel.setVisible(false);
        dPanel.setVisible(false);
        System.out.println("T-Panel enabled");
        title.setText("Translation");
    }
    @FXML
    void switchPanelAdd(ActionEvent event) {
        tPanel.setVisible(false);
        addPanel.setVisible(true);
        mPanel.setVisible(false);
        dPanel.setVisible(false);
        System.out.println("A-Panel enabled");
        title.setText("Add New Word");
    }

    @FXML
    void switchPanelMod(ActionEvent event) {
        tPanel.setVisible(false);
        addPanel.setVisible(false);
        mPanel.setVisible(true);
        dPanel.setVisible(false);
        System.out.println("M-Panel enabled");
        title.setText("Modify Word");
    }

    @FXML
    void switchPanelDel(ActionEvent event) {
        tPanel.setVisible(false);
        addPanel.setVisible(false);
        mPanel.setVisible(false);
        dPanel.setVisible(true);
        System.out.println("D-Panel enabled");
        title.setText("Delete");
    }

    @FXML
    void closeAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void minimizeAction(ActionEvent event) {

        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void OnMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    void OnMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ap.getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
}
