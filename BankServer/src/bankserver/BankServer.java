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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BankServer {
    static Scanner user = new Scanner(System.in);
    static boolean running = true;
    
    public static void main(String[] args) throws IOException {
        
        //last assignment
        BankAccount account = new BankAccount();
        final double AMOUNT = 50000;
        final double WITHDRAW_AMOUNT = 5000;
        final int REPETITIONS = 10;
        final int THREADS = 10;

        for (int i = 1; i <= THREADS; i++) {
           DepositRunnable d = new DepositRunnable(
              account, AMOUNT, REPETITIONS);
           WithdrawRunnable w = new WithdrawRunnable(
              account, WITHDRAW_AMOUNT, REPETITIONS);

           Thread dt = new Thread(d);
           Thread wt = new Thread(w);

           dt.start();
           wt.start();
        }
        
        //end last assignment
        
        final int ACCOUNTS_LENGTH = 10;
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        
        final int ADMIN_PORT = 8889;
        Admin admin = new Admin();
        
        ServerSocket serverClient = new ServerSocket(SBAP_PORT);
        ServerSocket serverAdmin = new ServerSocket(ADMIN_PORT);        
        
        System.out.println("Waiting for clients/admin to connect . . . ");
        
        while(running) { 
            
            Socket s = serverClient.accept();
            BankService service = new BankService(s, bank);            
            Thread t = new Thread(service);
            admin.incrementClients();
            t.start();

            Socket a = serverAdmin.accept();
            AdminService adminService = new AdminService(a, admin); 
            Thread t2 = new Thread(adminService);
            t2.start();
            
        }
    }  

}