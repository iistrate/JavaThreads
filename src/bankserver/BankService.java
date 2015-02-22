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
import java.util.ArrayList;
import java.util.Scanner;

public class BankService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Bank bank;
    
    public BankService(Socket aSocket, Bank aBank) {
        s = aSocket;
        bank = aBank;
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
            if (command.equals("QUIT")) {
                out.println("Exiting program.");
                out.flush();
                return;
            }
            else {
                executeCommand(command);
            }
        }
    }
    public void executeCommand(String command) {
        int account = in.nextInt();
        if (command.equals("DEPOSIT")) {
            double amount = in.nextDouble();
            bank.deposit(account, amount);
            out.println(account + " balance is " + bank.getBalance(account));
            out.flush();
        }
        else if (command.equals("WITHDRAW")) {
            double amount = in.nextDouble();
            out.println(bank.withdraw(account, amount));
            out.println(account + " balance is " + bank.getBalance(account));
            out.flush();
        }
        else if (command.equals("BALANCE")) {
            out.println(account + " " + bank.getBalance(account));
            out.flush();
        }
        else {
            out.println("Invalid command!");
            out.flush();   
            return;
        }
    }
}

