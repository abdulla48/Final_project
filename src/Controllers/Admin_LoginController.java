/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Users;
import Model.alertMessage;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author WINDOWS 10
 */
public class Admin_LoginController implements Initializable {


    @FXML
    private AnchorPane login_form;

    @FXML
    private TextField login_showPassword;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_patientLogin;
    @FXML
    private Label wrongLabel;
    @FXML
    private TextField adminUsernameTF;
    @FXML
    private PasswordField adminPasswordTF;


    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    
    @FXML
    private void adminLogin(ActionEvent event) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        String username = adminUsernameTF.getText();
        String password = adminPasswordTF.getText();

        TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class);
        query.setParameter("username", username);
        Users user = query.getSingleResult();

        if (user != null && user.getPassword().equals(password) && user.getRole().equals("admin")) {
            String loggedInUsername = adminUsernameTF.getText();
            Admin_dashboardController dashboardController = new Admin_dashboardController();
            dashboardController.setLoggedInUsername(loggedInUsername);
            
            ViewManager.closeAdminLoginPage();
            ViewManager.openAdminDashboardPage();
            
            
        } else {
            // Invalid credentials
            showErrorAlert("Invalid username or password!");
        }
        em.close();
        emf.close();
    }
    
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    
    @FXML
    private void OpenPatientLoginPage(ActionEvent event) throws IOException {
        ViewManager.closeAdminLoginPage();
        ViewManager.openPatientLoginPage();
    }

   

}


// THATS IT FOR THIS VIDEO, THANKS FOR WATCHING !!! : ) 