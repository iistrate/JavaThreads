# JavaThreads
Networking assignments

•• P21.3 Modify the BankServer program so that it can be terminated more elegantly. Provide 
another socket on port 8889 through which an administrator can log in. Support 
the commands LOGIN password, STATUS, PASSWORD newPassword, LOGOUT, and SHUTDOWN. The 
STATUS command should display the total number of clients that have logged in since 
the server started.

•• P21.4 Modify the BankServer program to provide complete error checking. For example, 
the program should check to make sure that there is enough money in the account 
when withdraw ing. Send appropriate error reports back to the client. Enhance the 
protocol to be similar to HTTP, in which each server response starts with a number 
indicating the success or failure condition, followed by a string with response data or 
an error description.

•• P20.10 Add a condition to the deposit method of the BankAccount class in Section 20.5,
restricting deposits to $100,000 (the insurance limit of the U.S. government). The
method should block until sufficient money has been withdrawn by another thread.
Test your program with a large number of deposit threads. 
