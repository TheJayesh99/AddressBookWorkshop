package com.bridgelabz.addressbook;

import java.util.LinkedList;
import java.util.Scanner;

public  class AddressbookConsoleService 
{
	public void createContact()
	{
		Contacts contact = new Contacts();
		LinkedList<Contacts> allContacts = new LinkedList<>();
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
		
		System.out.println(contact);
	}
}
