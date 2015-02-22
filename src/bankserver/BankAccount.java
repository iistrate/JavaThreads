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
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount
{  
   private double balance;
   private Lock balanceChangeLock;
   private Condition sufficientFundsCondition;
   private Condition overloadFundCondition;
   private int maxBalance;
   /**
      Constructs a bank account with a zero balance.
   */
   public BankAccount()
   {   
      balance = 0;
      balanceChangeLock = new ReentrantLock();
      sufficientFundsCondition = balanceChangeLock.newCondition();
      overloadFundCondition = balanceChangeLock.newCondition();
      maxBalance = 100000;
   }

   /**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
   */
   public BankAccount(double initialBalance)
   {   
      balance = initialBalance;
   }

   /**
      Deposits money into the bank account.
      @param amount the amount to deposit
   */
   public void deposit(double amount) throws InterruptedException {  
      balanceChangeLock.lock();
      try
      {
        while(balance + amount >= maxBalance) {
            System.out.println("Depositing halted, too much money " + balance);
            overloadFundCondition.await();
        }
        System.out.print("Depositing " + amount);
        double newBalance = balance + amount;
        System.out.println(", new balance is " + newBalance);
        balance = newBalance;
        sufficientFundsCondition.signalAll();
      }
      finally
      {
         balanceChangeLock.unlock();
      }
   }

   /**
      Withdraws money from the bank account.
      @param amount the amount to withdraw
   */
   public void withdraw(double amount) throws InterruptedException
   {   
      balanceChangeLock.lock();
      try
      {
        while(balance < amount) {
            sufficientFundsCondition.await();
        }
        System.out.println("Withdrawing " + amount);
        double newBalance = balance - amount;
        System.out.println(" , new balance is " + newBalance);
        balance = newBalance;
        if (newBalance <= maxBalance) {
            System.out.println("Depositing possibly allowed " + balance);
            overloadFundCondition.signalAll();
        }
      }
      finally
      {
         balanceChangeLock.unlock();
      }
   }

   /**
      Gets the current balance of the bank account.
      @return the current balance
   */
   public double getBalance()
   {   
      return balance;
   }
}
