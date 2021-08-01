package com.bridgelabz.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bridgelabz.addressbook.AddressBookDBService;
import com.bridgelabz.addressbook.Contacts;



public class AddressBookTesting 
{
	@Test
	public void givenReadFromDB_ShouldReturnListOfContacts() 
	{
		AddressBookDBService addressBookService = new AddressBookDBService();
		List<Contacts> contactList  = addressBookService.readFromDataBase();
		assertEquals(4, contactList.size());
	}
}
