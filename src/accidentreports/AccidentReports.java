/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentreports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class AccidentReports extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
public static Connection connectDB() {
        
        Connection con = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                            "jdbc:sqlserver://localhost:1433;databaseName = AccidentReport; selectMethod=cursor", "sa", "123456");
            System.out.println("DATABASE NAME IS:"
                    + con.getMetaData().getDatabaseProductName());
            
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
            
            while (resultSet.next()) {
                System.out.println("User email: "
                        + resultSet.getString("mobile") +" "+"Password: "+ resultSet.getString("Password")) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
