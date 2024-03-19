package accidentreports;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private Button Close;

    @FXML
    private TextField UsernameSignin;

    @FXML
    private PasswordField PasswordSignin;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink CreateAccount;

    @FXML
    private Label ErrorMessage;

    @FXML
    private Button ShowUserDetails;

    //variables and Methods
    private Connection con = null;
    private PreparedStatement pst = null;
    
    @FXML
    private void GoToSignupPage(ActionEvent e) throws IOException, SQLException  {
        Parent signup = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene signupScene = new Scene(signup);

        //This line gets the Stage  information
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(signupScene);
        window.show();
    }

    @FXML
    private void LoginUser(ActionEvent event) throws IOException, SQLException {
        String us = UsernameSignin.getText();
        String uspass = PasswordSignin.getText();
        if (us != null) {
            try {
                pst = con.prepareStatement("Select * from Users where (username like ? or mobile like ?) and password like ?");
                
                pst.setString(1, us);
                pst.setString(2, us);
                pst.setString(3, uspass);

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String pass = rs.getString("password");
                    User.username = rs.getString("username");
                    if (pass.equals(uspass)) {
                        System.out.println("User Found");
                        
                        loginButton.getScene().getWindow().hide();

                        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                
                        Scene scene = new Scene(root);
                
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                    } else {
                        ErrorMessage.setText("Password doesn't match!");
                        ErrorMessage.setVisible(true);
                    }
                }
            } catch (SQLException ex) {
                ErrorMessage.setText("Username doesn't exist!");
                ErrorMessage.setVisible(true);
                Logger.getLogger(AccidentReports.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = AccidentReports.connectDB();
    }    
    
}
