package Details;

import java.io.Serializable;

public class Customer implements Serializable
{
private String name;
private String dob;
private String address;
private int customerId;
private long phoneNumber;
private boolean status=true;

public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getName() {
	return name;
}
public  void setName(String name) {
	this.name = name;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public long getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(long phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public String toString()
{
	return "Customer Name:"+name+","+"Customer DOB:"+getDob()+","+"Address:"+address+
			","+"Phone Number:"+phoneNumber+","+"status:"+status; 
}

}

