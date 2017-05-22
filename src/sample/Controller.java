package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    private TextField addWord2;

    @FXML
    private TextField addWord1;

    @FXML
    private Label inputLabelE;

    @FXML
    private Label outputLabelB;

    @FXML
    private Label inputLabelB;

    @FXML
    private Label outputLabelE;

    @FXML
    private RadioButton e2bAdd;

    @FXML
    private RadioButton b2eAdd;

    @FXML
    private Button clearIn;

    @FXML
    private Button clearOut;

    @FXML
    private TextField inputEditWord;

    @FXML
    private TextField displayCurrentWord;

    @FXML
    private TextField inputNewWord;

    @FXML
    private Button save;

    @FXML
    private Button reset;



    String inputTXT;
    String outputTXT;



    @FXML
    void radioButtonAction(ActionEvent event) {
        if (b2eMode.isSelected()){
            e2bLayout.setVisible(false);
            b2eLayout.setVisible(true);
            clearTextField();
        }
        else{
            b2eLayout.setVisible(false);
            e2bLayout.setVisible(true);
            clearTextField();
        }

    }

    @FXML
    void searchAction(ActionEvent event) {
        if (e2bMode.isSelected()) {
            System.out.println("Running E2B mode");
            this.inputTXT = this.input.getText().toLowerCase();
            this.outputTXT = new Main().findDataB2E(inputTXT);
            this.output.setText(outputTXT);
        }
        else if (b2eMode.isSelected()){
            System.out.println("Running B2E mode");
            this.inputTXT = this.input.getText();
            System.out.println(inputTXT);
            this.outputTXT = new Main().findDataE2B(inputTXT);
            this.output.setText(outputTXT);
        }
    }

    @FXML
    void radioButtonActionAdd(ActionEvent event) {
        if(e2bAdd.isSelected()){
            activateE2B();
        }else{
            activateB2E();
        }
    }

    private void clearInputFields(){
        addWord1.setText("");
        addWord2.setText("");
    }

    private void clearTextField(){
        input.setText("");
        output.setText("");
    }

    private void activateB2E(){
        inputLabelB.setVisible(true);
        inputLabelE.setVisible(false);
        outputLabelE.setVisible(true);
        outputLabelB.setVisible(false);
        System.out.println("activateE2B Ran");
    }

    private void activateE2B(){
        inputLabelB.setVisible(false);
        inputLabelE.setVisible(true);
        outputLabelE.setVisible(false);
        outputLabelB.setVisible(true);
        System.out.println("activateB2E Ran");
    }

    @FXML
    void clearButtonAction(ActionEvent event) {
        if (event.getSource()==clearIn){
            addWord1.setText("");
        }else if(event.getSource()==clearOut){
            addWord2.setText("");
        }
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        if (e2bAdd.isSelected()){
            this.inputTXT=addWord1.getText().toLowerCase();
            this.outputTXT=addWord2.getText().toLowerCase();
            new Main().add2DB_E2B(inputTXT,outputTXT);
            clearTextField();
        }else if(b2eAdd.isSelected()){
            this.inputTXT=addWord1.getText().toLowerCase();
            this.outputTXT=addWord2.getText().toLowerCase();
            new Main().add2DB_B2E(inputTXT,outputTXT);
            clearTextField();
        }
    }

    @FXML
    void dynamicSearchWord(KeyEvent event) {

    }
    }
