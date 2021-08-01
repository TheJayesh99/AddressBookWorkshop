package com.bridgelabz.addressbook;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bridgelabz.addressbook.CustomException.ExceptionsType;

public  class AddressbookConsoleService 
{
	//list to hold all contacts
	Contacts[] addressBook = new Contacts[20];
	private int index = 0 ;
	Scanner scanner = new Scanner(System.in);

	//method to create contacts
	public Contacts createContact()
	{
		Contacts contact = new Contacts();
		return	getDetails(contact);
	}

	//method to get details of contact
	private Contacts getDetails(Contacts contact)
	{
		try
		{
			System.out.println("Enter First Name");
			contact.setFirstName(scanner.next());
			System.out.println("Enter last Name");
			contact.setLastname(scanner.next());
			System.out.println("Enter address");
			contact.setAddress(scanner.next());
			System.out.println("Enter City");
			contact.setCity(scanner.next());
			System.out.println("Enter State");
			contact.setState(scanner.next());
			System.out.println("Enter Pincode");
			contact.setZip(scanner.next());
			System.out.println("Enter Phone Number");
			contact.setPhoneNumber(scanner.nextInt());
			System.out.println("Enter Email");
			contact.setEmail(scanner.next());
			return contact;
		}
		catch (InputMismatchException e)
		{
			throw new CustomException(ExceptionsType.WRONG_INPUT, "Entered the wrong value");
		}
	}
	//method to add contact to list
	public Contacts[] addContacts(Contacts contact)
	{
		try
		{
			addressBook[index++] = contact;
			return addressBook;			
		} 
		catch (Exception e) 
		{
			throw new CustomException(ExceptionsType.NUll_VALUE,"Obtained value is null");
		}
	}

	//edit contact
	public Contacts[] editContact(String name)//TODO fix the exception
	{
		boolean is_found = false;
		if(addressBook.length == 0)
		{
			throw new CustomException(ExceptionsType.EMPTY_LIST,"AddessBook is empty");
		}
		else  
		{
			for (int contact = 0; contact < addressBook.length; contact++) 
			{
				if(addressBook[contact] != null && addressBook[contact].getFirstName().equals(name) ) 
				{
					getDetails(addressBook[contact]);
					is_found = true;
					System.out.println("Contact Updated");
				}
			}
		}
		if (!is_found)
		{
			System.out.println("contact not found");
		}
		return addressBook;
	}

	//display contacts
	public void displayContacts() 
	{
		if (addressBook != null) 
		{			
			for (int contact = 0; contact < addressBook.length; contact++) 
			{
				if(addressBook[contact] != null) 
				{
					System.out.println(addressBook[contact]);
				}
			}
		}
		else
		{
			System.out.println("Contact list is empty");
		}
	}
}

