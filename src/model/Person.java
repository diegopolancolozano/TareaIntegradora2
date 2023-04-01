package model;

public class Person{
	
	private String namePerson;
	private String phoneNumber;

	public Person(){
	}

	public Person(String name, String phone){
		this.namePerson=name;
		this.phoneNumber=phone;
	}

	public String getName(){
		return this.namePerson;
	}

	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	public void setName(String newName){
		this.namePerson=newName;
	}	
    
	public void setPhone(String newPhone){
		this.phoneNumber=newPhone;
	}

	public void setPerson(String name, String phone){
		this.namePerson=name;
		this.phoneNumber=phone;
	}
}