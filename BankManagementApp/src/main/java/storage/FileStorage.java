package storage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Details.AccountDetails;
import Details.Customer;
import logic_With_persistence.Persistence;
import newexception.MistakeOccuredException;
import util.HelperUtil;
public class FileStorage implements Persistence
{
		
    
	// method to give access to access active Account Only
		public void accountAccess(AccountDetails object) throws MistakeOccuredException 
		{
			if (object.isStatus() == false) 
			{
				throw new MistakeOccuredException("This Account is deactivated please Contact branch.");
			}
		}

		
		// method to check customer id's value is exist or not Exist in customer map
		public void idCheck(Map customer,int id) throws MistakeOccuredException
		{
			if (customer.get(id) == null) 
			{
				
				throw new MistakeOccuredException("Entered Key is not exist.");
			
			}
		}
	public int cusId=0;
	
	public long accNo=0;
	
	public int generateCusId()
	{
		return ++cusId;
	}
	
	public long generateAccNo()
	{
		return ++accNo;
	}
	
		public Map<Integer, Map<Long, AccountDetails>> addAccountToCusId(int id,
				AccountDetails accObj) throws MistakeOccuredException {
			
			HelperUtil.numberCheck(id);
			
			HelperUtil.objectCheck(accObj);
			
//			idCheck(id);
			
			Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
			
			Map<Long, AccountDetails> getExisting = accountMap.get(id);
			
			if (getExisting == null)
			{
				getExisting = new HashMap<>();
				
				accountMap.put(id, getExisting);
			}
			accObj.setAccountNumber(generateAccNo());
			
			getExisting.put(accObj.getAccountNumber(), accObj);
			
			return accountMap;
		}
	
		public int addCustomer(Customer obj)throws MistakeOccuredException
	    {
			Map<Integer,Customer> customer=getCustomer();
			
		try(FileOutputStream fos=new FileOutputStream("Customer");
				
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
			
			
			obj.setCustomerId(generateCusId());
			
			customer.put(obj.getCustomerId(),obj);
			
			oos.writeObject(customer);
			
			oos.writeObject(obj.getCustomerId());
			
			return obj.getCustomerId();
		
		}
		catch(IOException ex)
		{
			
			throw new MistakeOccuredException(ex);
		
		}
	}
	
		public void updateCustomerStatus(int id,int status)throws MistakeOccuredException
	    {
			Map<Integer,Customer> customer=getCustomer();
			
		try(FileOutputStream fos=new FileOutputStream("Customer");
				
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
			
			HelperUtil.numberCheck(id);
			
			
			Customer details=customer.get(id);
			
			HelperUtil.objectCheck(details, "Customer Details ");
			if(status==1)
			{
			
				details.setStatus(true);
				oos.writeObject(customer);
			
			}
			else if(status==0)
			{
			
				details.setStatus(false);
				oos.writeObject(customer);
			}
			
			
			
			oos.writeObject(cusId);
			
			
		
		}
		catch(IOException ex)
		{
			
			throw new MistakeOccuredException(ex);
		
		}
	}
		
	public long addAccount(int id,AccountDetails obj)throws MistakeOccuredException
	{
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
		try(FileOutputStream fos=new FileOutputStream("Account");
		
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
	        HelperUtil.numberCheck(id);
			
			HelperUtil.objectCheck(obj);
			
//			idCheck(id);
			
			
			
			Map<Long, AccountDetails> getExisting = accountMap.get(id);
			
			if (getExisting == null)
			{
				getExisting = new HashMap<>();
				
				accountMap.put(id, getExisting);
			}
			obj.setAccountNumber(generateAccNo());
			
			getExisting.put(obj.getAccountNumber(), obj);
			System.out.println(accountMap);
			
			oos.writeObject(accountMap);
			
			oos.writeObject(obj.getAccountNumber());
		
			return obj.getAccountNumber();
		}
		catch(IOException ex)
		{
			throw new MistakeOccuredException(ex);
		}
	}
	
	public void updateAccountStatus(int id,long accountNum,int status)throws MistakeOccuredException
    {
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
	try(FileOutputStream fos=new FileOutputStream("Account");
			
			ObjectOutputStream oos=new ObjectOutputStream(fos))
	{
		HelperUtil.numberCheck(id);

		HelperUtil.numberCheck(status);
		
		
		
		Map<Long,AccountDetails> accdetailMap=accountMap.get(id);
		HelperUtil.objectCheck(accdetailMap, "AccountMap");
		
        AccountDetails details=accdetailMap.get(accountNum);
		HelperUtil.objectCheck(details, "AccountDetails");
		
		if(status==1)
		{
		
			details.setStatus(true);
			oos.writeObject(accountMap);
		
		}
		else if(status==0)
		{
		
			details.setStatus(false);
			oos.writeObject(accountMap);
		}
		
		else
		{
			throw new MistakeOccuredException("Enter the corresponding Number.");
		}
		
		oos.writeObject(accNo);
		
		
	
	}
	catch(IOException ex)
	{
		
		throw new MistakeOccuredException(ex);
	
	}
}
	
	public void deposit(int id,long accNum,double amount) throws MistakeOccuredException
	{
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
//		System.out.println("Dep1:"+accountMap);
		
		try(FileOutputStream fos=new FileOutputStream("Account");
				
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
			HelperUtil.numberCheck(id);
			
			
			
			Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
			
//			System.out.println("dep2"+accDetailsMap);
			HelperUtil.objectCheck(accDetailsMap, "Customer account");
			
			AccountDetails accInfo = accDetailsMap.get(accNum);
			
			HelperUtil.objectCheck(accInfo, "AccountDetails");
			
			accountAccess(accInfo);
			
			double balance = accInfo.getBalance();
			
			double newBalance = balance + amount;
			
			accInfo.setBalance(newBalance);
			
		
			oos.writeObject(accountMap);
			
			oos.writeObject(accNo);
			
		}
		catch(IOException ex)
		{
			
			throw new MistakeOccuredException(ex);
		
		}	
		
	}
	
	
	public void withDraw(int id,long accNum,double amount) throws MistakeOccuredException
	{
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
		try(FileOutputStream fos=new FileOutputStream("Account");
				
				ObjectOutputStream oos=new ObjectOutputStream(fos))
		{
			HelperUtil.numberCheck(id);
			
			
			
			Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
			
			HelperUtil.objectCheck(accDetailsMap, "CustomerId");
			
			AccountDetails accInfo = accDetailsMap.get(accNum);
			
			HelperUtil.objectCheck(accInfo, "AccountDetails");
			
			accountAccess(accInfo);
			
			double balance = accInfo.getBalance();
			
			if (balance < amount) {
			
				throw new MistakeOccuredException("Insufficient balance.");
			}
			
			double newBalance = balance - amount;
			
			accInfo.setBalance(newBalance);
			
			oos.writeObject(accountMap);
			
			oos.writeObject(accNo);
			
		}
		catch(IOException ex)
		{
			
			throw new MistakeOccuredException(ex);
		
		}	
		
	}
	
	public Map<Integer,Customer> getCustomer() throws MistakeOccuredException
	{
		 File file=new File("Customer");
		 if(!file.exists())
		 {
			 try(FileOutputStream fos=new FileOutputStream(file);
						
						ObjectOutputStream oos=new ObjectOutputStream(fos))
				{
					
					Map<Integer, Customer > map=new HashMap<>();
					
					oos.writeObject(map);
					
					oos.writeObject(cusId);
			
				}
				catch(IOException ex)
				{
					throw new MistakeOccuredException(ex);
				}
		 }
		 try(FileInputStream fis=new FileInputStream("Customer");
		
				ObjectInputStream ois=new ObjectInputStream(fis))
		{
			
			Map<Integer,Customer> resultant=(Map) ois.readObject();
			
			cusId=(int)ois.readObject();
			
			return resultant;
		
		}
		catch(IOException ex)
		{
		
			throw new MistakeOccuredException(ex);
		
		} 
		catch (ClassNotFoundException e)
		{
			
			throw new MistakeOccuredException(e);
		
		}
	
	}
	
	public Map<Integer, Map<Long,AccountDetails>> getAccount() throws MistakeOccuredException
	{
		 File file1=new File("Account");
		 if(!file1.exists())
		 {
			 try(FileOutputStream fos=new FileOutputStream("Account");
						
						ObjectOutputStream oos=new ObjectOutputStream(fos))
				{
					
					Map<Integer, Map<Long,AccountDetails> >accountDetails=new HashMap<>();
					
					oos.writeObject(accountDetails);
					
					oos.writeObject(accNo);
				
				}
				catch(IOException ex)
				{
					throw new MistakeOccuredException(ex);
				}
		 }
		try(FileInputStream fis=new FileInputStream("Account");
		
				ObjectInputStream ois=new ObjectInputStream(fis))
		{
	        Map<Integer, Map<Long,AccountDetails>> resultant=(Map)ois.readObject();
	        
	        accNo=(long)ois.readObject();
	        
	        return resultant;
	        
		}
		catch(IOException ex)
		{
		
			throw new MistakeOccuredException(ex);
		
		}
		catch (ClassNotFoundException e) 
		{
			
			throw new MistakeOccuredException(e);
		
		}
	}


	@Override
	public void amountTransfer(long fromAccNo, long toAccNo, double amount) throws MistakeOccuredException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateCustomer(int customerId,String name, String dob, String address, long phno)
			throws MistakeOccuredException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int lastRegisteredCustomer() throws MistakeOccuredException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean getRole(String id, String passWord) throws MistakeOccuredException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void updateAccount(int customerId, long accNo, String branch) throws MistakeOccuredException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<String> getBranch() throws MistakeOccuredException {
		// TODO Auto-generated method stub
		return null;
	}


}
