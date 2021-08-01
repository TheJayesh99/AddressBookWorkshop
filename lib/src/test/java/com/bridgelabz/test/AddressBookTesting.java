package com.bridgelabz.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.addressbook.AddressBookDBService;
import com.bridgelabz.addressbook.AddressbookConsoleService;
import com.bridgelabz.addressbook.Contacts;



public class AddressBookTesting 
{
	AddressbookConsoleService addressbookConsoleService = new AddressbookConsoleService();

	@Before
	public void contactslist()
	{
		Contacts contact1 = new Contacts();
		contact1.setFirstName("virat");
		contact1.setLastname("kholi");
		contact1.setAddress("panvel");
		contact1.setCity("mumbai");
		contact1.setState("Maharashtra");
		contact1.setZip("12343");
		contact1.setPhoneNumber(9985623);
		contact1.setEmail("mail@virat");
		
		Contacts contact2 = new Contacts();
		contact2.setFirstName("ms");
		contact2.setLastname("dhoni");
		contact2.setAddress("panvel");
		contact2.setCity("thane");
		contact2.setState("Maharashtra");
		contact2.setZip("12343");
		contact2.setPhoneNumber(9983423);
		contact2.setEmail("mail@ms");
		
		Contacts contact3 = new Contacts();
		contact3.setFirstName("ambani");
		contact3.setLastname("mukesh");
		contact3.setAddress("jio");
		contact3.setCity("thane");
		contact3.setState("Maharashtra");
		contact3.setZip("12343");
		contact3.setPhoneNumber(99856232);
		contact3.setEmail("jio@mail");
		
		addressbookConsoleService.addContacts(contact1,"Sports");
		addressbookConsoleService.addContacts(contact2,"Sports");
		addressbookConsoleService.addContacts(contact3,"Company");
	}

	@Test
	public void givenContactDetails_WhenAdded_ShouldReturnContactList() 
	{
		contactslist();
		assertEquals(2, AddressbookConsoleService.addressBooks.size());
	}
	
	@Test
	public void givenContactDetails_WhenUpdated_ShouldReturnContactList() 
	{
		
		contactslist();
		addressbookConsoleService.editContact("virat","Sports");
		assertEquals(2, AddressbookConsoleService.addressBooks.size());
	}

	@Test
	public void givenContactDetails_WhenDelete_ShouldReturnContactList() 
	{
		
		contactslist();
		addressbookConsoleService.deleteContact("virat","Sports");
		assertEquals(2, AddressbookConsoleService.addressBooks.size());
	}

	@Test
	public void givenContactDetails_WhenSearchPerson_ShouldReturnContactListHavingSameName() 
	{
		
		contactslist();
		List<Contacts> contacts = addressbookConsoleService.searchPerson("thane");
		assertEquals(2, contacts.size());
	}

	@Test
	public void givenContactDetails_WhenViewPerson_ShouldReturnContactListHavingSameName() 
	{
		
		contactslist();
		List<Contacts> contacts = addressbookConsoleService.searchPerson("thane");
		assertEquals(2, contacts.size());
	}

	@Test
	public void givenContactDetails_WhenCountPerson_ShouldReturnContactListHavingSameName() 
	{
		
		contactslist();
		List<Contacts> contacts = addressbookConsoleService.searchPerson("thane");
		assertEquals(2, contacts.size());
	}

	
	@Test
	public void givenReadFromDB_ShouldReturnListOfContacts() 
	{
		AddressBookDBService addressBookService = new AddressBookDBService();
		List<Contacts> contactList  = addressBookService.readFromDataBase();
		assertEquals(4, contactList.size());
	}
}
