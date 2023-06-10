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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Abdullah
 */
public class Patient_registerController implements Initializable {

    @FXML
    private AnchorPane signup_form;
    @FXML
    private TextField signup_email;
    @FXML
    private TextField signup_username;
    @FXML
    private PasswordField signup_password;
    @FXML
    private TextField signup_firstname;
    @FXML
    private TextField signup_phone;
    @FXML
    private TextField signup_lastname;
    @FXML
    private TextField signup_age;
    @FXML
    private Button signup_btn;
    @FXML
    private Button signup_loginAccount;
    @FXML
    private ComboBox<String> signup_selectGender;
    

    
    
    private String[] genderList = {"male" , "female"};
   
    
    
    private void SelectGender(){
        List<String> listQ = new ArrayList<>();

        for (String data : genderList) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectGender.setItems(listData);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SelectGender();
    }  

    @FXML
    private void patientRegister(ActionEvent event) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Final-ProjectPU");
        EntityManager em = emf.createEntityManager();
        String username = signup_username.getText();
        String password = signup_password.getText();
        String firstname = signup_firstname.getText();
        String lastname = signup_lastname.getText();
        String gender = signup_selectGender.getValue();
        String email = signup_email.getText();
        String role = "patient";
        int age = Integer.parseInt(signup_age.getText());
        int phone = Integer.parseInt(signup_phone.getText());

        // Validate input
        if (username.isEmpty() || password.isEmpty() || firstname.isEmpty() || lastname.isEmpty()) {
            showErrorAlert("Please enter username and password.");
            return;
        }

        // Create and persist user entity
        Users user = new Users(null,username, password,  firstname,  lastname,  age,  email,  phone,  gender,  role);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        emf.close();

        // Show success message
        showSuccessAlert("Registration successful!");

        // Clear input fields
//        usernameField.clear();
//        passwordField.clear();
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
        ViewManager.closePatientRegisterPage();
        ViewManager.openAdminLoginPage();
    }
    
}
