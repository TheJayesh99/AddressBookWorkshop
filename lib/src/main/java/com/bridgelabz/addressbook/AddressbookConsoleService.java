package com.bridgelabz.addressbook;

import java.util.LinkedList;
import java.util.Scanner;

public  class AddressbookConsoleService 
{
	//list to hold all contacts
	LinkedList<Contacts> addressBook = new LinkedList<>(); 

	//method to create contacts
	public Contacts createContact()
	{
		Contacts contact = new Contacts();
		Scanner scanner = new Scanner(System.in);
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
		scanner.close();
		return contact;
	}
	
	//method to add contact to list
	public LinkedList<Contacts> addContacts(Contacts contact)
	{
		addressBook.add(contact);
		return addressBook;
	}
}
