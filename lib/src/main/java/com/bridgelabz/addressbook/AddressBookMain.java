package com.bridgelabz.addressbook;


public class AddressBookMain 
{
	public static void main(String[] args) 
	{
		AddressbookConsoleService addressbookConsoleService = new AddressbookConsoleService();
		System.out.println("Welcome to Address Book");
		addressbookConsoleService.createContact();
	}
}
