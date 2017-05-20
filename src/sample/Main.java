package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sql.StatementEvent;
import java.sql.*;

public class Main extends Application {

    Connection connection = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root));
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

    public String findDataB2E(String input){
        this.initDB();
        System.out.println("Find Data Ran!");
        String sql = "SELECT * FROM `dict_table` WHERE `en_word` = ?";
        try{
            PreparedStatement ps= this.connection.prepareStatement(sql);
            ps.setString(1,input);
            System.out.println("Inputed word: " + input);
            ResultSet resultSet = ps.executeQuery();
            String output = resultSet.getString("bn_word");
            System.out.println("Data Found!");
            System.out.println("Final output: " + output);
            connection.close();
            return output;

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String findDataE2B(String input){
        this.initDB();
        System.out.println("Find Data Ran!");
        String sql = "SELECT * FROM `dict_table` WHERE `bn_word` = ?";
        try{
            PreparedStatement ps= this.connection.prepareStatement(sql);
            ps.setString(1,input);
            System.out.println("input is: "+input);
            ResultSet resultSet = ps.executeQuery();
            String output = resultSet.getString("en_word");
            System.out.println("Data Found!");
            System.out.println("Final output: " + output);
            connection.close();
            return output;

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
