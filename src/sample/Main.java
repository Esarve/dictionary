package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

public class Main extends Application {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet resultSet = null;
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent scene = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(scene));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void initDB(){
        try{
            String url = "jdbc:sqlite:database.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connected Successfully!");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public String findDataE2B(String input){
        initDB();
//        System.out.println("Find Data Ran!");
        String sql = "SELECT * FROM `words` WHERE `en_word` = ?";
        try{
            this.ps= this.connection.prepareStatement(sql);
            ps.setString(1,input);
//            System.out.println("Inputed word: " + input);
            this.resultSet = this.ps.executeQuery();
//            System.out.println("Prepared statement executed!");
            String output = this.resultSet.getString("bn_word");
//            System.out.println("Data Found!");
//            System.out.println("Final output: " + output);
            this.connection.close();
            return output;

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String findDataB2E(String input){
        initDB();
//        System.out.println("Find Data Ran!");
        String sql = "SELECT * FROM `words` WHERE `bn_word` = ?";
        try{
            this.ps= this.connection.prepareStatement(sql);
            this.ps.setString(1,input);
//            System.out.println("input is: "+input);
            this.resultSet = this.ps.executeQuery();
//            System.out.println("Prepared statement executed!");
            String output = this.resultSet.getString("en_word");
//            System.out.println("Data Found!");
//            System.out.println("Final output: " + output);
            this.connection.close();
            return output;

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void add2DB_E2B(String input, String output){
        initDB();
        String sql = "INSERT INTO `words` (en_word, bn_word) VALUES (?, ?)";
        try {
            this.ps=this.connection.prepareStatement(sql);
            this.ps.setString(1,input);
            this.ps.setString(2,output);
            this.ps.execute();
            this.connection.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void add2DB_B2E(String input, String output){
        initDB();
        String sql = "INSERT INTO `words` (bn_word, en_word) VALUES (?, ?)";
        try {
            this.ps=this.connection.prepareStatement(sql);
            this.ps.setString(1,input);
            this.ps.setString(2,output);
            this.ps.execute();
            this.ps.close();
            this.connection.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteFromDB_E(String input){
        initDB();
        String sql = "DELETE FROM `words` WHERE `en_word` = ?";
        try{
            this.ps=this.connection.prepareStatement(sql);
            this.ps.setString(1,input);
            this.ps.execute();
            this.connection.close();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void deleteFromDB_B(String input){
        initDB();
        String sql = "DELETE FROM `words` WHERE `bn_word` = ?";
        try{
            this.ps=this.connection.prepareStatement(sql);
            this.ps.setString(1,input);
            this.ps.execute();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void updateDB_E2B(String input, String word){
        initDB();
        String sql = "UPDATE `words` SET `bn_word` = ? Where `en_word` = ?";
        try{
            System.out.println("Word: "+input);
            System.out.println("New Word: "+word);
            this.ps=this.connection.prepareStatement(sql);
            this.ps.setString(1,word);
            this.ps.setString(2,input);
            this.ps.execute();
            System.out.println("Added Successfully");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
