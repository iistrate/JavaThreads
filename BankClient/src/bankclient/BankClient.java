/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankclient;

/**
 *
 * @author Ioan
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BankClient {
    public static void main(String args[]) throws IOException {
        final int SBAP_PORT = 8888;
        final int ADMIN_PORT = 8889;
        
        //Testing Client
        Socket s = new Socket("localhost", SBAP_PORT);
        
        InputStream instream = s.getInputStream();
        OutputStream outstream = s.getOutputStream();
        
        Scanner in = new Scanner(instream);
        PrintWriter out = new PrintWriter(outstream);
        
        String command = "DEPOSIT 3 1000\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
     
        String response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "WITHDRAW 3 9900\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "WITHDRAW 3 500\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "QUIT\n";
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();

        response = in.nextLine();
        System.out.println("Receiving: " + response);

        s.close();

        System.out.println("\nTesting Admin");
  
        //Testing Admin
        s = new Socket("localhost", ADMIN_PORT);
        
        instream = s.getInputStream();
        outstream = s.getOutputStream();
        
        in = new Scanner(instream);
        out = new PrintWriter(outstream);

        command = "LOGIN pass\n"; 
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        response = in.nextLine();
        System.out.println("Receiving: " + response);

        command = "PASSWORD newPass\n"; 
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        response = in.nextLine();
        System.out.println("Receiving: " + response);
        
        command = "STATUS\n"; 
        System.out.print("Sending: " + command);
        out.print(command);
        out.flush();
        
        response = in.nextLine();
        System.out.println("Receiving: " + response);

        command = "SHUTDOWN\n"; 
        System.out.print("Sending: " + command);
        out.print(command);           
        out.flush();          
        
        s.close();
    }
}
