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
public class AdminLogin extends Stage {
    private Scene adminLoginPageScene;
    
    public AdminLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_Login.fxml"));
        Parent root = loader.load();
        adminLoginPageScene = new Scene(root);
        this.setScene(adminLoginPageScene);
        this.setTitle("Admin Login Page");
    }  
    
}
