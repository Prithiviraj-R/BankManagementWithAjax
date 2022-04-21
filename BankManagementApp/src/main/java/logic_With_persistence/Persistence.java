package logic_With_persistence;

import java.util.List;
import java.util.Map;
import Details.AccountDetails;
import Details.Customer;
import newexception.MistakeOccuredException;

public interface Persistence
{
	public int addCustomer(Customer cusObj) throws MistakeOccuredException;
	
	public long addAccount(int id,AccountDetails obj)throws MistakeOccuredException;
	
	public void updateCustomerStatus(int id,int status)throws MistakeOccuredException;
	
	public void updateAccountStatus(int id,long accountNum,int status)throws MistakeOccuredException;
	
	public void deposit(int id,long accNum,double amount) throws MistakeOccuredException;
	
	public void withDraw(int id,long accNum,double amount) throws MistakeOccuredException;
	
	public Map<Integer,Customer> getCustomer() throws MistakeOccuredException;
	
	public Map<Integer, Map<Long,AccountDetails>> getAccount() throws MistakeOccuredException;
	
	public void amountTransfer(long fromAccNo,long toAccNo,double amount)throws MistakeOccuredException;

	public void updateCustomer(int customerId,String name,String dob,String address,long phno) throws MistakeOccuredException;

	public int lastRegisteredCustomer() throws MistakeOccuredException;
	
	public boolean getRole(String id,String passWord) throws MistakeOccuredException;
	
	public void updateAccount(int customerId,long accNo,String branch) throws MistakeOccuredException;
	
	public List<String> getBranch() throws MistakeOccuredException;
}
