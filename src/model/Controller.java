package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Controller {

	private Project[] projects;
	private int createdProjects;

	/**
     * Constructor of Controller class.
     */
	public Controller() {

		projects = new Project[10];
		this.createdProjects = 0;

	}

	/**
     * allows to add a new project
     * @param nameP Name proyect.
     * @param nameC Name of client.
     * @param start Start date of the project.
     * @param budget Budget of the project.
     * @param nameGreen Name of employee in charge to negotiate with the client.
     * @param phoneGreen phone number of employee in charge.
     * @param nameClient Name of client in charge to negotiate.
     * @param phoneClient phone number of client in charge to negotiate.
     * @param eachStageDuration Duración de cada una de las etapas del proyecto.
     */
    public void CreateProject(String nameP, String nameC, Calendar start, double budget, String nameGreen, String phoneGreen, String nameClient, String phoneClient, int[] eachStageDuration){
        if(this.createdProjects<10){
			this.projects[this.createdProjects] = new Project(nameP, nameC, start, budget, nameGreen, phoneGreen, nameClient, phoneClient, eachStageDuration);
			this.createdProjects+=1;
		}
    }

	/**
     * get the amount of projects already created
     * @return amount of projects already created.
     */
    public int getCreatedProjects(){
        return createdProjects;
    }

    /**
     * Shows the names of the projects created
     * @return String of projects created.
     */
	public String showProjects(){
		String msg="";
		for(int i=0;i<createdProjects;i++){
			msg = msg + (i+1) + ". " + projects[i].getName() + " ";
		}
		return msg;
	}

    /**
     * consults the information of a project
     * @param index int : index of the selected project.
     * @return String with all the information
     */
	public String consultInformationProject(int index){
		String msg = "El proyecto: " + projects[index].getName() + " con el cliente: " + projects[index].getClient() + ", tiene un presupuesto de: " + String.valueOf(projects[index].getBudget() + "\n");
		msg = msg + "Inicia en la fecha: " + projects[index].getStartDate() + " y termina en la fecha: " + projects[index].getEndDate();
		return msg;
	}

	/**
	* consults the information of the people that negotiate in a project
	* @param index int : index of the selected project.
	* @return String with all the information
	*/
	public String consultPeopleOfProject(int index){
		String msg = projects[index].getPeople();
		return msg;
	}

	/**
	* gets the actual stage of the project
	* @param index int : index of the selected project.
	* @return int that represents the actual stage of the project
	*/
	public int getActualStage(int index){
		return projects[index].getActualStage();
	}

	/**
	* finishes the stage of a project
	* @param index int : index of the selected project.
	*/
	public void finishStage(int index){
		this.projects[index].finishStage();
	}

	/**
	* gets the dates of the stages
	* @param index int : index of the selected project.
	* @return String with all the dates and information. 
	*/
	public String consultStagesDates(int index){
		String msg = projects[index].showStagesDates();
		return msg;
	}

	/**
	* gets the quantity made capsules in the actual stage of the project
	* @param index int : index of the selected project.
	* @return int that represents the quantity of made capsules in the actual stage
	*/
	public int getActualStageMadeCapsule(int index){
		return projects[index].getActualStageMadeCapsule();
	}

	/**
	 * Registers a capsule in the given project
	 * 
	 * @param index index of the selected project
	 * @param situation String that represents the situation
	 * @param type int that represents the type of the capsule
	 * @param nameCreator name of the creator of the capsule
	 * @param positionCreator The position of the creator of the capsule.
	 * @param lesson the lesson that creator got.
	 * @param keywords The keywords which represents the most important topics of the capsule.
	 */
	public void registerCapsule(int index, String situation, int type, String nameCreator, String positionCreator, String lesson, ArrayList<String> keywords){
        this.projects[index].registerCapsule(situation, type, nameCreator, positionCreator, lesson, keywords);
    }

	/**
	 * search the first capsule that wasn't checked yet
	 * @return The text of the capsule or "" if it was not found
	 */
    public String findCapsule(){
        for(int i=0; i<createdProjects; i++){
            if(!(projects[i].findCapsule().equals(""))){
                return projects[i].findCapsule();
            }
        }
        return "";
    }

	/**
	 * accepts the first capsule that is not checked
	 * @param decision int that represents the decision of accepting a capsule
	 */
	public void acceptCapsule(int decision){
		for(int i=0;i<createdProjects;i++){
			projects[i].acceptCapsule(decision);
		}
	}

	/**
	 * get the quantity of capsules per type
	 * @param type int that represents the type searched, 1. tecnico, 2. gestion, 3. dominio, 4. experiencias and 5. any type
	 * @return the quantity of that type of capsules 
	 */
	public int amountCapsulesPerType(int type){
		int sum=0;
		switch(type){
			case 1:
			for(int i=0; i<createdProjects; i++){
				sum+=projects[i].getAmountTecnico();
			}
			return sum;
			case 2:
			for(int i=0; i<createdProjects; i++){
				sum+=projects[i].getAmountGestion();
			}
			return sum;
			case 3:
			for(int i=0; i<createdProjects; i++){
				sum+=projects[i].getAmountDominio();
			}
			return sum;
			case 4:
			for(int i=0; i<createdProjects; i++){
				sum+=projects[i].getAmountExperiencias();
			}
			return sum;
			case 5:
			for(int i=0; i<createdProjects; i++){
				sum+=projects[i].totalCapsules();
			}
			return sum;
		}
		return sum;
	}

	/**
	 * get the quantity of made capsules of a given stage
	 * @param indexProject index of the selected project
	 * @param stageSelected index of the stage in the project
	 * @return the quantity of capsules made in that project and stage
	 */
	public int getMadeCapsulesOfStage(int indexProject, int stageSelected){
		return projects[indexProject].getStageMadeCapsule(stageSelected);
	}

	/**
	 * gets an ArrayList of lesson of capsules of a given stage of a given project
	 * @param index index of the selected project
	 * @param stage index of the stage in the project
	 * @return the capsules made in that project and stage in a ArrayList of Strings
	 */
	public ArrayList<String> findGroupOfLessons(int index, int stage) {
		ArrayList<String> allCapsules = new ArrayList<String>();
		allCapsules = (ArrayList<String>) projects[index].findGroupOfLessons(stage).clone();
		return allCapsules;
	}

	/**
	 * Finds the projects with the biggest quantity of made capsules
	 * @return the of the projects
	 */
	public String findProjectMoreCapsules(){
		int biggestCapsules=0;
		String msg="La mayor cantidad de capsulas en un proyecto es: ";
		for(int i=0; i<createdProjects; i++){
			if(projects[i].totalCapsules()>biggestCapsules){
				biggestCapsules=projects[i].totalCapsules();
			}
		}
		msg = msg + biggestCapsules +" y esta cantidad esta en los siguientes proyectos:\n";
		for(int i=0; i<createdProjects; i++){
			if(projects[i].totalCapsules()==biggestCapsules){
				msg = msg + projects[i].getName();
			}
		}
		return msg;
	}

	/**
	 * shows if a user with the given name wrote a capsule in that project
	 * @param index index of the selected project
	 * @param name name of the worker
	 * @return an String showing if the worker made or did not made a capsule
	 */
	public String showIfWorkerCreatedCapsule(int index, String name){
		return projects[index].showIfWorkerCreatedCapsule(name);
	}

	/**
	 * gets an ArrayList of capsules by a given keyword
	 * @param word keyword that has to be in the ArrayList of capsules
	 * @return a ArrayList of strings representing the found capsules
	 */
	public ArrayList<String> searchWord(String word){
		ArrayList<String> foundCapsules = new ArrayList<String>();

		for(int i=0; i<createdProjects; i++){

			ArrayList<String> list = new ArrayList<String>();
			list = (ArrayList<String>) projects[i].searchWord(word).clone();

			for(int j=0; j<list.size(); j++){
				foundCapsules.add(list.get(j));
			}

		}

		return foundCapsules;
	}
}