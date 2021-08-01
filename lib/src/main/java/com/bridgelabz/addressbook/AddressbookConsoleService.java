package com.bridgelabz.addressbook;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Comparator;
import com.bridgelabz.addressbook.CustomException.ExceptionsType;

public  class AddressbookConsoleService 
{
	//it holds books as key and contacts as values
	HashMap<String,LinkedList<Contacts>> addressBooks = new HashMap<>();
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
	public HashMap<String, LinkedList<Contacts>> addContacts(Contacts contact)
	{
		try
		{
			System.out.println("Enter Book name to which you have to add contact");
			String bookName  = scanner.next();

			//checking book already exist
			if (addressBooks.containsKey(bookName))
			{
				//if exist then add contact to list
				LinkedList<Contacts> contactList  =  addressBooks.get(bookName);				
				addContactToExsistingBook(contact, bookName, contactList);
			}
			else
			{	
				//creating a new book and list of contacts
				LinkedList<Contacts> allContacts = new LinkedList<>();
				allContacts.add(contact);
				addressBooks.put(bookName,allContacts);
				System.out.println("New book created and Contact Added Sucessfully");
			}			
		} 
		catch (Exception e) 
		{
			throw new CustomException(ExceptionsType.NUll_VALUE,"Obtained value is null");
		}
		return addressBooks;
	}

	//check Duplicate using name
	private void addContactToExsistingBook(Contacts contact, String bookName, LinkedList<Contacts> contactList)
	{
		boolean isAlreadyExsist = false;
		for (Contacts searchContact : contactList) 
		{
			if (searchContact.getFirstName().equals(contact.getFirstName()))
			{
				isAlreadyExsist = true;
				break;
			}
		}
		if( !(isAlreadyExsist) )
		{
			contactList.add(contact);				
			addressBooks.put(bookName, contactList);
			System.out.println("New Contact Added Sucessfully");
		}
		else
		{
			System.out.println("Contact already exsist");
		}
	}

	//edit contact
	public HashMap<String, LinkedList<Contacts>> editContact(String name,String bookName)
	{
		boolean is_found = false;
		if(addressBooks.size() == 0)
		{
			throw new CustomException(ExceptionsType.EMPTY_BOOK,"AddessBook is empty");
		}
		else  
		{
			try
			{
				for (Contacts contact : addressBooks.get(bookName))
				{	
					if (contact.getFirstName().equals(name))
					{
						getDetails(contact);
						is_found = true;
						System.out.println("Contact Updated");
						return addressBooks;
					}
				}
			}
			catch (Exception e) 
			{
				throw new CustomException(ExceptionsType.NUll_VALUE,"No such addressBook");
			}
		}
		contactNotPresent(is_found);
		return addressBooks;
	}


	//Method to delete contact
	public HashMap<String, LinkedList<Contacts>> deleteContact(String name ,String bookName)
	{
		boolean is_found = false;
		LinkedList<Contacts> conatctList = addressBooks.get(bookName);
		for (Contacts contact : conatctList)
		{	
			if (contact.getFirstName().equals(name))
			{
				conatctList.remove(contact);
				System.out.println("Deleted sucessfully");
				return addressBooks;
			}
		}

		contactNotPresent(is_found);
		return addressBooks;
	}


	//display contacts
	public void displayContacts() 
	{
		for (String bookName : addressBooks.keySet())
		{
			System.out.println(bookName);
			LinkedList<Contacts> contactList  =  addressBooks.get(bookName);
			displayContacts(contactList);
		}
	}

	public void displayContacts(LinkedList<Contacts> contactList)
	{
		for (Contacts contact : contactList)
		{	
			System.out.println(contact);
		}
	}

	//check weather contact is present or not
	private void contactNotPresent(boolean is_found) 
	{
		if (!is_found)
		{
			System.out.println("Contact not found");
		}
	}

	//method to search multiple person in city and state
	public List<Contacts> searchPerson(String searchKey)
	{
		for (String bookName : addressBooks.keySet())
		{
			LinkedList<Contacts> contactList  =  addressBooks.get(bookName);
			List<Contacts> contactHavingSameCityOrState = contactList.stream()
					.filter(contact->contact.getState().equals(searchKey) || contact.getCity().equals(searchKey))
					.collect(Collectors.toList());
			return contactHavingSameCityOrState;
		}
		return null;
	}

	//method to sort contacts based on person name
	public void sortContacts()
	{
		for (String bookName : addressBooks.keySet())
		{
			System.out.println(bookName);
			LinkedList<Contacts> contatct = addressBooks.get(bookName);
			contatct.stream().sorted(Comparator.comparing(Contacts::getFirstName)).forEach(contact->System.out.println(contact));
		}
	}

}


