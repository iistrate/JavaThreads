/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankserver;

/**
 *
 * @author Ioan
 */
public class Admin {
    private String pass;
    private int clients;
    boolean loggedIn;
    
    Admin() {
        pass = "pass";
        clients = 0;
        loggedIn = false;
    }
    
    public boolean isLogged() {
        return loggedIn;
    } 
    
    public String login(String p) {
        if (p.equals(pass)) {
            loggedIn = true;
            return "Logged in";
        }
        return "Incorrect password";
    }
    public void incrementClients() {
        clients++;
    }
    public int getCLientCount() {
        return clients;
    }
    public String password(String newPass) {
        pass = newPass;
        return ("New password is " + newPass);
    }
    public void logout() {
        System.out.println("Logged out!");        
        loggedIn = false;
    }
}
