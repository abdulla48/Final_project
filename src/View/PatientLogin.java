/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah
 */
public class PatientLogin extends Stage {
    private Scene patientLoginPageScene;
    
    public PatientLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patient_login.fxml"));
        Parent root = loader.load();
        patientLoginPageScene = new Scene(root);
        this.setScene(patientLoginPageScene);
        this.setTitle("patient Login Page");
    }  
    
}
