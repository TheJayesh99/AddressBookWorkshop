package com.bridgelabz.addressbook;

import java.util.LinkedList;

public class AddressBookMain 
{
	public static void main(String[] args) 
	{
		AddressbookConsoleService addressbookConsoleService = new AddressbookConsoleService();
		System.out.println("Welcome to Address Book");
		Contacts contact = addressbookConsoleService.createContact();
		LinkedList<Contacts> contactlist = addressbookConsoleService.addContacts(contact);
		System.out.println(contactlist);
	}
}
