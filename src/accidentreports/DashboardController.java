/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accidentreports;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane navbar;

    @FXML
    private Circle circle;

    @FXML
    private Label user;

    @FXML
    private Button home;

    @FXML
    private Button myreports;

    @FXML
    private Button records;

    @FXML
    private Button statistics;

    @FXML
    private Button edit;

    @FXML
    private FontAwesomeIcon edit_icon;

    @FXML
    private Button contact;

    @FXML
    private AnchorPane home_page;

    @FXML
    private AnchorPane myreports_page;

    @FXML
    private AnchorPane report_accident_page;

    @FXML
    private Button report_accident_button;

    @FXML
    private Button report_accident_button1;

    @FXML
    private TableView<Reports> report_table_view;
    
     @FXML
    private TableView<Records> record_table;

    @FXML
    private TableColumn<Reports, Integer> col_report_id;
    
    @FXML
    private TableColumn<Reports, String> col_report_upazila;

    @FXML
    private TableColumn<Reports, String> col_report_date;

    @FXML
    private TableColumn<Reports, String> col_report_type;

    @FXML
    private TableColumn<Reports, String> col_report_place;

    @FXML
    private TableColumn<Reports, Integer> col_report_injured;

    @FXML
    private TableColumn<Reports, Integer> col_report_death;

    @FXML
    private TableColumn<Reports, String> col_report_cause;

    @FXML
    private TableColumn<Reports, String> col_report_varified;

    @FXML
    private Label report_table_title;

    @FXML
    private TextField search_field;

    @FXML
    private FontAwesomeIcon search_button;

    @FXML
    private ComboBox filter_cb;

    @FXML
    private Label pending_button;

    @FXML
    private Label approved_button;

    @FXML
    private Label disapproved_button;

    @FXML
    private Label all_button;

    @FXML
    private Label totalReports_label;

    @FXML
    private Label totalAccidents_label;

    @FXML
    private Label totalInjured_label;

    @FXML
    private Label totalDeath_label;

    @FXML
    private AnchorPane record_page;

    @FXML
    private AnchorPane statistics_page;

    @FXML
    private ComboBox contry_cb;

    @FXML
    private ComboBox division_cb;

    @FXML
    private ComboBox district_cb;

    @FXML
    private ComboBox upazila_cb;

    @FXML
    private ComboBox type_cb;

    @FXML
    private TextField place_tf;

    @FXML
    private TextField injured_tf;

    @FXML
    private TextField death_tf;

    @FXML
    private DatePicker date_df;

    @FXML
    private TextField cause_tf;

    @FXML
    private TextField description_tf;
    
     @FXML
    private ComboBox filter_record;
     
     @FXML
    private TableColumn<Records, Integer> col_recordid;

    @FXML
    private TableColumn<Records, String> col_recorddate;

    @FXML
    private TableColumn<Records, String> col_recordtype;

    @FXML
    private TableColumn<Records, String> col_recordupazila;

    @FXML
    private TableColumn<Records, String> col_recordplace;

    @FXML
    private TableColumn<Records, Integer> col_recordinjured;

    @FXML
    private TableColumn<Records, Integer> col_recorddeath;

    @FXML
    private TableColumn<Records, String> col_recordcause;
    
    @FXML
    private DatePicker record_dp;
    
    @FXML
    private Label record_label;

    //variables
    //variables and Methods
    private Connection con;
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    private Image image;
    private int ct;

    // Getting username from User.java class and putting it on the user label in the menu
    public void account() {
        user.setText(User.username);
        //System.out.println("in dashboard" + User.username);
    }

    // Esit button action
    public void exit() {

        System.exit(0);

    }

    //Menu Button Style change css when clicking buttons
    public void navButton() {
        home.setOnMouseClicked((MouseEvent event) -> {

            home.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color: linear-gradient(to bottom, #517ab5, #ae44a5); "
                    + "-fx-border-width: 0px 0px 0px 5px ; ");

            myreports.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            records.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            statistics.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            contact.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");
        });

        myreports.setOnMouseClicked((MouseEvent event) -> {

            myreports.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color: linear-gradient(to bottom, #517ab5, #ae44a5); "
                    + "-fx-border-width: 0px 0px 0px 5px ; ");

            home.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            records.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            statistics.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            contact.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");
        });

        records.setOnMouseClicked((MouseEvent event) -> {

            records.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color: linear-gradient(to bottom, #517ab5, #ae44a5); "
                    + "-fx-border-width: 0px 0px 0px 5px ; ");

            myreports.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            home.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            statistics.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            contact.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");
        });

        statistics.setOnMouseClicked((MouseEvent event) -> {

            statistics.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color: linear-gradient(to bottom, #517ab5, #ae44a5); "
                    + "-fx-border-width: 0px 0px 0px 5px ; ");

            myreports.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            records.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            home.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            contact.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");
        });

        contact.setOnMouseClicked((MouseEvent event) -> {

            contact.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.6), rgba(255, 106, 239, 0.6));"
                    + "-fx-border-color: linear-gradient(to bottom, #517ab5, #ae44a5); "
                    + "-fx-border-width: 0px 0px 0px 5px ; ");

            home.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            myreports.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            records.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");

            statistics.setStyle("-fx-background-color: linear-gradient(to bottom right,"
                    + "rgba(121, 172, 255, 0.2), rgba(255, 106, 239, 0.2));");
        });
    }

    //Home Page report an accident button action
    public void reportaccident() {
        if (report_accident_button.isFocused()) {
            report_accident_page.setVisible(true);
            home_page.setVisible(false);

            SetMakeReprotComboboxvalue();
        }
    }

    //My Reports page reprot an accident button work
    public void reportaccident1() {
        if (report_accident_button1.isFocused()) {
            report_accident_page.setVisible(true);
            myreports_page.setVisible(false);

            SetMakeReprotComboboxvalue();
        }
    }

    //Changing page for specific button click in the menu
    public void changePage() {

        if (myreports.isFocused()) {

            home_page.setVisible(false);
            myreports_page.setVisible(true);
            record_page.setVisible(false);
            statistics_page.setVisible(false);
            report_accident_page.setVisible(false);
            
            setFilterComboboxvalue();

            showReport("Select * from reports where ruser = '" + user.getText() + "' ");

        } else if (records.isFocused()) {

            home_page.setVisible(false);
            myreports_page.setVisible(false);
            record_page.setVisible(true);
            statistics_page.setVisible(false);
            report_accident_page.setVisible(false);
            
            allRecord();

        } else if (statistics.isFocused()) {

            home_page.setVisible(false);
            myreports_page.setVisible(false);
            record_page.setVisible(false);
            statistics_page.setVisible(true);
            report_accident_page.setVisible(false);

        } else {

            home_page.setVisible(true);
            myreports_page.setVisible(false);
            record_page.setVisible(false);
            statistics_page.setVisible(false);
            report_accident_page.setVisible(false);
        }
    }

    // My Reports page button work methods
    public void approvedButton() {
        report_table_title.setText("Approved Reports");
        String sql = "Select * from reports where ruser = '" + user.getText() + "' and isvarified = 'Yes' ";
        showReport(sql);
    }

    public void disapprovedButton() {
        report_table_title.setText("Pending Reports");
        String sql = "Select * from reports where ruser = '" + user.getText() + "' and isvarified = 'Declined' ";
        showReport(sql);
    }

    public void pendingButton() {
        report_table_title.setText("Declined Reports");
        String sql = "Select * from reports where ruser = '" + user.getText() + "' and isvarified = 'Pending' ";
        showReport(sql);
    }

    public void allmyreportButton() {
        report_table_title.setText("All My Reports");
        String sql = "Select * from reports where ruser = '" + user.getText() + "'";
        showReport(sql);
    }

    //Filtering desc (high to low)
    public void filterHightoLow() {
        if (filter_cb.getSelectionModel().getSelectedItem().toString() == "ID") {
            report_table_title.setText("Descending by Report ID");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by reportId desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Date") {
            report_table_title.setText("Descending by Date");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rDate desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Type") {
            report_table_title.setText("Descending by Type");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rtype desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Upazila") {
            report_table_title.setText("Descending by Upazila");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by upazilaname desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Place") {
            report_table_title.setText("Descending by Place");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rplace desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Injured") {
            report_table_title.setText("Descending by Injured");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rInjured desc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Death") {
            report_table_title.setText("Descending by Death");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rdeath desc";
            showReport(sql);
        }
    }

    //Filtering asc (low to high)
    public void filterLowtoHigh() {
        if (filter_cb.getSelectionModel().getSelectedItem().toString() == "ID") {
            report_table_title.setText("Descending by Report ID");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by reportId asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Date") {
            report_table_title.setText("Descending by Date");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rDate asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Type") {
            report_table_title.setText("Descending by Type");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rtype asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Upazila") {
            report_table_title.setText("Descending by Upazila");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by upazilaname asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Place") {
            report_table_title.setText("Descending by Place");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rplace asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Injured") {
            report_table_title.setText("Descending by Injured");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' order by rInjured asc";
            showReport(sql);
        } else if (filter_cb.getSelectionModel().getSelectedItem().toString() == "Death") {
            report_table_title.setText("Descending by Death");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause,isvarified from reports where ruser = '" + user.getText() + "' and isvarified = 'Yes' order by rdeath asc";
            showReport(sql);
        }
    }

    //Show reports by user in Myreports
    //Fething value from reports table in database for specific user By creating a Reports.java class
    public ObservableList<Reports> listData(String sql) {
        ObservableList<Reports> reportList = FXCollections.observableArrayList();

        con = AccidentReports.connectDB();

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            Reports report;

            while (rs.next()) {
                // Fetching data from database by column
                report = new Reports(rs.getInt("reportid"),
                        rs.getString("upazilaname"),
                        rs.getString("rdate"),
                        rs.getString("rtype"),
                        rs.getString("rplace"),
                        rs.getInt("rinjured"),
                        rs.getInt("rdeath"),
                        rs.getString("rcause"),
                        rs.getString("isvarified"));

                System.out.println(rs.getString("rdate") + " " + rs.getString("rplace") + " " + rs.getInt("rinjured") + " " + rs.getInt("rdeath"));

                reportList.add(report);
            }
        } catch (Exception e) {

        }

        return reportList;
    }

    //Placing fetched values in the table
    public void showReport(String sql) {
        ObservableList<Reports> show = listData(sql);

        col_report_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_report_upazila.setCellValueFactory(new PropertyValueFactory<>("upazilaname"));
        col_report_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_report_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_report_place.setCellValueFactory(new PropertyValueFactory<>("place"));
        col_report_injured.setCellValueFactory(new PropertyValueFactory<>("injured"));
        col_report_death.setCellValueFactory(new PropertyValueFactory<>("death"));
        col_report_cause.setCellValueFactory(new PropertyValueFactory<>("cause"));
        col_report_varified.setCellValueFactory(new PropertyValueFactory<>("varified"));

        report_table_view.setItems(show);

    }

    // Add a new report button work
    public void makeReport() {

        con = AccidentReports.connectDB();
        String sql = "Insert into reports values (?,?,?,?,?,?,?,?,?,?,?)";

        System.out.println("Selected: " + ((TextField) date_df.getEditor()).getText());

        if (place_tf.getText().isEmpty() | injured_tf.getText().isEmpty() | death_tf.getText().isEmpty()
                | ((TextField) date_df.getEditor()).getText().isEmpty()
                | division_cb.getSelectionModel().isEmpty()
                | district_cb.getSelectionModel().isEmpty()
                | upazila_cb.getSelectionModel().isEmpty()
                | type_cb.getSelectionModel().isEmpty()) {

            Alert alert = new Alert(AlertType.ERROR);

            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Fill/Select all the fields");

            alert.showAndWait();
        } else {
            try {
                String file = "D:\\Files\\Java programs\\FXML\\AccidentReports\\src\\Images\\Hatirjheel_bus.jpg";

                pst = con.prepareStatement(sql);
                pst.setObject(1, upazila_cb.getSelectionModel().getSelectedItem().toString());
                pst.setObject(2, user.getText());
                pst.setObject(3, ((TextField) date_df.getEditor()).getText());
                pst.setObject(4, type_cb.getSelectionModel().getSelectedItem().toString());
                pst.setObject(5, place_tf.getText());
                pst.setObject(6, Integer.parseInt(injured_tf.getText()));
                pst.setObject(7, Integer.parseInt(death_tf.getText()));
                pst.setObject(8, cause_tf.getText());
                pst.setObject(9, description_tf.getText());
                pst.setObject(10, file);
                pst.setObject(11, "Pending");

                pst.executeUpdate();

                Alert alert = new Alert(AlertType.INFORMATION);

                alert.setTitle("Info");
                alert.setHeaderText(null);
                alert.setContentText("Accident Reported. Thank you for the support.");

                alert.showAndWait();

            } catch (SQLException ex) {
                System.out.println("Error");
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // setting My Reports page filter comboBox values
    public void setFilterComboboxvalue() {
        ObservableList<String> filterList = FXCollections.observableArrayList("ID", "Date", "Type", "Upazila", "Place", "Injured", "Death");
        filter_cb.setItems(filterList);
        filter_record.setItems(filterList);
    }

    //Setting add a new report page comboBox value
    public void SetMakeReprotComboboxvalue() {
        //set Contry combobox values
        ObservableList<String> contrylist = FXCollections.observableArrayList("Bangladesh", "Others");
        contry_cb.setItems(contrylist);

        ct = 0;
        con = AccidentReports.connectDB();
        
        //  fetch Division from database
        String sql = "Select divisionName from division";

        String[] city = new String[100];

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                city[ct] = rs.getString("DivisionName");
                ct++;
            }
        } catch (SQLException ex) {
        }

        //Show Fetched data in division comboBox values
        String[] setlist = new String[ct];

        for (int j = 0; j < ct; j++) {
            setlist[j] = city[j];
        }
        ObservableList<String> list = FXCollections.observableArrayList(setlist);
        division_cb.setItems(list);
        
        contry_cb.getSelectionModel().select(0);

        //Set Type combobox values
        ObservableList<String> typelist = FXCollections.observableArrayList("Road", "Fire", "Chemical", "Others");
        type_cb.setItems(typelist);

    }
    
    // Set District ComboBox values
    public void setDistrictComboboxvalue() {
        ct = 0;
        con = AccidentReports.connectDB();

            String sql = "Select districts.Districtname from districts inner join division on "
                    + "division.divisionid=districts.DivisionId where Division.DivisionName "
                    + "    = '" + division_cb.getSelectionModel().getSelectedItem().toString() + "' ";

            String[] city = new String[100];

            //Fetch data of name from Database table dsitricts
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    city[ct] = rs.getString("DistrictName");
                    ct++;
                }
            } catch (SQLException ex) {}

            //Show Fetched data in district comboBox 
            String[] setlist = new String[ct];

            for (int j = 0; j < ct; j++) {
                setlist[j] = city[j];
            }

            ObservableList<String> list = FXCollections.observableArrayList(setlist);
            district_cb.setItems(list);
    }

    // Set upazila ComboBox values
    public void setUpazilaComboboxvalue() {
        ct = 0;
        con = AccidentReports.connectDB();
            System.out.println(district_cb.getSelectionModel().getSelectedItem().toString());
            String sql = "Select Upazilas.UpazilaName from Upazilas inner join districts on "
                    + "Upazilas.DistrictId = districts.DistrictId where districts.DistrictName "
                    + " = '" + district_cb.getSelectionModel().getSelectedItem().toString() + "' ";

            String[] city = new String[100];

            //Fetch data of name from Database table Upazilas
            try {
                st = con.createStatement();
                rs = st.executeQuery(sql);

                while (rs.next()) {
                    city[ct] = rs.getString("UpazilaName");
                    ct++;
                }
            } catch (SQLException ex) {
            }

            //Show Fetched data in upazila comboBox 
            String[] setlist = new String[ct];

            for (int j = 0; j < ct; j++) {
                setlist[j] = city[j];
            }
            ObservableList<String> list = FXCollections.observableArrayList(setlist);
            upazila_cb.setItems(list);
    }
    
    
    
    /// Records page Portion
    
    //Show reports by user in Records
    //Fething value from reports table in database for specific user By creating a Reports.java class
    public ObservableList<Records> listallData(String sql) {
        ObservableList<Records> recordtList = FXCollections.observableArrayList();

        con = AccidentReports.connectDB();

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            Records record;

            while (rs.next()) {
                // Fetching data from database by column
                record = new Records(rs.getInt("reportid"),
                        rs.getString("Upazilaname"),
                        rs.getString("rdate"),
                        rs.getString("rtype"),
                        rs.getString("rplace"),
                        rs.getInt("rinjured"),
                        rs.getInt("rdeath"),
                        rs.getString("rcause"));

                System.out.println(rs.getString("rdate") + " " + rs.getString("rplace") + " " + rs.getInt("rinjured") + " " + rs.getInt("rdeath"));

                recordtList.add(record);
            }
        } catch (Exception e) {

        }

        return recordtList;
    }

    //Placing fetched values in the table
    public void showRecords(String sql) {
        ObservableList<Records> show = listallData(sql);

        col_recordid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_recordupazila.setCellValueFactory(new PropertyValueFactory<>("upazilaname"));
        col_recorddate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_recordtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_recordplace.setCellValueFactory(new PropertyValueFactory<>("place"));
        col_recordinjured.setCellValueFactory(new PropertyValueFactory<>("injured"));
        col_recorddeath.setCellValueFactory(new PropertyValueFactory<>("death"));
        col_recordcause.setCellValueFactory(new PropertyValueFactory<>("cause"));

        record_table.setItems(show);

    }
    //Filtering desc (high to low)
    public void filterRecordHightoLow() {
        if (filter_record.getSelectionModel().getSelectedItem().toString() == "ID") {
            record_label.setText("Descending by Report ID");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by reportId desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Date") {
            record_label.setText("Descending by Date");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rDate desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Type") {
            record_label.setText("Descending by Type");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rtype desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Upazila") {
            record_label.setText("Descending by Upazila");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by upazilaname desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Place") {
            record_label.setText("Descending by Place");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rplace desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Injured") {
            record_label.setText("Descending by Injured");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rInjured desc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Death") {
            record_label.setText("Descending by Death");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rdeath desc";
            showRecords(sql);
        }
    }

    //Filtering asc (low to high)
    public void filterRecordLowtoHigh() {
        if (filter_record.getSelectionModel().getSelectedItem().toString() == "ID") {
            record_label.setText("Ascending Report ID");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by reportId asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Date") {
            record_label.setText("Ascending by Date");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rDate asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Type") {
            record_label.setText("Ascending by Type");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rtype asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Upazila") {
            record_label.setText("Ascending by Upazila");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by upazilaname asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Place") {
            record_label.setText("Ascending by Place");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rplace asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Injured") {
            record_label.setText("Ascending by Injured");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rInjured asc";
            showRecords(sql);
        } else if (filter_record.getSelectionModel().getSelectedItem().toString() == "Death") {
            record_label.setText("Ascending by Death");
            String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' order by rdeath asc";
            showRecords(sql);
        }
    }
    
    public void allRecord(){
        
        String sql = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports";
        
        showRecords(sql);
    }
    
    public void showTodaysRecord(){
        
        String pattern = "MM/dd/yyyy";
        String date = new SimpleDateFormat(pattern).format(new Date());
        
        System.out.println("Cur Date: " + date);
        
        String sql  = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where isvarified = 'Yes' and rdate = '"+ date +"' ";
        
        showRecords(sql);
    }
    
    public void showRecordOndate(){
        
        String sql  = "select reportID,Upazilaname,rdate,rtype,rplace,rinjured,rdeath,rcause from reports where rdate = '"+ ((TextField) record_dp.getEditor()).getText() +"'and isvarified = 'Yes' ";
        showRecords(sql);
    }

    // home page
    public void totalReports(){
        
        con = AccidentReports.connectDB();
//        WE WILL COUNT THE SURNAME WHICH INDICATES THE TOTAL OF STUDENT
        String sql = "SELECT count(reportId) FROM reports";
        String sql1 = "SELECT count(reportId) FROM reports where ";
        
        try{
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            PreparedStatement pst1 = con.prepareStatement(sql1);
            ResultSet rs1 = pst1.executeQuery();
            
            
            while(rs.next()){
                
                String totalReports = rs.getString("count(reportId)");
                System.out.println(totalReports);
                totalReports_label.setText("total reports: " +totalReports);
                
            }
            
            while(rs1.next()){
                
                String totalAccident = rs1.getString("count(reportId)");
                System.out.println("Total accident: "+totalAccident);
                totalAccidents_label.setText(totalAccident);
                
            }
            
        }catch(Exception e){}
        
    }
    @Override
    public void initialize(URL url, ResourceBundle resource) {
        account();
        
        //setHomePageLabels();
        totalReports();

        // Toggle button view on mouse clicked
        navButton();

        // Change Page view
        changePage();

    }
}
