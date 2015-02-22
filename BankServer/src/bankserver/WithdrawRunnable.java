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
   A withdraw runnable makes periodic withdrawals from a bank account.
*/
public class WithdrawRunnable implements Runnable
{
   private static final int DELAY = 1; 
   private BankAccount account;
   private double amount;
   private int count;

   /**
      Constructs a withdraw runnable.
      @param anAccount the account from which to withdraw money
      @param anAmount the amount to withdraw in each repetition
      @param aCount the number of repetitions
   */
   public WithdrawRunnable(BankAccount anAccount, double anAmount,
         int aCount)
   {
      account = anAccount;
      amount = anAmount;
      count = aCount;
   }

   public void run()
   {
      try
      {
         for (int i = 1; i <= count; i++)
         {
            account.withdraw(amount);
            Thread.sleep(DELAY);
         }
      }
      catch (InterruptedException exception) {}
   }
}