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
public class AdminDashboard extends Stage{
    private Scene adminDashboardPageScene;
    
    public AdminDashboard() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_dashboard.fxml"));
        Parent root = loader.load();
        adminDashboardPageScene = new Scene(root);
        this.setScene(adminDashboardPageScene);
        this.setTitle("Admin Login Page");
    }  
}
