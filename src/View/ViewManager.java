/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;

/**
 *
 * @author Abdullah
 */
public class ViewManager {
    public static AdminLogin adminLogin;
    public static PatientLogin patientLogin;
    public static PatientRegister patientRegister;
    public static AdminDashboard adminDashboard;
    public static PatientDashboard patientDashboard;
    
    private ViewManager(){}
    
    public static void openAdminLoginPage() throws IOException{
        if(adminLogin == null){
            adminLogin = new AdminLogin();
            adminLogin.show();
        }else{
           adminLogin.show();
        }
    }
    
    public static void closeAdminLoginPage(){
        if(adminLogin!= null){
            adminLogin.close();
        }
    }
    
    public static void openPatientLoginPage() throws IOException{
        if(patientLogin == null){
            patientLogin = new PatientLogin();
            patientLogin.show();
        }else{
           patientLogin.show();
        }
    }
    
    public static void closePatientLoginPage(){
        if(patientLogin!= null){
            patientLogin.close();
        }
    }
    
    public static void openPatientRegisterPage() throws IOException{
        if(patientRegister == null){
            patientRegister = new PatientRegister();
            patientRegister.show();
        }else{
           patientRegister.show();
        }
    }
    
    public static void closePatientRegisterPage(){
        if(patientRegister!= null){
            patientRegister.close();
        }
    }
    
    
    public static void openAdminDashboardPage() throws IOException{
        if(adminDashboard == null){
            adminDashboard = new AdminDashboard();
            adminDashboard.show();
        }else{
           adminDashboard.show();
        }
    }
    
    public static void closeAdminDashboardPage(){
        if(adminDashboard!= null){
            adminDashboard.close();
        }
    }
    
    
    public static void openPatientDashboardPage() throws IOException{
        if(patientDashboard == null){
            patientDashboard = new PatientDashboard();
            patientDashboard.show();
        }else{
           patientDashboard.show();
        }
    }
    
    public static void closePatientDashboardPage(){
        if(patientDashboard!= null){
            patientDashboard.close();
        }
    }
    
}
