package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

import model.Controller;

/**
 * @author Diego Polanco
 */
public class Main{

	private Scanner reader;
	private Controller controller;

	/**
     * Constructor of Main class.
     */
	public Main(){

		this.reader = new Scanner(System.in);
		this.controller = new Controller();

    }
    public static void main(String[] args){
        Main view = new Main();

        System.out.println("Bienvenido a la version preliminar del gestor de proyectos y capsulas de GreenSQA");
		System.out.println("Para empezar cree un proyecto");
		view.RegisterProject();
        int option=0;
        	do{
			view.menu();
			option = view.validateInteger();
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
					view.finishStage();
					break;

				case 4:
					view.createCapsule();
					break;

				case 5:
					view.acceptCapsule();
					break;

				case 6:
					view.showRegisterCapsulesByType();
					break;

				case 7:
					view.showGroupOfLessons();
					break;

				case 8:
					view.projectWithMoreCapsules();
					break;

				case 9:
					view.showIfWorkerCreatedCapsule();
					break;

				case 10:
					view.searchCapsule();
					break;

				default:
					System.out.println("No valido");
					break;
			}
		}while(option!=0);
	}

	/**
     * Shows the options
     */
    public void menu(){
        System.out.println("0. Salir");
        System.out.println("1. Crear Proyecto");
		System.out.println("2. Consultar información del proyecto");
		System.out.println("3. Culminar etapa");
		System.out.println("4. Crear capsula");
		System.out.println("5. Aceptar capsula");
		System.out.println("6. Cantidad de capsulas de cada tipo");
		System.out.println("7. Mostrar un grupo de lecciones de cierto proyecto en cierta etapa");
		System.out.println("8. Mostrar proyecto con más capsulas");
		System.out.println("9. Buscar si un colaborador a creado alguna capsula en un proyecto");
		System.out.println("10. Buscar una capsula");
    }

	/**
     * allow to register a new project.
     */
    public void RegisterProject() {

		if(controller.getCreatedProjects()<10){
			System.out.println("Inserte nombre del proyecto");
			String nameProject = reader.next();
			System.out.println("Inserte nombre del cliente");
			String nameClient = reader.next();
			System.out.println("Inserte el presuspuesto");
			double budget = reader.nextDouble();

			System.out.println("Inserte fecha de inicio");
			System.out.print("dia : ");
			int startDay = validateInteger();
			System.out.println("");
			System.out.print("mes : ");
			int startMonth = validateInteger();
			System.out.println("");
			System.out.print("año : ");
			int startYear = validateInteger();
			System.out.println("");
			Calendar start = Calendar.getInstance();
			start.set(Calendar.YEAR, startYear);
			start.set(Calendar.MONTH, startMonth);
			start.set(Calendar.DAY_OF_MONTH, startDay);

			System.out.println("Inserte nombre del gerente de GreenSQA");
			reader.nextLine();
			String nameGreen = reader.nextLine();
			System.out.println("Inserte telefono del gerente de GreenSQA");
			String phoneGreen = reader.nextLine();

			System.out.println("Inserte nombre del cliente");
			String ClientManagerName = reader.nextLine();
			System.out.println("Inserte el telefono del cliente");
			String phoneClient = reader.nextLine();

			int[] eachStageDuration = new int[6];
			for(int i=0;i<6;i++){
				System.out.println("Cuanto dura la etapa numero " + (1+i));
				eachStageDuration[i]=validateInteger();
			}

			controller.CreateProject(nameProject, nameClient, start, budget, nameGreen, phoneGreen, ClientManagerName, phoneClient, eachStageDuration);
		}
		else{
			System.out.println("Cantidad de proyectos lleno");
		}
    }

	/**
 	* shows information of the selected project
 	*/
	public void consultProject(){
		
		int index;
		do{
			index=selectProject();
		}while (index<0 || index>=controller.getCreatedProjects());

		int decision;
		do{
		System.out.println("¿Que quieres consultar?");
		System.out.println("1. Informacion general");
		System.out.println("2. Información del cliente y gerente");
		System.out.println("3. consultar fechas de etapas del proyecto");

		decision = validateInteger();
		}while(decision>3 || decision<1);

		switch(decision){
			case 1:
				System.out.println(controller.consultInformationProject(index));
				System.out.println("\nEsta en la etapa: " + (controller.getActualStage(index)+1));
				break;
			
			case 2:
				System.out.println(controller.consultPeopleOfProject(index));
				break;

			case 3:
				System.out.println(controller.consultStagesDates(index));
				break;

			default:
				System.out.println("opcion no valida");
				break;
		}
	}

	/**
 	* allows the user to select a project
	* @return the index of the project in the controller array
 	*/
	public int selectProject(){
		int indexProject;
		System.out.println("Seleccione un proyecto");
		do{
			System.out.println(controller.showProjects());
			indexProject = validateInteger() - 1;
		}while(indexProject>controller.getCreatedProjects() && !(indexProject<0) && indexProject>controller.getCreatedProjects());

		return indexProject;
	}

	/**
 	* finishes the actual stage of the project selected
 	*/
	public void finishStage(){
		int index = selectProject();
		if(controller.getActualStage(index)>5){
			System.out.println("Ya terminó");
		}
		else{
			controller.finishStage(index);
			System.out.println("Se guardarón los cambios");
		}
	}

	/**
 	* creates a new capsule in the given project and it actual stage
 	*/

	public void createCapsule(){
		int index = selectProject();
		if(controller.getActualStageMadeCapsule(index)<50){
			System.out.println("Inserte el nombre del creador de la capsula");
			reader.nextLine();
			String name=reader.nextLine();
			System.out.println("Inserte su rol");
			String rol = reader.nextLine();
			int type;
			do{
				System.out.println("Inserte el tipo (1. tecnico / 2. gestion / 3. dominio / 4. experiencias)");
				type = validateInteger();
			}while(type>4 || type<1);
			reader.nextLine();
			System.out.println("Inserte la descripción");
			String description = reader.nextLine();
			System.out.println("Inserte la lección aprendida");
			String lesson = reader.nextLine();

			ArrayList<String> keywords = new ArrayList<>();
            String[] words = description.split(" ");
            for (String word : words) {
                if (word.startsWith("#") && word.endsWith("#")) {
                    String keyword = word.substring(1, word.length() - 1);
                    keywords.add(keyword);
                }
            }
			
			words = lesson.split(" ");
            for (String word : words) {
                if (word.startsWith("#") && word.endsWith("#")) {
                    String keyword = word.substring(1, word.length() - 1);
                    keywords.add(keyword);
                }
            }

			if(keywords.size()==0){
				System.out.println("No se pudo efectuar, pues faltan palabras clave");
				System.out.println("recuerde que las palabras clave están entre  el simbolo hashtag(#)");
			}else{
				controller.registerCapsule(index, description, type, name, rol, lesson, keywords);
			}
			}
		else{
			System.out.println("Limite alcanzado");
		}
	}

	/**
 	* allows the user to accept a capsule
 	*/

	public void acceptCapsule(){
		String msg = controller.findCapsule();
		System.out.println(msg);

		if(!(msg.equals(""))){
			int decision;
			do{
				System.out.println("¿aceptar?\n1. no / 2. si");
				decision = validateInteger();
			}while(decision==1 || decision==2);
			controller.acceptCapsule(decision);
			System.out.println("Se guardo la capsula");
		}
		else{
			System.out.println("No hay más capsulas");
		}
	}

	/**
 	* show the quantity of capsules per type
 	*/
	public void showRegisterCapsulesByType(){
		System.out.println("¿De que tipo de capsulas te gustaria saber la cantidad?");
		System.out.println("1. tecnico // 2. gestion // 3. dominio // 4. experiencias // 5. todas");
		int decision;
		do{
			decision = validateInteger();
		}while(decision<1 || decision>5);
		System.out.println("Hay un total de: " + controller.amountCapsulesPerType(decision));
	}

	/**
 	* shows an arrayList of capsules in a given stage of a project
 	*/
	public void showGroupOfLessons(){
		int index = selectProject();
		System.out.println("Seleccione la etapa");
		System.out.print("1. inicio");
		int actualStage = controller.getActualStage(index);
		if(actualStage==1) System.out.print(" 2. analisis");
		if(actualStage==2) System.out.print(" 3. diseño");
		if(actualStage==3) System.out.print(" 4. ejecucion");
		if(actualStage==4) System.out.print(" 5. cierre");
		if(actualStage==5) System.out.print(" 6. seguimiento y control");
		System.out.println("");

		int option;
		int stage;
		do{
			option=validateInteger();
			stage=option-1;
		}while(option>actualStage || option<0);

		int quantityOfCapsulesOfStage = controller.getMadeCapsulesOfStage(index, stage);
		
		ArrayList<String> allCapsules = (ArrayList<String>) controller.findGroupOfLessons(index, stage).clone();
		
		if(allCapsules.size()==0){
			System.out.println("No hay capsulas");
		}
		boolean enough = false;
		for(int i=0;i<quantityOfCapsulesOfStage && !enough;i++){
			System.out.println(allCapsules.get(i));
			System.out.println("Inserte 1 para parar, 0 para continuar");
			if(validateInteger()==1) enough = true;
		}
	}

	/**
 	* shows the projects with teh biggest quantity of capsules
 	*/
	public void projectWithMoreCapsules(){
		System.out.println(controller.findProjectMoreCapsules());
	}

	/**
 	* shows if a worker made a capsule in the selected project
 	*/
	public void showIfWorkerCreatedCapsule(){
		int index = selectProject();
		reader.nextLine();
		System.out.println("Inserte el nombre del colaborador");
		String name = reader.nextLine();
		System.out.println(controller.showIfWorkerCreatedCapsule(index, name));
	}

	/**
 	* Shows an ArrayList of capsules with the same keyword that the user wrote
 	*/
	public void searchCapsule(){
		System.out.println("Inserte la palabra clave a buscar");
		reader.nextLine();
		String word = reader.nextLine();
		
		ArrayList<String> foundCapsules = (ArrayList<String>) controller.searchWord(word).clone();
		if(foundCapsules.size()==0){
			System.out.println("No hay capsulas o no se han aceptado");
		}else{
			boolean enough=false;
			for(int i=0;i<foundCapsules.size() && !enough;i++){
				System.out.println(foundCapsules.get(i));
				System.out.println("Inserte 1 para parar, 0 para continuar");
				if(validateInteger()==1) enough = true;
			}
		}
	}

	/**
 	* validates the input of a integer, a loop that only finishes when the user writes an integer
	* @return an integer
 	*/
	public int validateInteger(){
		reader.nextLine();
		int number = 0;
		do{
			if(reader.hasNextInt()){
				number = reader.nextInt(); 
			}
			else{
				System.out.println("Inserte un entero");
				reader.nextLine(); 
				number = -1; 
			}
		}while(number==-1);

		return number;
	}
}