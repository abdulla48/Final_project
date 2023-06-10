/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.BookedAppointments;
import Model.Users;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Patient_dashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button availableCourse_btn;

    @FXML
    private Button studentGrade_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private Label home_totalFemale;

    @FXML
    private Label home_totalMale;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private AnchorPane addStudents_form;

    private TextField addStudents_search;

    

    

    private TextField addStudents_studentNum;

    


    private TextField addStudents_firstName;

    private TextField addStudents_lastName;

    private DatePicker addStudents_birth;


    private ComboBox<?> addStudents_gender;

    private ImageView addStudents_imageView;


    @FXML
    private AnchorPane availableCourse_form;

    private TextField availableCourse_course;

    private TextField availableCourse_description;

    private TextField availableCourse_degree;


    

    @FXML
    private AnchorPane studentGrade_form;

    private TextField studentGrade_studentNum;

    private Label studentGrade_year;

    private Label studentGrade_course;

    private TextField studentGrade_firstSem;

    private TextField studentGrade_secondSem;


    

    @FXML
    private TextField studentGrade_search;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;
    @FXML
    private TextField patients_search;
    @FXML
    private TableView<Users> patients_tableView;
    @FXML
    private TableColumn<Users, ?> patientID_col;
    @FXML
    private TableColumn<Users, ?> patientFirstName_col;
    @FXML
    private TableColumn<Users, ?> patientLastName_col;
    @FXML
    private TextField patient_id;
    @FXML
    private ComboBox<?> patient_gender;
    @FXML
    private TextField patient_firstName;
    @FXML
    private TextField patient_lastName;
    @FXML
    private Button patient_addBtn;
    @FXML
    private Button patient_updateBtn;
    @FXML
    private Button patient_deleteBtn;
    @FXML
    private Button patient_clearBtn;
    @FXML
    private TextField patient_age;
    @FXML
    private TextField patient_email;
    @FXML
    private TextField patient_phone;
    @FXML
    private TextField FAppointmentDate_TF;
    @FXML
    private TextField FAppointmentDay_TF;
    @FXML
    private TextField FAppointmentTime_TF;
    @FXML
    private Button FAppointment_addBtn;
    @FXML
    private Button FAppointment_updateBtn;
    @FXML
    private Button FAppointment_clearBtn;
    @FXML
    private Button FAppointment_deleteBtn;
    @FXML
    private TableView<?> FAppointment_tableView;
    @FXML
    private TableColumn<?, ?> FAppointmentDate_col;
    @FXML
    private TableColumn<?, ?> FAppointmentDay_col;
    @FXML
    private TableColumn<?, ?> FAppointmentTime_col;
    @FXML
    private TextField BAppointmentDate_TF;
    @FXML
    private TextField BAppointmentDay_TF;
    @FXML
    private TextField BAppointmentTime_TF;
    @FXML
    private Button BAppointment_addBtn;
    @FXML
    private Button BAppointment_updateBtn;
    @FXML
    private Button BAppointment_clearBtn;
    @FXML
    private Button BAppointment_deleteBtn;
    @FXML
    private TableView<BookedAppointments> BAppointment_tableView;
    @FXML
    private TableColumn<BookedAppointments, ?> BAppointmentDate_col;
    @FXML
    private TableColumn<BookedAppointments, ?> BAppointmentDay_col;
    @FXML
    private TableColumn<BookedAppointments, ?> BAppointmentTime_col;
    @FXML
    private TableColumn<BookedAppointments, ?> patientAge_col;
    @FXML
    private TableColumn<BookedAppointments, ?> patientEmail_col;
    @FXML
    private TableColumn<BookedAppointments, ?> patientPhone_col;
    @FXML
    private TableColumn<BookedAppointments, ?> patientetGender_col;
    

    
    //*******************************************************************************************
    
    public void homeDisplayTotalEnrolledStudents() {

        

    }

    public void homeDisplayFemaleEnrolled() {

       

    }

    public void homeDisplayMaleEnrolled() {

        
    }

    public void homeDisplayTotalEnrolledChart() {

        
    }

    public void homeDisplayFemaleEnrolledChart() {

        

    }

    public void homeDisplayEnrolledMaleChart() {

        
    }
    
    //********************************************************************************************

    @FXML
    public void patientAdd() {

        
    }

    @FXML
    public void patientUpdate() {

        
    }

    @FXML
    public void patientDelete() {

        
    }

    @FXML
    public void patientClear() {
        
    }

    
    @FXML
    public void patientSearch() {

//        FilteredList<Users> filter = new FilteredList<>(addStudentsListD, e -> true);
//
//        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {
//
//            filter.setPredicate(predicateStudentData -> {
//
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//
//                String searchKey = newValue.toLowerCase();
//
//                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//
//        SortedList<studentData> sortList = new SortedList<>(filter);
//
//        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
//        addStudents_tableView.setItems(sortList);

    }

    private final String[] genderList = {"male", "female"};

    public void patientGenderList() {

        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        patient_gender.setItems(ObList);

    }
    
    @FXML
    private void addStudentsSearch(KeyEvent event) {
    }

    @FXML
    private void addStudentsYearList(ActionEvent event) {
    }

    @FXML
    private void patientSelect(MouseEvent event) {
    }
    

    

    
    

    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    



    
    
    //********************************************************************************************
    

    

//    public void availableCourseClear() {
//        availableCourse_course.setText("");
//        availableCourse_description.setText("");
//        availableCourse_degree.setText("");
//    }

    

//*********************************************************************************************

   

    @FXML
    public void studentGradesSearch() {

//        FilteredList<studentData> filter = new FilteredList<>(studentGradesList, e -> true);
//
//        studentGrade_search.textProperty().addListener((Observable, oldValue, newValue) -> {
//
//            filter.setPredicate(predicateStudentData -> {
//
//                if (newValue.isEmpty() || newValue == null) {
//                    return true;
//                }
//                String searchKey = newValue.toLowerCase();
//
//                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getFirstSem().toString().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getSecondSem().toString().contains(searchKey)) {
//                    return true;
//                } else if (predicateStudentData.getFinals().toString().contains(searchKey)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//
//        SortedList<studentData> sortList = new SortedList<>(filter);
//
//        sortList.comparatorProperty().bind(studentGrade_tableView.comparatorProperty());
//        studentGrade_tableView.setItems(sortList);

    }

    private double x = 0;
    private double y = 0;

    @FXML
    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    public void defaultNav(){
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }
    
    @FXML
    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

//            TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
//            addStudentsShowListData();
//            addStudentsYearList();
//            addStudentsGenderList();
//            addStudentsStatusList();
//            addStudentsCourseList();
//            addStudentsSearch();

        } else if (event.getSource() == availableCourse_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            availableCourse_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            studentGrade_btn.setStyle("-fx-background-color:transparent");

//            availableCourseShowListData();

        } else if (event.getSource() == studentGrade_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            studentGrade_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            availableCourse_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

//            studentGradesShowListData();
            studentGradesSearch();

        }
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // SORRY ABOUT THAT, I JUST NAMED THE DIFFERENT COMPONENTS WITH THE SAME NAME 
    // MAKE SURE THAT THE NAME YOU GAVE TO THEM ARE DIFFERENT TO THE OTHER OKAY?
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        displayUsername();
        defaultNav();
        
        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
//        addStudentsShowListData();
        patientGenderList();
//        addStudentsCourseList();

//        availableCourseShowListData();

//        studentGradesShowListData();

    }

    

    @FXML
    private void FAppointmentAdd(ActionEvent event) {
    }

    @FXML
    private void FAppointmentUpdate(ActionEvent event) {
    }

    @FXML
    private void FAppointmentClear(ActionEvent event) {
    }

    @FXML
    private void FAppointmentDelete(ActionEvent event) {
    }

    @FXML
    private void FAppointmentSelect(MouseEvent event) {
    }

    @FXML
    private void BAppointmentAdd(ActionEvent event) {
    }

    @FXML
    private void BAppointmentUpdate(ActionEvent event) {
    }

    @FXML
    private void BAppointmentClear(ActionEvent event) {
    }

    @FXML
    private void BAppointmentDelete(ActionEvent event) {
    }

    @FXML
    private void BAppointmentSelect(MouseEvent event) {
    }


}
