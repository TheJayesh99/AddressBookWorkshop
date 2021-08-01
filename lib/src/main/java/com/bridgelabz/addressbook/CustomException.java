package com.bridgelabz.addressbook;

public class CustomException extends RuntimeException
{
	public enum ExceptionsType
	{
		EMPTY_LIST,
		WRONG_INPUT,
		NUll_VALUE
	}
	
	public ExceptionsType type;

	public CustomException(ExceptionsType type,String message) 
	{
		super(message);
		this.type = type;
	}
	
}
