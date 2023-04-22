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

	/**
	* Constructor of project class
	* @param nameP name project
	* @param nameC name client
	* @param start start date
	* @param budget the budget of the project
	* @param nameGreen name of green's manager
	* @param phoneGreen phone of green's manager
	* @param nameClientManager name of client's manager
	* @param phoneClient phone of client's manager
	* @param eachStageDuration arreglo con la duración de cada etapa en meses
	*/
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

	/**
	* gets the name of the project
	* @return name of the project
	*/
	public String getName(){
		return this.name;
	}

	/**
	* gets the name of the client
	* @return name of the client
	*/
	public String getClient(){
		return this.clientName;
	}

	/**
	* gets the budget of the project
	* @return budget of the project
	*/
	public double getBudget(){
		return this.budget;
	}

	/**
	* gets the information of the managers
	* @return information of the managers
	*/
	public String getPeople(){
		String msg="";
		msg = "De green esta el gerente: " + greenManager.getName() + " de telefono: " + greenManager.getPhoneNumber() + "\ny del cliente esta: \n";
		msg = msg + clientManager.getName() + " de telefono: " + clientManager.getPhoneNumber() + "\n";
		return msg;
	}

	/**
	* gets the start date of the project
	* @return start date of the project
	*/
	public String getStartDate(){
		return this.startString;
	}

	/**
	* gets the end date of the project
	* @return end date of the project
	*/
	public String getEndDate(){
		return this.endString;
	}

	/**
	* gets the actual stage of the project
	* @return actual stage of the project
	*/
	public int getActualStage(){
		return this.actualStage;
	}

	/**
	* finishes the actual stage of the projects and continuous with the next stage
	*/
	public void finishStage(){
		this.stages[actualStage].setRealEndDate();
		this.actualStage=this.actualStage+1;
	}

	/**
	* gets the dates of the project
	* @return dates of the project
	*/
	public String showStagesDates(){
		String msg = "";
		for(int i=0; i<6; i++){
			msg = msg + "Etapa " + (i+1) + ". fecha planificada de fin: " + formatter.format(stages[i].getEndDate().getTime()) + ".\n";
		}
		return msg;
	}

	/**
	* gets the quantity made capsules in the actual stage date of the project
	* @return quantity of made capsules of the actual stage of the project
	*/
	public int getActualStageMadeCapsule(){
		return stages[this.actualStage].getMadeCapsules();
	}

	/**
	* gets the quantity made capsules in the given stage date of the project
	* @return quantity of made capsules of the stage of the project
	*/
	public int getStageMadeCapsule(int stage){
		return stages[stage].getMadeCapsules();
	}


	/**
	 * Registers a capsule in the actual stage
	 * @param situation String that represents the situation
	 * @param type int that represents the type of the capsule
	 * @param nameCreator name of the creator of the capsule
	 * @param positionCreator The position of the creator of the capsule.
	 * @param lesson the lesson that creator got.
	 * @param keywords The keywords which represents the most important topics of the capsule.
	 */
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

	/**
	 * search the first capsule that wasn't checked yet
	 * @return The text of the capsule or "" if it was not found
	 */
    public String findCapsule(){
        for(int i=0; i<=actualStage; i++){
            if(!((stages[i].findCapsule()).equals(""))){
				return stages[i].findCapsule();
            }
        }
        return "";
    }

	/**
	 * accepts the first capsule that is not checked
	 * @param decision int that represents the decision of accepting a capsule
	 */
	public void acceptCapsule(int decision){
		for(int i=0;i<=actualStage;i++){
			stages[i].acceptCapsule(decision, name, actualStage);
		}
	}

	/**
	 * get the amount of capsules of type Tecnico
	 * @return amount of capsules of type Tecnico
	 */
	public int getAmountTecnico(){
		return amountTecnico;
	}

	/**
	 * get the amount of capsules of type Gestion
	 * @return amount of capsules of type Gestion
	 */
	public int getAmountGestion(){
		return amountGestion;
	}

	/**
	 * get the amount of capsules of type Dominio
	 * @return amount of capsules of type Dominio
	 */
	public int getAmountDominio(){
		return amountDominio;
	}

	/**
	 * get the amount of capsules of type Experiencias
	 * @return amount of capsules of type Experiencias
	 */
	public int getAmountExperiencias(){
		return amountExperiencias;
	}

	/**
	 * get the amount of capsules
	 * @return amount of capsules
	 */
	public int totalCapsules(){
		return amountDominio+amountExperiencias+amountGestion+amountTecnico;
	}

	/**
	 * gets an ArrayList of all capsules in a certain stage
	 * @param stage the stage selected to find capsules
	 * @return ArrayList of capsules
	 */
	public ArrayList<String> findGroupOfLessons(int stage) {
		ArrayList<String> allCapsules = new ArrayList<String>();
        allCapsules = (ArrayList<String>) stages[stage].findGroupOfLessons().clone();
		return allCapsules;
	}

	/**
	 * shows if a user with the given name wrote a capsule in this project
	 * @param name name of the worker
	 * @return an String showing if the worker made or did not made a capsule
	 */
	public String showIfWorkerCreatedCapsule(String name){
		boolean found = false;
		for(int i=0; i<=actualStage && !found; i++){
			if(stages[i].showIfWorkerCreatedCapsule(name)){
				found=true;
			}
		}
		String msg="No ha creado";
		if(found==true){
			msg="Si ha creado";
		}
		return msg;
	}

	/**
	 * gets an ArrayList of capsules by a given keyword
	 * @param word keyword that has to be in the ArrayList of capsules
	 * @return a ArrayList of strings representing the found capsules
	 */
	public ArrayList<String> searchWord(String word){
		ArrayList<String> foundCapsules = new ArrayList<String>();

		for(int i=0; i<=actualStage; i++){

			ArrayList<String> list = new ArrayList<String>();
			list = (ArrayList<String>) stages[i].searchWord(word).clone();

			for(int j=0; j<list.size(); j++){
				foundCapsules.add(list.get(j));
			}

		}

		return foundCapsules;
	}

}
