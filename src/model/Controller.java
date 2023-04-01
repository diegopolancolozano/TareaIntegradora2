package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Controller {

	private Project[] projects;
	private int createdProjects;

	public Controller() {

		projects = new Project[10];
		this.createdProjects = 0;

	}

    public void CreateProject(String nameP, String nameC, Calendar start, Calendar end, double budget){
        if(this.createdProjects<10){
			this.projects[this.createdProjects] = new Project(nameP, nameC, start, end, budget);
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

	public String setManagers(int indexProject, String nameGreen, String phoneGreen, int quantityOfClients, String[] clientNames, String[] clientPhones){

		this.projects[indexProject].setManagers(nameGreen, phoneGreen, quantityOfClients, clientNames, clientPhones);

		return "Cambio hecho";
	}	
}