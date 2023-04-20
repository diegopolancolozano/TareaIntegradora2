package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;

import model.Controller;

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
		System.out.println("Para empezar cree un proyecto");
		view.RegisterProject();
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

				default:
					System.out.println("No valido");
					break;
			}
		}while(option!=0);
	}

    public void menu(){
        System.out.println("0. Salir");
        System.out.println("1. Crear Proyecto");
		System.out.println("2. Consultar información del proyecto");
		System.out.println("3. Culminar etapa");
		System.out.println("4. Crear capsula");
		System.out.println("5. Aceptar capsula");
		System.out.println("6. Cantidad de capsulas de cada tipo");
		System.out.println("7. Mostrar un grupo de lecciones de cierto proyecto en cierta etapa");
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
			System.out.print("dia : ");
			int startDay = reader.nextInt();
			System.out.println("");
			System.out.print("mes : ");
			int startMonth = reader.nextInt();
			System.out.println("");
			System.out.print("año : ");
			int startYear = reader.nextInt();
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
				eachStageDuration[i]=reader.nextInt();
			}

			controller.CreateProject(nameProject, nameClient, start, budget, nameGreen, phoneGreen, ClientManagerName, phoneClient, eachStageDuration);
		}
		else{
			System.out.println("Cantidad de proyectos lleno");
		}
    }

	public void consultProject(){
		
		int index=selectProject();

		System.out.println("¿Que quieres consultar?");
		System.out.println("1. Informacion general");
		System.out.println("2. Información del cliente y gerente");
		System.out.println("3. consultar fechas de etapas del proyecto");

		int decision = reader.nextInt();

		switch(decision){
			case 1:
				System.out.println(controller.consultInformationProject(index));
				reader.nextLine();
				System.out.println("\nEsta en la etapa: " + (controller.getActualStage(index)+1));
				break;
			
			case 2:
				System.out.println(controller.consultPeopleOfProject(index));
				reader.nextLine();
				break;

			case 3:
				System.out.println(controller.consultStagesDates(index));
				reader.nextLine();
				break;

			default:
				System.out.println("opcion no valida");
				break;
		}
	}

	public int selectProject(){
		int indexProject;
		System.out.println("Seleccione un proyecto");
		do{
			System.out.println(controller.showProjects());
			indexProject = reader.nextInt() - 1;
		}while(indexProject>controller.getCreatedProjects() && !(indexProject<0) && indexProject>controller.getCreatedProjects());

		return indexProject;
	}

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
				type = reader.nextInt();
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

	public void acceptCapsule(){
		String msg = controller.findCapsule();
		System.out.println(msg);

		if(!(msg.equals(""))){
			System.out.println("¿aceptar?\n1. no / 2. si");
			int decision = reader.nextInt();
			controller.acceptCapsule(decision);
			System.out.println("Se guardo la capsula");
		}
		else{
			System.out.println("No hay más capsulas");
		}
	}

	public void showRegisterCapsulesByType(){
		System.out.println("¿De que tipo de capsulas te gustaria saber la cantidad?");
		System.out.println("1. tecnico // 2. gestion // 3. dominio // 4. experiencias // 5. todas");
		int decision = reader.nextInt();
		System.out.println("Hay un total de: " + controller.amountCapsulesPerType(decision));
	}

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
		int option=reader.nextInt();
		int stage;
		do{
			stage = option-1;
		}while(option>actualStage && option<0);

		int quantityOfCapsulesOfStage = controller.getMadeCapsulesOfStage(index, stage);
		
		ArrayList<String> allCapsules = (ArrayList<String>) controller.findGroupOfLessons(index, stage).clone();
		
		if(allCapsules.size()==0){
			System.out.println("No hay capsulas");
		}
		boolean enough = false;
		for(int i=0;i<quantityOfCapsulesOfStage && !enough;i++){
			System.out.println(allCapsules.get(i));
			System.out.println("Inserte 1 para parar");
			if(reader.nextInt()==1) enough = true;
		}
	}

}