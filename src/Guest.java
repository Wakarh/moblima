import java.io.Serializable;

enum Gender
{
	MALE, FEMALE
}

public class Guest implements Serializable{
	
	Gender gender;
	private String name, creditcard, address, country, identity, nationality;
	private int contact, guestID, reservationID;
	
	Guest(String name, String creditcard, String address, String country, Gender gender, String identity, String nationality, int contact)
	{
		this.name = name;
		this.creditcard = creditcard;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.identity = identity;
		this.nationality = nationality;
		this.contact = contact;
	}
	
	public String getAll()
	{
		return name+", "+creditcard+", "+country+", "+gender+", "+identity+", "+nationality+", "+contact;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getCreditcard()
	{
		return creditcard;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getCountry()
	{
		return country;
	}
	
	public Gender getGender()
	{
		return gender;
	}
	
	public String getIdentity()
	{
		return identity;
	}
	
	public String getNationality()
	{
		return nationality;
	}
	
	public int getContact()
	{
		return contact;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setCreditcard(String creditcard)
	{
		this.creditcard = creditcard;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public void setIdentity(String identity)
	{
		this.identity = identity;
	}
	
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	
	public void setContact(int contact)
	{
		this.contact = contact;
	}
	

}
