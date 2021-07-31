package com.bridgelabz.addressbook;

public class Contacts 
{
	//contact details
	private String firstName;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String zip;
	private int phoneNumber;
	private String email;


	public Contacts() 
	{
		super();
	}
	
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastname() 
	{
		return lastname;
	}
	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public String getCity() 
	{
		return city;
	}
	public void setCity(String city) 
	{
		this.city = city;
	}
	public String getState() 
	{
		return state;
	}
	public void setState(String state) 
	{
		this.state = state;
	}
	public String getZip() 
	{
		return zip;
	}
	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	public int getPhoneNumber() 
	{
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	//to convert  contact object to string
	@Override
	public String toString() {
		return "Contacts [firstName=" + firstName + ", lastname=" + lastname + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	} 

}


