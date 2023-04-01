package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.text.ParseException;


public class Project{
	
	private String name;
	private String clientName;
	private Calendar initialDate;
	private Calendar finalDate;
	private double budget;
    private int actualStage;

	private int quantityOfClients;
	private Person clients[];
	private Person greenManager;

	private DateFormat formatter;
	private String startString;
	private String endString;

	public Project(String nameP, String nameC, Calendar start, Calendar end, double budget){

		this.name = nameP;	
		this.clientName = nameC;
		this.initialDate = start;
		this.finalDate = end;
		this.budget = budget;
        this.actualStage = 0;

		this.greenManager=new Person();

		this.formatter = new SimpleDateFormat("dd/MM/yy");
		this.startString = formatter.format(this.initialDate.getTime());
		this.endString = formatter.format(this.finalDate.getTime());
		
	}

	public String getName(){
		return this.name;
	}

	public String getClient(){
		return this.clientName;
	}

	public double getBudget(){
		return this.budget;
	}

	public int getQuantityOfClients(){
		return this.quantityOfClients;
	}

	public String setManagers(String nameGreen, String phoneGreen, int quantityOfClients, String[] clientNames, String[] clientPhones){
		greenManager.setName(nameGreen);
		greenManager.setPhone(phoneGreen);

		this.quantityOfClients=quantityOfClients;

		this.clients = new Person[quantityOfClients];

		for(int i=0;i<quantityOfClients;i++){
			this.clients[i] = new Person(clientNames[i], clientPhones[i]);
		}

		return "Cambio hecho";
	}

	public String getPeople(){
		String msg="No se han añadido";
		if(quantityOfClients!=0){
			for(int i=0; i<quantityOfClients; i++){
				msg = "De green esta el gerente: " + greenManager.getName() + " de telefono: " + greenManager.getPhoneNumber() + "\n y del cliente esta: \n";
				msg = msg + clients[i].getName() + " de telefono: " + clients[i].getPhoneNumber() + "\n";
			}
		}
		return msg;
	}

	public String getStartDate(){
		return this.startString;
	}

	public String getEndDate(){
		return this.endString;
	}
}