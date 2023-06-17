
package Controllers;

import Model.Appointments;
import Model.AppointmentsJpaController;
import Model.BookedAppointments;
import Model.BookedAppointmentsJpaController;
import Model.Users;
import Model.exceptions.NonexistentEntityException;
import View.ViewManager;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Patient_dashboardController implements Initializable {
    

    private Users userLogin;
    
    EntityManagerFactory emf =Persistence.createEntityManagerFactory("Final-ProjectPU");
    AppointmentsJpaController ajc = new AppointmentsJpaController(emf);
    BookedAppointmentsJpaController bjc = new BookedAppointmentsJpaController(emf);
     
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
    private Button FAppointments_btn;
    @FXML
    private Button BWAppointments_btn;
    @FXML
    private Button BFAppointments_btn;
    
    
    @FXML
    private AnchorPane free_appointments;
    @FXML
    private TableView<Appointments> freeAppointments_tableView;
    @FXML
    private TableColumn<Appointments, String> FAppointmentDate_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentDay_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentTime_col;
    @FXML
    private TableColumn<Appointments, Integer> FAppointmentID_col;
    @FXML
    private TableColumn<Appointments, String> FAppointmentStatus_col;
    @FXML
    private Button FAppointment_bookBtn;
    @FXML
    private AnchorPane BF_appointments;
    @FXML
    private TableView<BookedAppointments> BWAppointment_tableView;
    @FXML
    private TableColumn<BookedAppointments, Integer> BWAppointmentID_col;
    @FXML
    private TableColumn<BookedAppointments, String> AppointmentID_col;
    @FXML
    private TableColumn<BookedAppointments, String> BWAppointmentUserID_col;
    @FXML
    private TableColumn<BookedAppointments, String> BWAppointmentStatus_col;
    @FXML
    private TableColumn<BookedAppointments, String> BWppointmentDC_col;
    
    @FXML
    private TableView<BookedAppointments> BFAppointment_tableView;
    @FXML
    private TableColumn<BookedAppointments, Integer> BFAppointmentID_col;
    @FXML
    private TableColumn<BookedAppointments, String> AppointmentID_col2;
    @FXML
    private TableColumn<BookedAppointments, String> BFAppointmentUserID_col;
    @FXML
    private TableColumn<BookedAppointments, String> BFAppointmentStatus_col;
    @FXML
    private TableColumn<BookedAppointments, String> BFppointmentDC_col;
    @FXML
    private AnchorPane BW_appointments;
    

    
 
    
    //********************************************************************************************

    public void FreeAppointmentsShowTableView(){
        
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List <Appointments> free = em.createNamedQuery("Appointments.findFree").getResultList();
        freeAppointments_tableView.getItems().setAll(free);
        em.close();
        
        Appointments selectedAppointment = freeAppointments_tableView.getSelectionModel().getSelectedItem();
        FAppointmentDate_col.setText(selectedAppointment.getAppointmentDate());
        FAppointmentDay_col.setText(selectedAppointment.getAppointmentDay());
        FAppointmentTime_col.setText(selectedAppointment.getAppointmentTime());
        FAppointmentID_col.setText(String.valueOf(selectedAppointment.getId()));
        FAppointmentStatus_col.setText(selectedAppointment.getStatus());
        
    }
    
    
    public void BWAppointmentsShowTableView(){
        
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List<BookedAppointments> waiting = em.createNamedQuery("BookedAppointments.findWaiting").getResultList();

        BWAppointment_tableView.getItems().setAll(waiting);
        
//        List<BookedAppointments> filteredResults = new ArrayList<>();
//        
//        for (BookedAppointments appointment : waiting) {
//            if (appointment.getUserId().equals(userLogin.getId())) {
//                filteredResults.add(appointment);
//            }
//        }
//
//        BWAppointment_tableView.getItems().setAll(filteredResults);

        
        em.close();
        
        BookedAppointments selectedAppointment = BWAppointment_tableView.getSelectionModel().getSelectedItem();
        BWAppointmentID_col.setText(String.valueOf(selectedAppointment.getId()));
        AppointmentID_col.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        BWAppointmentUserID_col.setText(String.valueOf(selectedAppointment.getUserId()));
        BWAppointmentStatus_col.setText(String.valueOf(selectedAppointment.getStatus()));
        BWppointmentDC_col.setText(selectedAppointment.getDoctorCommnet());
        
    }
    
    
    public void BFAppointmentsShowTableView(){
        
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        List<BookedAppointments> finished = em.createNamedQuery("BookedAppointments.findFinished").getResultList();

        BWAppointment_tableView.getItems().setAll(finished);
        
        List<BookedAppointments> filteredResults = new ArrayList<>();
        
        for (BookedAppointments appointment : finished) {
            if (appointment.getUserId().equals(userLogin.getId())) {
                filteredResults.add(appointment);
            }
        }

        BWAppointment_tableView.getItems().setAll(filteredResults);
        
        em.close();
        
        BookedAppointments selectedAppointment = BFAppointment_tableView.getSelectionModel().getSelectedItem();
        BFAppointmentID_col.setText(String.valueOf(selectedAppointment.getId()));
        AppointmentID_col2.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        BFAppointmentUserID_col.setText(String.valueOf(selectedAppointment.getUserId()));
        BFAppointmentStatus_col.setText(String.valueOf(selectedAppointment.getStatus()));
        BFppointmentDC_col.setText(selectedAppointment.getDoctorCommnet());
        
    }
    
    
    @FXML
    private void Book_Appointment(ActionEvent event) throws NonexistentEntityException, Exception {
        emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        
        Appointments selectedAppointment = freeAppointments_tableView.getSelectionModel().getSelectedItem();
        
        if(selectedAppointment != null){
            selectedAppointment.setStatus("booked");
            BookedAppointments booked = new BookedAppointments();
            booked.setAppointmentId(selectedAppointment);
            booked.setUserId(this.userLogin);
            booked.setStatus("waiting");
            ajc.edit(selectedAppointment);
            bjc.create(booked);
        }
        em.close();
        
    }

    

    

    
    //********************************************************************************************
    


    @FXML
    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                ViewManager.closePatientDashboardPage();
                ViewManager.openPatientLoginPage();

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
            free_appointments.setVisible(false);
            BW_appointments.setVisible(false);
            BF_appointments.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            FAppointments_btn.setStyle("-fx-background-color:transparent");
            BWAppointments_btn.setStyle("-fx-background-color:transparent");
            BFAppointments_btn.setStyle("-fx-background-color:transparent");


        } else if (event.getSource() == FAppointments_btn) {
            home_form.setVisible(false);
            free_appointments.setVisible(true);
            BW_appointments.setVisible(false);
            BF_appointments.setVisible(false);

            FAppointments_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            BWAppointments_btn.setStyle("-fx-background-color:transparent");
            BFAppointments_btn.setStyle("-fx-background-color:transparent");

            FreeAppointmentsShowTableView();

        } else if (event.getSource() == BWAppointments_btn) {
            home_form.setVisible(false);
            free_appointments.setVisible(false);
            BW_appointments.setVisible(true);
            BF_appointments.setVisible(false);

            BWAppointments_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            FAppointments_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            BFAppointments_btn.setStyle("-fx-background-color:transparent");

            BWAppointmentsShowTableView();

        } else if (event.getSource() == BFAppointments_btn) {
            home_form.setVisible(false);
            free_appointments.setVisible(false);
            BW_appointments.setVisible(false);
            BF_appointments.setVisible(true);

            BFAppointments_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            FAppointments_btn.setStyle("-fx-background-color:transparent");
            BWAppointments_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

            BFAppointmentsShowTableView();

        }
    }

   

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        this.userLogin = Controllers.Patient_loginController.userLogin;
        this.username.setText(userLogin.getFirstname());
        
        FAppointmentDate_col.setCellValueFactory(new PropertyValueFactory<>("appointment_date"));
        FAppointmentDay_col.setCellValueFactory(new PropertyValueFactory<>("appointment_day"));
        FAppointmentTime_col.setCellValueFactory(new PropertyValueFactory<>("appointment_time"));
        FAppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        FAppointmentStatus_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        BWAppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        AppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("appointment_id "));
        BWAppointmentUserID_col.setCellValueFactory(new PropertyValueFactory<>("user_id "));
        BWAppointmentStatus_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        BWppointmentDC_col.setCellValueFactory(new PropertyValueFactory<>("doctor_commnet"));
        
        
        BWAppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        AppointmentID_col.setCellValueFactory(new PropertyValueFactory<>("appointment_id "));
        BWAppointmentUserID_col.setCellValueFactory(new PropertyValueFactory<>("user_id "));
        BWAppointmentStatus_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        BWppointmentDC_col.setCellValueFactory(new PropertyValueFactory<>("doctor_commnet"));
        
        
        defaultNav();
    }

    




}
