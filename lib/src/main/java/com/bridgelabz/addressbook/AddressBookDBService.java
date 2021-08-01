package com.bridgelabz.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bridgelabz.addressbook.CustomException.ExceptionsType;

public class AddressBookDBService
{
	List<Contacts> contactListDB = new ArrayList<>();
	
	//to establish connection with database
		private Connection getConnection() throws SQLException 
		{		
			String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
			String username = "root";
			String password = "Jayesh@13";
			System.out.println("Conecting to database"+jdbcURL);
			Connection connection = null;
			connection = DriverManager.getConnection(jdbcURL,username,password);
			System.out.println("Connection sucessful" + connection);
			return connection;
		}

		//read data from database
		public List<Contacts> readFromDataBase()
		{
			String query = "select * from addressbook"
					+ "       join ab_grp on (addressbook.id = ab_grp.id)"
					+ "      join group_type on (ab_grp.gid =group_type.gid);";
			contactListDB = getQueryResult(query);
			return contactListDB;
		}
		
		private List<Contacts> getQueryResult(String query) 
		{
			List<Contacts> contactslist = new ArrayList<Contacts>();
			try(Connection connection = this.getConnection())
			{
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next())
				{
					int id = resultSet.getInt("id");
					String firstName = resultSet.getString("first");
					String lastname = resultSet.getString("last");
					String address = resultSet.getString("address");
					String city = resultSet.getString("city") ;
					String state= resultSet.getString("state");
					String zip= resultSet.getString("zip");
					int phoneNumber= resultSet.getInt("phoneNumber");
					String email = resultSet.getString("email");
					Contacts contact =  contactslist.stream().filter(contacts -> contacts.getFirstName().equals(firstName)).findFirst().orElse(null);
					if(contact != null)
					{
						List<String> group = contact.getGroups();
						group.add(resultSet.getString("gname"));
						contact.setGroups(group);
					}
					else
					{
						List<String> group = new ArrayList<String>(); 
						group.add(resultSet.getString("gname"));					
						contactslist.add(new Contacts(id,firstName, lastname, address, city, state, zip, phoneNumber, email,group));
					}	

				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new CustomException(ExceptionsType.INVALID_QUERY,"error while executing query");
			}
			return contactslist;
		}
		
		//method to update contact
		public void updateContactInDataBase(String name,int phoneNumber)
		{
			String sql = "update addressbook set phoneNumber = "+phoneNumber+" where first = '"+name+"';";
			try(Connection connection = this.getConnection())
			{
				Statement statement = connection.createStatement();
				int rowChanged = statement.executeUpdate(sql);
				if (rowChanged == 1)
				{
					Contacts contact = getContactFormList(name);
					contact.setPhoneNumber(phoneNumber);
				}
			}
			catch(SQLException e)
			{
				throw new CustomException(ExceptionsType.INVALID_QUERY,"error while executing query");
			}
		}
		
		//check sync with database
		public boolean checkSyncWithDB(String name) 
		{
			return getContactFromDatabase(name).get(0).equals(getContactFormList(name));
		}

		//search a particular contact in list
		private Contacts getContactFormList(String name)
		{
			return contactListDB.stream().filter(contacts->contacts.getFirstName().equals(name)).findFirst().orElse(null);
		}
		
		//get particular contact from database
		private List<Contacts> getContactFromDatabase(String name) 
		{
			String sql = "select * from addressbook join ab_grp on (addressbook.id = ab_grp.id)"
					+ "     join group_type on (ab_grp.gid =group_type.gid) where first = '"+name+"' ; "; 
			return getQueryResult(sql);
		}
		
		//method to find contact added after particular date
		public List<Contacts> getContatctsAddedAfterdate(String date)
		{
			String query = "Select  * from addressbook "
					+ "join ab_grp on (addressbook.id = ab_grp.id)"
					+ " join group_type on (ab_grp.gid =group_type.gid)"
					+ "  Where date_added Between cast('"+date+"' as date) and date(now()) ;";
			return getQueryResult(query); 
		}
		
		//method to find contacts having same city
		public HashMap<String, Integer> getContactHaveSameCity()
		{
			HashMap<String, Integer> matches = new HashMap<String, Integer>();
			try(Connection connection = this.getConnection())
			{
				String sql = " select city,count(city) from addressbook group by city; ";
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);
				while (resultSet.next())
				{
					matches.put(resultSet.getString(1),resultSet.getInt(2));
				}
			}
			catch(SQLException e)
			{
				throw new CustomException(ExceptionsType.INVALID_QUERY,"error while executing query");
			}
			return matches;
		}
}
