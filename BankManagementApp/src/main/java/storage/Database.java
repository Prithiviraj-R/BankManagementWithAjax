package storage;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Details.AccountDetails;
import Details.Customer;
import logic_With_persistence.Persistence;
import newexception.MistakeOccuredException;
import util.HelperUtil;
public class Database implements Persistence
{
	String url="jdbc:mysql://localhost:3306/bank";
	String uName="root";
	String pass="1234";
	private long accountId;
	private int customerId;
	
	public void idCheck(int id) throws MistakeOccuredException
	{
		Map<Integer,Customer> customerMap=getCustomer();
		if (customerMap.get(id) == null) 
		{
			throw new MistakeOccuredException("Entered Key is not exist.");
		}
	}
	
	public void accountAccess(AccountDetails accDetails) throws MistakeOccuredException
	{
	    if(accDetails.isStatus()==false)
	    {
	    	throw new MistakeOccuredException("Account Inactive please contact branch.");
	    }
	}
	
	public void createTable() throws MistakeOccuredException 
	{
		try(Connection con=DriverManager.getConnection(url, uName, pass);
		Statement st=con.createStatement();)
		{
			String query1="create table if not exists customerInfo(customerID int not null auto_increment,name varchar(25),dob varchar(10),address varchar(25),mobileNo bigint,status tinyint,primary key(customerID));";
		    String query2="create table if not exists accountInfo(accountID int not null auto_increment,customerID int not null,branchName varchar(20),balance int,status tinyint,primary key(accountID),foreign key(customerID) references customerInfo(customerID),foreign key(branchName) references branch(Branch));";
		    int count1=st.executeUpdate(query1);		  
			int count2=st.executeUpdate(query2);
		}
		catch(SQLException e) 
		{
			throw new MistakeOccuredException(e);
		}
	}
     

	public int insertTable(String query)throws MistakeOccuredException
	{
		int count;
		try(Connection con=DriverManager.getConnection(url, uName, pass);
    			Statement st=con.createStatement();)
		{
			st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);
			return id;
		}
		catch(SQLException e) 
		{
			throw new MistakeOccuredException("Performed Action is failed");
		}
	}
	
	public void userMaintainance(int id)throws MistakeOccuredException
	{
		System.out.println("Line 1");
		String selectQuery="select * from customerInfo where customerID="+id+";";
		System.out.println("Line 2");
		try(Connection con=DriverManager.getConnection(url, uName, pass);
    			Statement st=con.createStatement();)
		{
			 ResultSet rs=st.executeQuery(selectQuery);
			 System.out.println("Line 3"+rs);
			 rs.next();
			 String name=rs.getString("Name");
			 System.out.println("line 4"+name);
			 String dob=rs.getString("dob");
			 System.out.println("line 5:dob");
			 System.out.println("password faild");
			 st.executeUpdate("insert into info(UserName,password,role,customerId) values('"+String.valueOf(id)+"','"+name.substring(0,2)+dob.substring(4,dob.length()-1)+"',"+0+","+id+")");
	         System.out.println("Process done");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new MistakeOccuredException(e);
		}
	}
	public long insertTableAcc(String query)throws MistakeOccuredException
	{
		try(Connection con=DriverManager.getConnection(url, uName, pass);
    			Statement st=con.createStatement();)
		{
			st.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet rs=st.getGeneratedKeys();
			rs.next();
			long id=rs.getInt(1);
			return id;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("I am From Database");
			throw new MistakeOccuredException(e);
		}
	}
	public int addCustomer(Customer cusObj)throws MistakeOccuredException
	{
		String name=cusObj.getName();
		String dob=cusObj.getDob();
		String address=cusObj.getAddress();
		boolean status=cusObj.isStatus();
		long mobile=cusObj.getPhoneNumber();
		int add=insertTable("insert into customerInfo(name,dob,address,status,mobileNo) values('"+ name +"','"+dob+"','"+ address +"',"+status+","+mobile+");");
//		userMaintainance(add);
		return add;
	}
	
	public long addAccount(int id,AccountDetails accObj)throws MistakeOccuredException
	{
		idCheck(id);
		String branch=accObj.getBranch();
		int customerId=accObj.getCustomerId();
		double balance=accObj.getBalance();
		boolean status=accObj.isStatus();
		long add=insertTableAcc("insert into accountInfo(customerId,branchName,balance,status) values('"+customerId+"','"+branch+"',"+ balance +","+status+");");
		return add;
	}
	
	public int updateRecord(String query) throws MistakeOccuredException
	{
		
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				Statement statement=con.createStatement();)
		{
			int number=statement.executeUpdate(query);
			System.out.println(number);
			return number;
		}
		catch (SQLException e) 
		{
			throw new MistakeOccuredException("Performed Action is failed");
		}
	}
	
	public void updateCustomerStatus(int id,int status)throws MistakeOccuredException
	{
         updateRecord("update customerInfo set status="+ status +" where customerID ="+ id +";");
         System.out.println("Sucessfully Updated.");
	}
	
	public void updateAccountStatus(int id,long accNo,int status)throws MistakeOccuredException
	{
		updateRecord("update accountInfo set status="+ status +" where accountId = "+ accNo +" and "+"customerId="+ id +";");
		System.out.println("Sucessfully updated.");
	}


	@Override
	public void deposit(int id, long accNum, double amount) throws MistakeOccuredException
	{
		idCheck(id);
		
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
		Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "Customer ID is not belong to AccountID");
		
		AccountDetails accInfo = accDetailsMap.get(accNum);
		
		accountAccess(accInfo);

		HelperUtil.objectCheck(accInfo, "Account id is not belong for this customer");
		
		double newBalance=accInfo.getBalance()+amount;
		
		updateRecord("update accountInfo set balance ="+newBalance+" where accountId ="+accNum +" and "+" customerId="+id+";");
		
	}


	@Override
	public void withDraw(int id, long accNum, double amount) throws MistakeOccuredException 
	{
		idCheck(id);
		
		Map<Integer,Map<Long,AccountDetails>> accountMap=getAccount();
		
		Map<Long, AccountDetails> accDetailsMap = accountMap.get(id);
		
		HelperUtil.objectCheck(accDetailsMap, "CustomerId is not correct");
		
		AccountDetails accInfo = accDetailsMap.get(accNum);
		
		HelperUtil.objectCheck(accInfo, "Account id is not belong for this customer & ");
		
		accountAccess(accInfo);
		
		if (accInfo.getBalance() < amount) {
		
			throw new MistakeOccuredException("Insufficient balance.");
		}
		
		double newBalance = accInfo.getBalance() - amount;
		
	    updateRecord("update accountInfo set balance="+ newBalance +" where accountId="+accNum+" and "+"customerId="+id+";");
	}

    public void amountTransfer(long fromAccNo,long toAccNo,double amount) throws MistakeOccuredException
    {

    	if(fromAccNo==toAccNo)
    	{
    		throw new MistakeOccuredException("Transaction cannot be done between same Account.");
    	}
    	AccountDetails fromAccInfo=getAccountWithAccNo(fromAccNo);
    	
    	AccountDetails toAccInfo=getAccountWithAccNo(toAccNo);
    	
    	accountAccess(fromAccInfo);
    	
    	accountAccess(toAccInfo);
    	
    	double balance=toAccInfo.getBalance();
    	
    	double newBalance=balance+amount;
    	
    	updateRecord("update accountInfo set balance="+ newBalance +" Where accountId="+toAccNo+";");
       
    	double fromAccBalance=fromAccInfo.getBalance();
        
    	if(fromAccBalance<amount)
        {
        	throw new MistakeOccuredException("Insufficientbalance");
        }
        
    	double balanceAftrWithdrawed=fromAccBalance-amount;
        
    	updateRecord("update accountInfo set balance="+ balanceAftrWithdrawed +" Where accountId="+fromAccNo+";");
    }
	@Override
	public Map<Integer, Customer> getCustomer() throws MistakeOccuredException
	{
		createTable();
		Map<Integer,Customer> customerData=new HashMap<>();
		try(Connection connection=DriverManager.getConnection(url, uName, pass);
				Statement statement = connection.createStatement())
		{
			 String sql="select * from customerInfo;";
			 ResultSet result=statement.executeQuery(sql);
			
			 while(result.next())
			 {
				 Customer customerInfo=new Customer();
				 customerInfo.setName(result.getString("Name"));
				 customerInfo.setDob(result.getString("dob"));
				 customerInfo.setAddress(result.getString("Address"));
				 customerInfo.setPhoneNumber(result.getLong("MobileNo"));
				 customerInfo.setStatus(result.getBoolean("status"));
				 customerId=result.getInt("customerId");
				 customerData.put(customerId, customerInfo);
			 }

					return customerData;
		}					
				
		
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new MistakeOccuredException(e);
		}
	}


	@Override
	public Map<Integer, Map<Long, AccountDetails>> getAccount() throws MistakeOccuredException
	{
		createTable();
		Map<Integer,Map<Long,AccountDetails>> accountMap=new HashMap<>();
		try(Connection connection=DriverManager.getConnection(url, uName, pass);
				Statement statement = connection.createStatement())
		{
			String sql="select * from accountInfo;";
			ResultSet result=statement.executeQuery(sql);
			 
			while(result.next())
			{
				 AccountDetails accountInfo=new AccountDetails();
				 accountInfo.setCustomerId(result.getInt("customerId"));
				 accountInfo.setBranch(result.getString("branchName"));
				 accountInfo.setBalance(result.getDouble("balance"));
				 accountInfo.setStatus(result.getBoolean("status"));
				 accountId = result.getLong("accountId");
				 accountInfo.setAccountNumber(result.getLong("accountId"));
				 Map<Long,AccountDetails> temp=accountMap.get(accountInfo.getCustomerId());
				 if(temp==null)
				 {
					 temp=new HashMap<>();
					 accountMap.put(accountInfo.getCustomerId(), temp);
				 }
				 temp.put((long) accountId, accountInfo);
				 }
			
				return accountMap;
		   }
			catch(SQLException ex)
			{
				throw new MistakeOccuredException(ex);
			}
		
	}
 	public AccountDetails getAccountWithAccNo(long accNo) throws MistakeOccuredException
 	{
 		try(Connection connection=DriverManager.getConnection(url, uName, pass);
				Statement statement = connection.createStatement())
		{
			String sql="select * from accountInfo where accountId="+accNo+";";
			ResultSet result=statement.executeQuery(sql);
			AccountDetails accountInfo=null; 
			while(result.next())
			{
				 accountInfo=new AccountDetails();
				 accountInfo.setCustomerId(result.getInt("customerId"));
				 accountInfo.setBranch(result.getString("branchName"));
				 accountInfo.setBalance(result.getDouble("balance"));
				 accountInfo.setStatus(result.getBoolean("status"));
				 accountId = result.getLong("accountId");
				 accountInfo.setAccountNumber(result.getLong("accountId"));
			}
			return accountInfo;
 	    }
 		catch(SQLException ex)
 		{
 			throw new MistakeOccuredException("Transaction failed");
 		}
}
 	public void updateCustomer(int customerId,String name,String dob,String address,long phno) throws MistakeOccuredException
	{
 		String query="update customerInfo set name=?,dob=?,address=?,mobileNo=? where customerID=?;";
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				PreparedStatement statement=con.prepareStatement(query))
		{
			statement.setString(1, name);
			statement.setString(2, dob);
			statement.setString(3, address);
			statement.setLong(4, phno);
			statement.setInt(5, customerId);
			int number=statement.executeUpdate();
			System.out.println("Sucessfully Updated");
		}
		catch (SQLException e) 
		{
			throw new MistakeOccuredException(e);
		}
	}
 	public void updateAccount(int customerId,long accNo,String branch) throws MistakeOccuredException
	{
        System.out.println(branch);
        System.out.println(customerId);
        System.out.println(accNo);
 		String query="update accountInfo set branchName = ? where accountID= ?;";
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				PreparedStatement statement=con.prepareStatement(query))
		{
			statement.setString(1, branch);
			statement.setLong(2, accNo);
			int number=statement.executeUpdate();
			System.out.println("Sucessfully Updated");
		}
		catch (SQLException e) 
		{
			throw new MistakeOccuredException("Performed Action is failed");
		}
	}
 	public int lastRegisteredCustomer() throws MistakeOccuredException
 	{
 		String query="select max(customerID) from customerInfo;";
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				Statement statement=con.createStatement();)
		{	
		     ResultSet rs=statement.executeQuery(query);
		     rs.next();
		     int id=rs.getInt(1);
		     System.out.println(id);
		     return id;
		}
		catch(SQLException ex)
		{
	        throw new MistakeOccuredException(ex);
		}
 	}
 	
 	public boolean getRole(String id,String passWord) throws MistakeOccuredException
 	{
 		String query="select role from info where UserName=? and password=?;";
		try(Connection con=DriverManager.getConnection(url, uName, pass);
				PreparedStatement statement=con.prepareStatement(query);)
		{	
			 statement.setString(1, id);
			 statement.setString(2, passWord);
		     ResultSet rs=statement.executeQuery();
		     rs.next();
		     boolean role=rs.getBoolean("role");
		     return role;
		}
		catch(SQLException ex)
		{
	        throw new MistakeOccuredException("Entered username or password is invalid");
		}
 	}
 	
 	public List<String> getBranch() throws MistakeOccuredException
 	{
 		List<String> branchList=new ArrayList<>();
 		String query="select Branch from branch;";
		try(Connection con=DriverManager.getConnection(url, uName, pass);
			     Statement statement=con.createStatement();)
		{	
		     ResultSet rs=statement.executeQuery(query);
		     while(rs.next())
		     {
		        branchList.add(rs.getString("Branch"));
		     }
		     return branchList;
		}
		catch(SQLException ex)
		{
	        throw new MistakeOccuredException("Entered username or password is invalid");
		}
 	}
}
	
	
	
