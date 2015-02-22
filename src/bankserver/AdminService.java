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
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class AdminService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Admin admin;
    
    public AdminService(Socket aSocket, Admin anadmin) {
        s = aSocket;
        admin = anadmin;
    }
    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally {
                s.close();
            }
        }
        catch(IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public void doService() {
        while(true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.next();
            if (command.equals("SHUTDOWN")) {
                out.println("Response: client shutdown");
                return;
            }
            else {
                executeCommand(command);
            }
        }
    }
    public void executeCommand(String command) {
        if (command.equals("LOGIN")) {
            String pass = in.next();
            out.println("Response: " + admin.login(pass));
            out.flush();
       }
        else if (command.equals("STATUS")) {
            out.println("Response: client count is " + Integer.toString(admin.getCLientCount()));
            out.flush();
        }
        else if (command.equals("PASSWORD")) {
            String newPass = in.next();
            out.println("Response: " + admin.password(newPass));
            out.flush();
        }
        else if (command.equals("LOGOUT")) {
            admin.logout();
            out.println("Response: Admin logged in is " + Boolean.toString(admin.isLogged()));
            out.flush();
        }
        else {
            out.println("Invalid command!");
            out.flush();   
            return;
        }
        out.flush();
   }
}

