/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Users;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author WINDOWS 10
 */
public class Patient_loginController implements Initializable {


    
    @FXML
    private Button login_patientLogin;
    @FXML
    private Button login_createAccount;
    @FXML
    private TextField patientUsernameTF;
    @FXML
    private PasswordField patientPasswordTF;
    @FXML
    private Button patient_loginBtn;

    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }


    

    @FXML
    private void patientLogin(ActionEvent event) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        String username = patientUsernameTF.getText();
        String password = patientPasswordTF.getText();

        TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class);
        query.setParameter("username", username);
        Users user = query.getSingleResult();

        if (user != null && user.getPassword().equals(password) && user.getRole().equals("patient")) {
            
            ViewManager.closePatientLoginPage();
            ViewManager.openPatientDashboardPage();
            
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
    private void OpenAdminLoginPage(ActionEvent event) throws IOException {
        ViewManager.closePatientLoginPage();
        ViewManager.openAdminLoginPage();
    }
    
    @FXML
    private void OpenPatientRegisterPage(ActionEvent event) throws IOException {
        ViewManager.closePatientLoginPage();
        ViewManager.openPatientRegisterPage();
    }

   
    

}


// THATS IT FOR THIS VIDEO, THANKS FOR WATCHING !!! : ) 