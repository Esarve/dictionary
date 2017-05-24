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
    private GridPane b2eLayout; // Will be removed

    @FXML
    private GridPane e2bLayout; // Will be removed

    @FXML
    private RadioButton b2eMode; // Will be removed

    @FXML
    private RadioButton e2bMode; // Will be removed

    @FXML
    private TextField input;

    @FXML
    private TextField output;

    @FXML
    private TextField addWord2;

    @FXML
    private TextField addWord1;

    @FXML
    private Label inputLabelE; // Will be removed

    @FXML
    private Label outputLabelB; // Will be removed

    @FXML
    private Label inputLabelB;

    @FXML
    private Label outputLabelE;

    @FXML
    private RadioButton e2bAdd; // Will be removed

    @FXML
    private RadioButton b2eAdd; // Will be removed

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



    private String inputTXT;
    private String outputTXT;

    // Checks if the word is Bengali or not

    private boolean isBangali(String check){
        int c = check.codePointAt(0);
        if(c>=0x0985 && c<=0x09FB){
            return true;
        }
        return false;
    }

    // For radio buttons. Will be removed sooon due to layout changes.
    // Currently enables / disables panels (E2B / B2E)
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

    // This method is for searching the current word
    // The SEARCH button uses this method
    // Will be removed most probably
    @FXML
    void searchAction(ActionEvent event) {
        if (!isBangali(input.getText())) {
            System.out.println("Running E2B mode");
            this.inputTXT = this.input.getText().toLowerCase();
            this.outputTXT = new Main().findDataB2E(inputTXT);
            this.output.setText(outputTXT);
        }
        else {
            System.out.println("Running B2E mode");
            this.inputTXT = this.input.getText();
            System.out.println(inputTXT);
            this.outputTXT = new Main().findDataE2B(inputTXT);
            this.output.setText(outputTXT);
        }
    }

    // For radio buttons. Will be removed sooon
    @FXML
    void radioButtonActionAdd(ActionEvent event) {
        if(e2bAdd.isSelected()){
            activateE2B();
        }else{
            activateB2E();
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

    // For radio buttons. Will be removed sooon
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

    // This method will clear the text fields in ADD tab
    // Both clear buttons will use this method
    @FXML
    void clearButtonAction(ActionEvent event) {
        if (event.getSource()==clearIn){
            addWord1.setText("");
        }else if(event.getSource()==clearOut){
            addWord2.setText("");
        }
    }

    //This method will add new words to the dictionary.
    // This is the method of ADD button in Add panel
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

    // This method will search for the translation of the word inputted in Modification tab.
    // It uses the Main's FIND FROM DB "findDataE2b" and "findDataB2E" method
    @FXML
    void dynamicSearchWord(KeyEvent event) {
        try{
            this.inputTXT=inputEditWord.getText();
            if(isBangali(inputTXT)){
                System.out.println("Running E2B mode");
                this.outputTXT=new Main().findDataE2B(inputTXT);
                displayCurrentWord.setText(outputTXT);

            }else{
                System.out.println("Running B2E mode");
                this.outputTXT=new Main().findDataB2E(inputTXT);
                displayCurrentWord.setText(outputTXT);
            }
        }catch (Exception e){
            displayCurrentWord.setText("");
        }
    }


    }
