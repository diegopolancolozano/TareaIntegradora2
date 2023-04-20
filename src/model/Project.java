package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.text.ParseException;


public class Project{
	
	private String name;
	private String clientName;
	private Calendar initialDate;
	private Calendar finalDate;
	private double budget;
    private int actualStage;

	private Person clientManager;
	private Person greenManager;

	private DateFormat formatter;
	private String startString;
	private String endString;

	private Stage[] stages;

	private int amountTecnico;
	private int amountGestion;
	private int amountDominio;
	private int amountExperiencias;

	public Project(String nameP, String nameC, Calendar start, double budget, String nameGreen, String phoneGreen, String nameClientManager, String phoneClient, int[] eachStageDuration){

		this.name = nameP;	
		this.clientName = nameC;
		this.initialDate = start;
		this.budget = budget;
        this.actualStage = 0;

		this.greenManager=new Person(nameGreen, phoneClient);
		this.clientManager=new Person(nameClientManager, phoneClient);

		this.formatter = new SimpleDateFormat("dd/MM/yy");

		this.stages = new Stage[6];

		Calendar tempDate = Calendar.getInstance();
		tempDate=(Calendar) initialDate.clone();
		tempDate.add(Calendar.MONTH, (eachStageDuration[0]-1));
		stages[0] = new Stage(tempDate);

		for(int i=1; i<6;i++){
			Calendar temporalDate = Calendar.getInstance();
			temporalDate= (Calendar) stages[i-1].getEndDate().clone();
			temporalDate.add(Calendar.MONTH, eachStageDuration[i]);
			this.stages[i] = new Stage(temporalDate);
		}

		this.finalDate = stages[5].getEndDate();

		this.startString = formatter.format(this.initialDate.getTime());
		this.endString = formatter.format(this.finalDate.getTime());

		amountTecnico=0;
		amountGestion=0;
		amountDominio=0;
		amountExperiencias=0;
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

	public String getPeople(){
		String msg="";
		msg = "De green esta el gerente: " + greenManager.getName() + " de telefono: " + greenManager.getPhoneNumber() + "\ny del cliente esta: \n";
		msg = msg + clientManager.getName() + " de telefono: " + clientManager.getPhoneNumber() + "\n";
		return msg;
	}

	public String getStartDate(){
		return this.startString;
	}

	public String getEndDate(){
		return this.endString;
	}

	public int getActualStage(){
		return this.actualStage;
	}

	public void finishStage(){
		this.stages[actualStage].setRealEndDate();
		this.actualStage=this.actualStage+1;
	}

	public String showStagesDates(){
		String msg = "";
		for(int i=0; i<6; i++){
			msg = msg + "Etapa " + (i+1) + ". fecha planificada de fin: " + formatter.format(stages[i].getEndDate().getTime()) + ".\n";
		}
		return msg;
	}

	public int getActualStageMadeCapsule(){
		return stages[this.actualStage].getMadeCapsules();
	}

	public int getStageMadeCapsule(int stage){
		return stages[stage].getMadeCapsules();
	}

	public void registerCapsule(String situation, int type, String nameCreator, String positionCreator, String lesson, ArrayList<String> keywords){
		switch (type){
			case 1 :
				this.amountTecnico=amountTecnico+1;
				break;
			case 2 :
				this.amountGestion=amountGestion+1;
				break;
			case 3 :
				this.amountDominio=amountDominio+1;
				break;
			case 4 :
				this.amountExperiencias=amountExperiencias+1;
				break;
				default:
				break;
		}
		this.stages[this.actualStage].registerCapsule(situation, type, nameCreator, positionCreator, lesson, keywords);
    }

    public String findCapsule(){
        for(int i=0; i<=actualStage; i++){
            if(!((stages[i].findCapsule()).equals(""))){
				return stages[i].findCapsule();
            }
        }
        return "";
    }

	public void acceptCapsule(int decision){
		for(int i=0;i<=actualStage;i++){
			stages[i].acceptCapsule(decision, name, actualStage);
		}
	}

	public int getAmountTecnico(){
		return amountTecnico;
	}

	public int getAmountGestion(){
		return amountGestion;
	}

	public int getAmountDominio(){
		return amountDominio;
	}

	public int getAmountExperiencias(){
		return amountExperiencias;
	}

	public int totalCapsules(){
		return amountDominio+amountExperiencias+amountGestion+amountTecnico;
	}

	public ArrayList<String> findGroupOfLessons(int stage) {
		ArrayList<String> allCapsules = new ArrayList<String>();
        allCapsules = (ArrayList<String>) stages[stage].findGroupOfLessons().clone();
		return allCapsules;
	}

}
