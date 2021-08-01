package com.bridgelabz.addressbook;

import java.util.List;
import java.util.Scanner;

public class AddressBookMain 
{
	private static final int ADD_CONTACT = 1;
	private static final int EDIT_CONATCT = 2;
	private static final int DISPLAY_CONATCT = 3;
	private static final int DELETE_CONATCT = 4;
	private static final int SEARCH_CONTACT = 5;
	private static final int VIEW_PERSON = 6;
	private static final int COUNT_PERSON = 7;
	private static final int SORT = 8;
	private static final int EXIT = 9;
	
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
					+ "\n 4 Delete Contact"
					+ "\n 5 Search Person on basis of city or state"
					+ "\n 6 View Person on basis of city or state"
					+ "\n 7 Count number Of person in same city or state"
					+ "\n 8 Sort Persons by name "
					+ "\n 9 Exit"
					+ "");

			int userChoice = scanner.nextInt();
			switch (userChoice) 
			{
			case ADD_CONTACT:				
				Contacts contact = addressbookConsoleService.createContact();
				addressbookConsoleService.addContacts(contact);
				break;

			case EDIT_CONATCT:
				System.out.println("Enter a name of person of whom you waht to change data");
				String nameToEdit  = scanner.next();
				System.out.println("Enter Book name to which you have to edit contact");
				String bookNameToEdit  = scanner.next();
				addressbookConsoleService.editContact(nameToEdit , bookNameToEdit);
				break;

			case DISPLAY_CONATCT:
				addressbookConsoleService.displayContacts();
				break;

			case DELETE_CONATCT:
				System.out.println("Enter a name of person of whom you want to delete conatct");
				String nameToDelete  = scanner.next();
				System.out.println("Enter Book name to which you have to delete contact");
				String bookNameForDelete  = scanner.next();
				addressbookConsoleService.deleteContact(nameToDelete , bookNameForDelete);
				break;

			case SEARCH_CONTACT:
				List<Contacts> contactListToSearchPerson = addressbookConsoleService.searchPerson(getSearchKey(scanner));
				if(contactListToSearchPerson.isEmpty())
				{					
					System.out.println("There are no such contact");
				}
				else 
				{
					contactListToSearchPerson.stream().map(contacts -> contacts.getFirstName()).forEach(conatct_name -> System.out.println(conatct_name));
				}
				break;
			
			case VIEW_PERSON:
				List<Contacts> contactListToVIewPerson = addressbookConsoleService.searchPerson(getSearchKey(scanner));
				if(contactListToVIewPerson.isEmpty())
				{					
					System.out.println("There are no such contact");
				}
				else 
				{
					System.out.println(contactListToVIewPerson);
				}
				break;
				
			case COUNT_PERSON:
				List<Contacts> contactListToCount = addressbookConsoleService.searchPerson(getSearchKey(scanner));
				System.out.println("There are total "+contactListToCount.size()+" Contacts");
				break;
				
			case SORT:
				addressbookConsoleService.sortContacts();
				break;
				
			case EXIT:
				System.out.println("Thanks for using us");
				scanner.close();
				System.exit(0);
				break;


			default:
				System.out.println("Enter a proper value");
			}
		}
	}
	
	//gets key for search
	private static String getSearchKey(Scanner scanner) 
	{
		System.out.println("Enter the city or state which to be searched");
		String searchKey = scanner.next();
		return searchKey;
	}
}
