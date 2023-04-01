package ui;

import java.util.Scanner;
import model.Controller;
import java.util.Calendar;

public class Main{

	private Scanner reader;
	private Controller controller;

	public Main(){

		this.reader = new Scanner(System.in);
		this.controller = new Controller();

    }
    public static void main(String[] args){
        Main view = new Main();

        System.out.println("Bienvenido a la version preliminar del gestor de proyectos y capsulas de GreenSQA");
        int option=0;
        	do{
			view.menu();
			option = view.reader.nextInt();
			switch (option) {
				case 0:
					System.out.println("saliendo...");
					break;

                case 1:
                    view.RegisterProject();
                    break;

				case 2:
					view.consultProject();
					break;

				case 3:
					view.addManagers();
					break;

				default:
					System.out.println("No valido");
					break;
			}
		}while(option!=0);
	}

    public void menu(){
        System.out.println("0. Salir");
        System.out.println("1. Crear Proyecto");
		System.out.println("2. consultar información del proyecto");
		System.out.println("3. añadir gerentes");
    }

    public void RegisterProject() {

		if(controller.getCreatedProjects()<10){
			System.out.println("Inserte nombre del proyecto");
			String nameProject = reader.next();
			System.out.println("Inserte nombre del cliente");
			String nameClient = reader.next();
			System.out.println("Inserte el presuspuesto");
			double budget = reader.nextDouble();

			System.out.println("Inserte fecha de inicio");
			System.out.print("año : ");
			int startYear = reader.nextInt();
			System.out.println("");
			System.out.print("mes : ");
			int startMonth = reader.nextInt();
			System.out.println("");
			System.out.print("dia : ");
			int startDay = reader.nextInt();
			System.out.println("");
			Calendar start = Calendar.getInstance();
			start.set(Calendar.YEAR, startYear);
			start.set(Calendar.MONTH, startMonth);
			start.set(Calendar.DAY_OF_MONTH, startDay);

			System.out.println("Inserte fecha de fin");
			System.out.print("año : ");
			int endYear = reader.nextInt();
			System.out.println("");
			System.out.print("mes : ");
			int endMonth = reader.nextInt();
			System.out.println("");
			System.out.print("dia : ");
			int endDay = reader.nextInt();
			Calendar end = Calendar.getInstance();
			end.set(Calendar.YEAR, endYear);
			end.set(Calendar.MONTH, endMonth);
			end.set(Calendar.DAY_OF_MONTH, endDay); 

			controller.CreateProject(nameProject, nameClient, start, end, budget);
		}
		else{
			System.out.println("Cantidad de proyectos lleno");
		}
    }

	public void consultProject(){
		
		int index=selectProject();

		System.out.println("¿Que quieres consultar?");
		System.out.println("1. Informacion general");
		System.out.println("2. Información de los clientes");

		int decision = reader.nextInt();

		switch(decision){
			case 1:
				System.out.println(controller.consultInformationProject(index));
				reader.nextLine();
				break;
			
			case 2:
				System.out.println(controller.consultPeopleOfProject(index));
				reader.nextLine();
				break;

			default:
				System.out.println("opcion no valida");
				break;
		}
	}

	public int selectProject(){
		System.out.println(controller.showProjects());
		int indexProject = reader.nextInt() - 1;
		return indexProject;
	}

	public void addManagers(){

		int index=selectProject();

		System.out.println("Inserte nombre del gerente de GreenSQA");
		String nameGreen = reader.next();
		System.out.println("Inserte telefono del gerente de GreenSQA");
		String phoneGreen = reader.next();
		
		System.out.println("¿Cuantos gerentes del cliente son?");
		int quantityClients = reader.nextInt();

		String[] nameClients = new String[quantityClients];
		String[] phoneClients = new String[quantityClients];

		for(int i=0;i<(quantityClients);i++){
			System.out.println("Inserte nombre del gerente del cliente no." + (i+1));
			nameClients[i] = reader.next();
			System.out.println("Inserte telefono del gerente del cliente no." + (i+1));
			phoneClients[i] = reader.next();
		}

		String msg = controller.setManagers(index, nameGreen, phoneGreen, quantityClients, nameClients, phoneClients);
		System.out.println(msg);
		reader.next();	
	}
}
