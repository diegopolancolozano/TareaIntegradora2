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
	private Person[] clients;
	private Person greenManager;

	private DateFormat formatter;
	private String startString;
	private String endString;

	private Stage[] stages;

	private int stageCreated; 

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

		this.stageCreated = 0;

		this.stages = new Stage[6];
		for(int i=0; i<6;i++){
			this.stages[i] = new Stage(this.initialDate);
			}
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

	public void modifyStagesDates(int[] durationMonths){
		Calendar acumulated = (Calendar) initialDate.clone();
		if(this.actualStage==0){
			this.actualStage=1;
			for(int i=0;i<6;i++){
				stages[i].setEndDate(acumulated, durationMonths[i]);
				acumulated.add(Calendar.MONTH, durationMonths[i]);
			}
		}
	}

	public int getActualStage(){
		return this.actualStage;
	}

	public void finishStage(){
		this.actualStage=this.actualStage+1;
	}

	public String showStagesDates(){
		String msg = "";
		for(int i=0; i<6; i++){
			msg = msg + "Etapa " + i + ". fecha planificada: " + formatter.format(stages[i].getEndDate().getTime()) + ". y fecha real: " + formatter.format(stages[i].getRealEndDate().getTime()) + "\n";
		}
		return msg;
	}

	public int getActualStageMadeCapsule(){
		return stages[this.actualStage].getMadeCapsules();
	}

	public void registerCapsule(String situation, int type, String nameCreator, String positionCreator, String lesson){
        this.stages[this.stageCreated].registerCapsule(situation, type, nameCreator, positionCreator, lesson);
		this.stageCreated+=1;
    }

    public String findCapsule(){
        for(int i=0; i<=stageCreated; i++){
            if(!((stages[i].findCapsule()).equals(""))){
				return stages[i].findCapsule();
            }
        }
        return "";
    }

	public void acceptCapsule(int decision){
		for(int i=0;i<=stageCreated;i++){
			stages[i].acceptCapsule(decision);
		}
	}
}
