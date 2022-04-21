package logic_With_persistence;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Details.AccountDetails;
import Details.Customer;
import newexception.MistakeOccuredException;
import util.HelperUtil;

public class Cache 
{
	Map<Integer,Customer> customer=new HashMap<>();

	Map<Integer,Map<Long,AccountDetails>> account=new HashMap();
	

	public void putCustomer(int id,Customer obj)
	{
	     
		customer.put(id, obj); 
	     
	    System.out.println(customer);

	}

	public void putAccount(int id,long accNo,AccountDetails obj)
	{
	     
		Map<Long,AccountDetails> details=account.get(id); 
	     
		if(details==null)
	    
		{
	    	 details=new HashMap();
	    
	    	 account.put(id,details);
	     
		}
	     details.put(accNo, obj);
	    
	     System.out.println(account);

	}
	
	public Map<Integer,Customer> getAllCustomer()
	{
		
		return customer;
	
	}
	
	public Map<Integer,Map<Long,AccountDetails>> getAllAccount()
	{
		
		return account;
	
	}

	public void preload(Map<Integer,Customer> obj)
	{
		
		customer.putAll(obj);

	}

	public void preloadAccount(Map<Integer,Map<Long,AccountDetails>> map)
	{
		
		account.putAll(map);
		
	}
	public Customer getDetails(int id)
	{
		Customer details=customer.get(id);
		
		return details;
	}

	public Map<Long,AccountDetails> getAccount(int id)
	{
		
		Map<Long, AccountDetails> accountsBelongtoCus = account.get(id);
		
		return accountsBelongtoCus;
		
	}
	
	public AccountDetails getAccount(int id,long accNo)
	{
		
		Map<Long, AccountDetails> accountsBelongtoCus = account.get(id);
		
		AccountDetails accDetails=accountsBelongtoCus.get(accNo);
		
		return accDetails;
	}
	

	public void setCustomerStatus(int id,int status)
	{
		Customer details=customer.get(id);
		
		if(status==1)
		{
			details.setStatus(true);
		}
		
		else if(status==0)
		{
			details.setStatus(false);
		}
		
		System.out.println(details);
	}

	public boolean getCustomerStatus(int id) throws MistakeOccuredException
	{
		
		Customer details=customer.get(id);
		
		HelperUtil.objectCheck(details, "Customer Details ");
		
		return details.isStatus();

	}

	public void setAccountStatus(int id,long accNo,int status) throws MistakeOccuredException
	{
		Map<Long,AccountDetails> accMap=account.get(id);
		
		HelperUtil.objectCheck(accMap, "AccountDetails Map ");
		
		AccountDetails details=accMap.get(accNo);
		
		HelperUtil.objectCheck(details, "Account details ");
		
		if(status==1)
		{
			details.setStatus(true);
		}
		else if(status==0)
		{
			details.setStatus(false);
		}
		
		System.out.println(details);
	}

	public boolean getAccountStatus(int id,long accNo) throws MistakeOccuredException
	{
	    Map<Long,AccountDetails> accMap=account.get(id);
		
		AccountDetails details=accMap.get(accNo);
		
		HelperUtil.objectCheck(details, "Account details ");
		
		return details.isStatus();
	}


	public void deposit(int id,long accNo,double amount) throws MistakeOccuredException
	{
		Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
		
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		double balance = accInfo.getBalance();
		
		double newBalance = balance + amount;
		
		accInfo.setBalance(newBalance);
	}

	public void withDraw(int id,long accNo,double amount) throws MistakeOccuredException 
	{
		Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
		
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		double balance = accInfo.getBalance();
		
		if (balance < amount) {
		
			throw new MistakeOccuredException("Insufficient balance.");
		}
		
		double newBalance = balance - amount;
		
		accInfo.setBalance(newBalance);
	}

	public double getBalance(int id,long accNo) throws MistakeOccuredException
	{
	    Map<Long, AccountDetails> accDetailsMap = account.get(id);
		
	    HelperUtil.objectCheck(accDetailsMap, "AccountDetails Map ");
	    
		AccountDetails accInfo = accDetailsMap.get(accNo);
		
		HelperUtil.objectCheck(accInfo, "Account details ");
		
		return accInfo.getBalance();
	}
	
	public List<Integer> getAllActiveCustomerId()
	{
		List customerId=new ArrayList();
		Set<Integer> customerIdList=customer.keySet();
		for(Integer id:customerIdList)
		{
			if(customer.get(id).isStatus()==true)
			{
				customerId.add(id);
			}
		}
		return customerId;
	}
	
	public Map<Integer,Customer> getAllactiveCustomer()
	{
		Map<Integer,Customer> activeAccount=new HashMap<>();
		Set<Integer> customerIdList=customer.keySet();
		for(Integer id:customerIdList)
		{
			if(customer.get(id).isStatus()==true)
			{
				activeAccount.put(id,customer.get(id));
			}
		}
		return activeAccount;
	}
	
	public Map<Integer,Customer> getAllInactiveCustomer()
	{
		Map<Integer,Customer> inactiveAccount=new HashMap<>();
		Set<Integer> customerIdList=customer.keySet();
		for(Integer id:customerIdList)
		{
			if(customer.get(id).isStatus()==false)
			{
				inactiveAccount.put(id,customer.get(id));
			}
		}
		return inactiveAccount;
	}
	
	public int accountCount()
	{
		List<Long> accountId=new ArrayList<>();
		Set<Integer> customerId=account.keySet();
		for(Integer id:customerId)
		{
			for(Object accNo:account.get(id).keySet())
			{
				accountId.add((long) accNo);
			}
		}
		return accountId.size();
	}
	
	public int activeAccountCount()
	{
		List<Long> accountId=new ArrayList<>();
		Set<Integer> customerId=account.keySet();
		for(Integer id:customerId)
		{
			for(Object accNo:account.get(id).keySet())
			{
				if(account.get(id).get(accNo).isStatus()==true)
				{
					accountId.add((long)accNo);
				}
			}
		}
		return accountId.size();
	}
	public int customerCount()
	{
		List<Integer> customerId=new ArrayList<>();
		Set<Integer> idSet=customer.keySet();
		for(Integer id:idSet)
		{
			customerId.add(id);
		}
		return customerId.size();
	}
}

