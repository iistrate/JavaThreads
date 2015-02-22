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
/**
   A bank consisting of multiple bank accounts.
*/
public class Bank
{
   private BankAccount[] accounts;

   /**
      Constructs a bank account with a given number of accounts.
      @param size the number of accounts
   */
   public Bank(int size)
   {
      accounts = new BankAccount[size];
      for (int i = 0; i < accounts.length; i++)
      {
         accounts[i] = new BankAccount();
      }
   }

   /**
      Deposits money into a bank account.
      @param accountNumber the account number
      @param amount the amount to deposit
   */
   public void deposit(int accountNumber, double amount)
   {
      BankAccount account = accounts[accountNumber];
      account.deposit(amount);
   }

   /**
      Withdraws money from a bank account.
      @param accountNumber the account number
      @param amount the amount to withdraw
      * @return error code
   */
   public String withdraw(int accountNumber, double amount)
   {
        BankAccount account = accounts[accountNumber];
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            return "Code 200 Operation withdrawal succesful.";
        }
        return "Code 450[overdrawn], withdrawal unsuccesful.";
   }

   /**
      Gets the balance of a bank account.
      @param accountNumber the account number
      @return the account balance
   */
   public double getBalance(int accountNumber)
   {
      BankAccount account = accounts[accountNumber];
      return account.getBalance();
   }
}

