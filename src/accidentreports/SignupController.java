package accidentreports;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignupController implements Initializable {

    @FXML
    private AnchorPane SignupPage;

    @FXML
    private TextField UsernameSignup;

    @FXML
    private PasswordField PasswordSignup;

    @FXML
    private Button Signup;

    @FXML
    private Hyperlink GoToSignin;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private TextField MobileSignup;

    @FXML
    private ComboBox GenderSignup;

    @FXML
    private TextField AgeSignup;

    @FXML
    private Label ErrorMessage;

    @FXML
    private FontAwesomeIcon Closesignup;

    int i = 0;

    private Connection con = null;
    private PreparedStatement pst = null;
    
    
    @FXML
    private void AddNewUser(ActionEvent e) throws IOException, SQLException{
        String username = UsernameSignup.getText();
        String mobile = MobileSignup.getText();
        int age = Integer.valueOf(AgeSignup.getText());
        String agestr = AgeSignup.getText() ;
        String gender = GenderSignup.getSelectionModel().getSelectedItem().toString();
        String password = PasswordSignup.getText();
        String cpassword = ConfirmPassword.getText();

        if (username != null && mobile != null && agestr != null && gender != null && password != null) {
            if (password.equals(cpassword)) {
                String sql = "insert into users (Username, Password, Age, Mobile, Gender) values (?,?,?,?,?)";
                try {
                    pst = con.prepareStatement(sql);
                    pst.setString(1, username);
                    pst.setString(2, password);
                    pst.setInt(3, age);
                    pst.setString(4, mobile);
                    pst.setString(5, gender);

                    i = pst.executeUpdate();

                    if (i == 1) {
                        System.out.println("User Added Successfully");
                        ErrorMessage.setText("Signup Successful.");
                        UsernameSignup.setText(null);
                        MobileSignup.setText(null);
                        AgeSignup.setText(null);
                        GenderSignup.setItems(null);
                        PasswordSignup.setText(null);
                        ConfirmPassword.setText(null);
                        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
                        Scene scene = new Scene(parent);

                        //This line gets the Stage  information
                        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

                        window.setScene(scene);
                        window.show();
                    }

                    AccidentReports cnObj = new AccidentReports();
                    cnObj.connectDB();

                } catch (SQLException ex) {
                    ErrorMessage.setVisible(true);
                    ErrorMessage.setText("Username already exit!");
                    Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    pst.close();
                }
            } else {
                ErrorMessage.setVisible(true);
                ErrorMessage.setText("Passwords doesn't match!");
            }
        } else {
            ErrorMessage.setVisible(true);
            ErrorMessage.setText("Fill all the fields!");
        }

    }

    @FXML
    private void GoToSigninPage(ActionEvent e) throws IOException, SQLException{
        Parent parent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(parent);

        //This line gets the Stage  information
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();

        AccidentReports cnObj = new AccidentReports();
        cnObj.connectDB();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Male", "Female", "Others");
        GenderSignup.setItems(list);
        
        con = AccidentReports.connectDB();
    }    
    
}
