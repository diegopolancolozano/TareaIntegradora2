package model;

public class Person{
	
	private String namePerson;
	private String phoneNumber;

	public Person(){
	}

	/**
	 * Constructor of person class, represents a manager of a company
	 * @param name the name of the manager
	 * @param phone the phone of the manager
	 */
	public Person(String name, String phone){
		this.namePerson=name;
		this.phoneNumber=phone;
	}

	/**
	 * gets the name of the manager
	 * @return the name of the manager
	 */
	public String getName(){
		return this.namePerson;
	}

	/**
	 * gets the phone number of the manager
	 * @return the phone number of the manager
	 */
	public String getPhoneNumber(){
		return this.phoneNumber;
	}

	/**
	 * sets the name of the manager
	 * @param newName the new name
	 */
	public void setName(String newName){
		this.namePerson=newName;
	}	
    
	/**
	 * sets the phone number of the manager
	 * @param phoneNumber the new phoneNumber
	 */
	public void setPhone(String newPhone){
		this.phoneNumber=newPhone;
	}

	/**
	 * Sets all the manager information
	 * @param name new name
	 * @param phone new phone number
	 */
	public void setPerson(String name, String phone){
		this.namePerson=name;
		this.phoneNumber=phone;
	}
}