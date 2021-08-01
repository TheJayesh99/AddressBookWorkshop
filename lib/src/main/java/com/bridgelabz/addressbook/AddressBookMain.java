package com.bridgelabz.addressbook;

import java.util.Scanner;

public class AddressBookMain 
{
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		AddressbookConsoleService addressbookConsoleService = new AddressbookConsoleService();
		System.out.println("Welcome to Address Book");
		while (true) 
		{
			System.out.println("Enter what you have to do");
			System.out.println(""
					+ " 1 Add Contact "
					+ "\n 2 Edit Contact "
					+ "\n 3 Display Contacts"
					+ "\n 4 Exit"
					+ "");

			int userChoice = scanner.nextInt();
			switch (userChoice) 
			{
			case 1:				
				Contacts contact = addressbookConsoleService.createContact();
				addressbookConsoleService.addContacts(contact);
				break;
			
			case 2:
				System.out.println("Enter a name of person of whom you waht to change data");
				addressbookConsoleService.editContact(scanner.next());
				break;

			case 3:
				addressbookConsoleService.displayContacts();
				break;
			
			case 4:
				scanner.close();
				System.exit(0);
				break;

			default:
				System.out.println("Enter a proper value");
			}
		}
	}
}
