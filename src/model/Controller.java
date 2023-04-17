package model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Controller {

	private Project[] projects;
	private int createdProjects;

	public Controller() {

		projects = new Project[10];
		this.createdProjects = 0;

	}

    public void CreateProject(String nameP, String nameC, Calendar start, double budget, String nameGreen, String phoneGreen, String nameClient, String phoneClient, int[] eachStageDuration){
        if(this.createdProjects<10){
			this.projects[this.createdProjects] = new Project(nameP, nameC, start, budget, nameGreen, phoneGreen, nameClient, phoneClient, eachStageDuration);
			this.createdProjects+=1;
		}
    }

    public int getCreatedProjects(){
        return createdProjects;
    }

	public String showProjects(){
		String msg="";
		for(int i=0;i<createdProjects;i++){
			msg = msg + (i+1) + ". " + projects[i].getName() + " ";
		}
		return msg;
	}

	public String consultInformationProject(int index){
		String msg = "El proyecto: " + projects[index].getName() + " con el cliente: " + projects[index].getClient() + ", tiene un presupuesto de: " + String.valueOf(projects[index].getBudget() + "\n");
		msg = msg + "Inicia en la fecha: " + projects[index].getStartDate() + " y termina en la fecha: " + projects[index].getEndDate();
		return msg;
	}

	public String consultPeopleOfProject(int index){
		String msg = projects[index].getPeople();
		return msg;
	}

	public int getActualStage(int index){
		return projects[index].getActualStage();
	}

	/*public void modifyStagesDates(int index, int[] durationMonths){

		projects[index].modifyStagesDates(durationMonths);

	}*/

	public void finishStage(int index){
		this.projects[index].finishStage();
	}

	public String consultStagesDates(int index){
		String msg = projects[index].showStagesDates();
		return msg;
	}

	public int getActualStageMadeCapsule(int index){
		return projects[index].getActualStageMadeCapsule();
	}

	public void registerCapsule(int index, String situation, int type, String nameCreator, String positionCreator, String lesson, ArrayList<String> keywords){
        this.projects[index].registerCapsule(situation, type, nameCreator, positionCreator, lesson, keywords);
    }

    public String findCapsule(){
        for(int i=0; i<createdProjects; i++){
            if(!(projects[i].findCapsule().equals(""))){
                return projects[i].findCapsule();
            }
        }
        return "";
    }

	public void acceptCapsule(int decision){
		for(int i=0;i<createdProjects;i++){
			projects[i].acceptCapsule(decision);
		}
	}

}