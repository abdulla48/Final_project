/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Appointments;
import Model.BookedAppointments;
import Model.Users;
import View.ViewManager;
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Admin_dashboardController implements Initializable {
    String loggedInUsername;

    public void setLoggedInUsername(String loggedInUsername) {
        this.loggedInUsername = loggedInUsername;
    }

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

    private Button addStudents_btn;

    private Button availableCourse_btn;

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
    private AnchorPane availableCourse_form;


    

    @FXML
    private AnchorPane studentGrade_form;

  
    

    @FXML
    private TextField studentGrade_search;

   

    
    @FXML
    private TextField patients_search;
    @FXML
    private TableView<Users> patients_tableView;
    @FXML
    private TableColumn<Users, Integer> patientID_col;
    @FXML
    private TableColumn<Users, String> patientFirstName_col;
    @FXML
    private TableColumn<Users, String> patientLastName_col;
    @FXML
    private TableColumn<Users, Integer> patientAge_col;
    @FXML
    private TableColumn<Users, String> patientEmail_col;
    @FXML
    private TableColumn<Users, Integer> patientPhone_col;
    @FXML
    private TableColumn<Users, String> patientetGender_col;
    @FXML
    private TextField patient_id;
    @FXML
    private ComboBox<String> patient_gender;
    @FXML
    private TextField patient_firstName;
    @FXML
    private TextField patient_lastName;
    @FXML
    private TextField patient_age;
    @FXML
    private TextField patient_email;
    @FXML
    private TextField patient_phone;
    @FXML
    private Button patient_addBtn;
    @FXML
    private Button patient_updateBtn;
    @FXML
    private Button patient_deleteBtn;
    @FXML
    private Button patient_clearBtn;
    
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
    private TableView<Appointments> FAppointment_tableView;
    @FXML
    private TableColumn<Appointments, String> FAppointmentDate_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentDay_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentTime_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentStatus_col1;
    @FXML
    private Button BAppointment_addBtn;
    @FXML
    private Button BAppointment_updateBtn;
    @FXML
    private Button BAppointment_clearBtn;
    @FXML
    private Button BAppointment_deleteBtn;
    
    
    @FXML
    private Button addPatients_btn;
    @FXML
    private Button freeAppointments_btn;
    @FXML
    private Button bookedAppointments_btn;
    @FXML
    private AnchorPane addPatients;
    @FXML
    private Button patient_showBtn;
    
    EntityManagerFactory emf;
    
    @FXML
    private Button FAppointment_showBtn1;
    @FXML
    private TextField BAppointmentID_TF;
    @FXML
    private TextField BAppointmentUserID_TF;
    @FXML
    private TextField BAppointmentDCom_TF1;
    @FXML
    private TableView<BookedAppointments> BAppointment_tableView;
    @FXML
    private TableColumn<BookedAppointments, Integer> BAppointmentID_col;
    @FXML
    private TableColumn<BookedAppointments, Integer> BAppointmentUserID_col;
    @FXML
    private TableColumn<BookedAppointments, String> BAppointmentStatus_col;
    @FXML
    private TableColumn<BookedAppointments, String> BAppointmentDCom_col1;
    @FXML
    private Button BAppointment_showBtn1;
    

    
   
    
    //********************************************************************************************

    
    private void displayUsername(){
        this.username.setText(loggedInUsername);
    }
    
    @FXML
    private void patientShow(ActionEvent event) {
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List <Users> Patients = em.createNamedQuery("Users.findPatients").getResultList();
        patients_tableView.getItems().setAll(Patients);
        em.close();
        
        Users selectedPatient = patients_tableView.getSelectionModel().getSelectedItem();
        
        patient_id.setText(String.valueOf(selectedPatient.getId()));
        patient_gender.setValue(selectedPatient.getGender());
        patient_firstName.setText(selectedPatient.getFirstname());
        patient_lastName.setText(selectedPatient.getLastname());
        patient_age.setText(String.valueOf(selectedPatient.getAge()));
        patient_email.setText(selectedPatient.getEmail());
        patient_phone.setText(String.valueOf(selectedPatient.getPhone()));
    }
    
    @FXML
    public void patientAdd() {
        Users user = new Users();
        
        
        user.setId(null);
        user.setGender(patient_gender.getValue());
        user.setFirstname(patient_firstName.getText());
        user.setLastname(patient_lastName.getText());
        user.setAge(Integer.parseInt(patient_age.getText()));
        user.setPhone(Integer.parseInt(patient_phone.getText()));
        user.setEmail(patient_email.getText());
        user.setRole("patient");
        
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        
        
        
    }

    @FXML
    public void patientUpdate() {
        Users selectedPatient = patients_tableView.getSelectionModel().getSelectedItem();
        
        
        
        if (selectedPatient != null) {

            EntityManager em = this.emf.createEntityManager(); 
            em.getTransaction().begin();

            try {
                
                selectedPatient.setId(null);
                selectedPatient.setGender(patient_gender.getValue());
                selectedPatient.setFirstname(patient_firstName.getText());
                selectedPatient.setLastname(patient_lastName.getText());
                selectedPatient.setAge(Integer.parseInt(patient_age.getText()));
                selectedPatient.setPhone(Integer.parseInt(patient_phone.getText()));
                selectedPatient.setEmail(patient_email.getText());
                selectedPatient.setRole("patient");

                
                em.merge(selectedPatient);

                em.getTransaction().commit();
                
            } catch (Exception e) {
                
                e.printStackTrace();
                em.getTransaction().rollback();
            } finally {
                
                em.close();
            }
        }
        
        
    }

    @FXML
    public void patientDelete() {
        Users selectedPatient = patients_tableView.getSelectionModel().getSelectedItem();
    
        if (selectedPatient != null) {
            int patientId = selectedPatient.getId(); 

            EntityManager em = this.emf.createEntityManager(); 

            try {
                em.getTransaction().begin();

                Users patientToDelete = em.find(Users.class, patientId);

                em.remove(patientToDelete);

                em.getTransaction().commit();

                patients_tableView.getItems().remove(selectedPatient);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
        
    }

    
    
    private void resetControls(){
        
        patient_id.setText("");
        patient_firstName.setText("");
        patient_lastName.setText("");
        patient_age.setText("");
        patient_phone.setText("");
        patient_email.setText("");
        patients_tableView.getItems().clear();
    }
    
    @FXML
    public void patientClear() {
        resetControls();
    }

    
    @FXML
    public void patientSearch() {

        String searchQuery = patients_search.getText().toLowerCase();
        ObservableList<Users> filteredData = FXCollections.observableArrayList();

        for (Users item : patients_tableView.getItems()) {
           
            if (item.getFirstname().toLowerCase().contains(searchQuery)) {
                filteredData.add(item);
            }
        }
        patients_tableView.setItems(filteredData);
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
    



    

//*********************************************************************************************

   

    

    

    @FXML
    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                ViewManager.closeAdminDashboardPage();
                ViewManager.openAdminLoginPage();

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
            addPatients.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addPatients_btn.setStyle("-fx-background-color:transparent");
            freeAppointments_btn.setStyle("-fx-background-color:transparent");
            bookedAppointments_btn.setStyle("-fx-background-color:transparent");

            

        } else if (event.getSource() == addPatients_btn) {
            home_form.setVisible(false);
            addPatients.setVisible(true);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(false);

            addPatients_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            freeAppointments_btn.setStyle("-fx-background-color:transparent");
            bookedAppointments_btn.setStyle("-fx-background-color:transparent");



        } else if (event.getSource() == freeAppointments_btn) {
            home_form.setVisible(false);
            addPatients.setVisible(false);
            availableCourse_form.setVisible(true);
            studentGrade_form.setVisible(false);

            freeAppointments_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addPatients_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            bookedAppointments_btn.setStyle("-fx-background-color:transparent");



        } else if (event.getSource() == bookedAppointments_btn) {
            home_form.setVisible(false);
            addPatients.setVisible(false);
            availableCourse_form.setVisible(false);
            studentGrade_form.setVisible(true);

            bookedAppointments_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addPatients_btn.setStyle("-fx-background-color:transparent");
            freeAppointments_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");



        }
    }

    

    

     @FXML
    private void FAppointmentShow(ActionEvent event) {
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List <Appointments> free = em.createNamedQuery("Appointments.findFree").getResultList();
        FAppointment_tableView.getItems().setAll(free);
        em.close();
        
        Appointments selectedAppointment = FAppointment_tableView.getSelectionModel().getSelectedItem();
        
        FAppointmentDate_col.setText(selectedAppointment.getAppointmentDate());
        FAppointmentDay_col.setText(selectedAppointment.getAppointmentDay());
        FAppointmentTime_col.setText(selectedAppointment.getAppointmentTime());
        FAppointmentStatus_col1.setText(selectedAppointment.getStatus());
    }
    
    @FXML
    private void FAppointmentAdd(ActionEvent event) {
        Appointments appointment = new Appointments();
        
        
        appointment.setId(null);
        appointment.setAppointmentDate(FAppointmentDate_TF.getText());
        appointment.setAppointmentDay(FAppointmentDay_TF.getText());
        appointment.setAppointmentTime(FAppointmentTime_TF.getText());
        appointment.setStatus("free");
        
        
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void FAppointmentUpdate(ActionEvent event) {
        Appointments selectedAppointment = FAppointment_tableView.getSelectionModel().getSelectedItem();
        
        
        
        if (selectedAppointment != null) {

            EntityManager em = this.emf.createEntityManager(); 
            em.getTransaction().begin();

            try {
                selectedAppointment.setId(null);
                selectedAppointment.setAppointmentDate(FAppointmentDate_TF.getText());
                selectedAppointment.setAppointmentDay(FAppointmentDay_TF.getText());
                selectedAppointment.setAppointmentTime(FAppointmentTime_TF.getText());
                selectedAppointment.setStatus("free");
                
                
                em.merge(selectedAppointment);

                em.getTransaction().commit();
                
            } catch (Exception e) {
                
                e.printStackTrace();
                em.getTransaction().rollback();
            } finally {
                
                em.close();
            }
        }
        
    }

    @FXML
    private void FAppointmentClear(ActionEvent event) {
        patient_id.setText("");
        patient_firstName.setText("");
        patient_lastName.setText("");
        patient_age.setText("");
        patient_phone.setText("");
        patient_email.setText("");
        patients_tableView.getItems().clear();
    }

    @FXML
    private void FAppointmentDelete(ActionEvent event) {
        Appointments selectedAppointment = FAppointment_tableView.getSelectionModel().getSelectedItem();
    
        if (selectedAppointment != null) {
            int appointmentId = selectedAppointment.getId(); 

            EntityManager em = this.emf.createEntityManager(); 

            try {
                em.getTransaction().begin();

                Appointments patientToDelete = em.find(Appointments.class, appointmentId);

                em.remove(patientToDelete);

                em.getTransaction().commit();

                FAppointment_tableView.getItems().remove(selectedAppointment);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        }
    }


    @FXML
    private void BAppointmentShow(ActionEvent event) {
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List <BookedAppointments> appoint = em.createNamedQuery("BookedAppointments.findAll").getResultList();
        BAppointment_tableView.getItems().setAll(appoint);
        em.close();
        
        BookedAppointments selectedAppointment = BAppointment_tableView.getSelectionModel().getSelectedItem();
        
        BAppointmentID_TF.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        BAppointmentUserID_TF.setText(String.valueOf(selectedAppointment.getUserId()));
        BAppointmentDCom_TF1.setText(selectedAppointment.getDoctorCommnet());
    }
    
    @FXML
    private void BAppointmentAdd(ActionEvent event) {
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = this.emf.createEntityManager();
        
        int appointmentId = Integer.parseInt(BAppointmentID_TF.getText());
        int userId = Integer.parseInt(BAppointmentUserID_TF.getText());
        
        BookedAppointments bappointments = new BookedAppointments();
        Appointments appointment = em.find(Appointments.class, appointmentId);
        Users user = em.find(Users.class, userId);
        
        bappointments.setAppointmentId(appointment);
        bappointments.setUserId(user);
        bappointments.setId(null);
        bappointments.setStatus("booked");
        
        
        
        em.getTransaction().begin();
        em.persist(appointment);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void BAppointmentUpdate(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager(); 
        
        BookedAppointments selectedAppointment = BAppointment_tableView.getSelectionModel().getSelectedItem();
        
        int appointmentId = Integer.parseInt(BAppointmentID_TF.getText());
        int userId = Integer.parseInt(BAppointmentUserID_TF.getText());
        
        Appointments appointment = em.find(Appointments.class, appointmentId);
        Users user = em.find(Users.class, userId);
        
        if (selectedAppointment != null) {

            
            em.getTransaction().begin();

            try {
                selectedAppointment.setId(null);
                selectedAppointment.setAppointmentId(appointment);
                selectedAppointment.setUserId(user);
                selectedAppointment.setStatus("booked");
                
                
                em.merge(selectedAppointment);

                em.getTransaction().commit();
                
            } catch (Exception e) {
                
                e.printStackTrace();
                em.getTransaction().rollback();
            } finally {
                
                em.close();
            }
        }
    }

    @FXML
    private void BAppointmentClear(ActionEvent event) {
        BAppointmentID_TF.setText("");
        BAppointmentUserID_TF.setText("");
        BAppointmentDCom_TF1.setText("");
    }

    @FXML
    private void BAppointmentDelete(ActionEvent event) {
        BookedAppointments selectedAppointment = BAppointment_tableView.getSelectionModel().getSelectedItem();
    
        if (selectedAppointment != null) {
            int appointmentId = selectedAppointment.getId(); 

            EntityManager em = this.emf.createEntityManager(); 

            try {
                em.getTransaction().begin();

                BookedAppointments appointmentToDelete = em.find(BookedAppointments.class, appointmentId);

                em.remove(appointmentToDelete);

                em.getTransaction().commit();

                BAppointment_tableView.getItems().remove(selectedAppointment);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
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

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Scene 1 (Patient)
        patientID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        patientFirstName_col.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        patientLastName_col.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        patientAge_col.setCellValueFactory(new PropertyValueFactory<>("age"));
        patientEmail_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        patientPhone_col.setCellValueFactory(new PropertyValueFactory<>("phone"));
        patientetGender_col.setCellValueFactory(new PropertyValueFactory<>("gender"));

        // Scene 2 (FAppointment)
        FAppointmentDate_col.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        FAppointmentDay_col.setCellValueFactory(new PropertyValueFactory<>("appointment_day"));
        FAppointmentTime_col.setCellValueFactory(new PropertyValueFactory<>("appointment_time"));
        FAppointmentStatus_col1.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Scene 3 (BAppointment)
        BAppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
        BAppointmentUserID_col.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        BAppointmentStatus_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        BAppointmentDCom_col1.setCellValueFactory(new PropertyValueFactory<>("doctor_comment"));

        // Common initialization
        displayUsername();
        defaultNav();
    }

    


}
