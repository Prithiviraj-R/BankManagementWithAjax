package Details;

import java.io.Serializable;

public class AccountDetails implements Serializable
{
private int customerId; 
private long accountNumber;
private String branch;
private boolean status=true;
private double balance=0;

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

public int getCustomerId() {
	return customerId;
}

public void setCustomerId(int customerId)
{
	this.customerId = customerId;
}

public long getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}

public String getBranch() {
	return branch;
}

public void setBranch(String branch) {
	this.branch = branch;
}

public String toString()
{
	
		return "Account Number:"+accountNumber+","+"Status:"+status+","+"branch:"+branch+","+"Balance:"+balance+".";
	
}
}
