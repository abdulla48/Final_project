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
public class PatientRegister extends Stage {
    private Scene patientRegisterPageScene;
    
    public PatientRegister() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("patient_register.fxml"));
        Parent root = loader.load();
        patientRegisterPageScene = new Scene(root);
        this.setScene(patientRegisterPageScene);
        this.setTitle("patient Register Page");
    }  
    
}
