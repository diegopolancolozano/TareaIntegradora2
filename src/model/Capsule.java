package model;

import java.util.ArrayList;

public class Capsule{

	private String id;
    private String situation;
    private Type typeCapsule;

    private String lesson;

    private Worker worker;

    private Status approved;
    private String link;

    private ArrayList<String> keyWords;

    /**
     * Constructor of capsule class
     * 
	 * @param situation String that represents the situation
	 * @param typee int that represents the type of the capsule
	 * @param name name of the creator of the capsule
	 * @param positionCreator The position of the creator of the capsule.
	 * @param lesson the lesson that creator got.
	 * @param keywords The keywords which represents the most important topics of the capsule.
	 */
    public Capsule(String situation, int typee, String name, String positionCreator, String lesson, ArrayList<String> keyWords){

        this.situation=situation;
        switch(typee){
            case 1:
                typeCapsule=Type.TECNICO;
                break;
            case 2:
                typeCapsule=Type.GESTION;
                break;
            case 3:
                typeCapsule=Type.DOMINIO;
                break;
            case 4:
                typeCapsule=Type.EXPERIENCIAS;
                break;
        }
        
        this.worker = new Worker(name, positionCreator);

        this.lesson=lesson;
        this.approved=Status.STAND_BY;

        this.link="";

        this.keyWords= new ArrayList<String>();
        this.keyWords.addAll(keyWords);
    }

    /**
     * get the information of the capsule
     * @return information of the capsule
     */
    public String getCapsule(){
        String msg="";
        if(this.approved==Status.ACCEPTED){
            msg+=this.link+"\n";
        }
        msg += "Del colaborador " + this.worker.getName() + ":"; 
        msg = msg + "\n" + this.lesson + "\n" + this.situation;
        return msg;
    }

    /**
     * get the approved information
     * @return information of the approval
     */
    public int getAproved(){
        if(approved==Status.STAND_BY){
            return 0;
        }
        if(approved==Status.NOT_ACCEPTED){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * sets the aproved atribute
     * @param decision the new approved status
     * @param nameProject the name of the project (for the link)
     * @param actualStage the stage of the project (for the link)
     * @param numberCapsule the number of the capsule of the project (for the link)
     */
    public void setAproved(int decision, String nameProject, int actualStage, int numberCapsule){
        if(decision==1){
            approved=Status.NOT_ACCEPTED;
        }
        else{
            approved=Status.ACCEPTED;
        }
        this.link="www.GreenSQA/Capsules/"+nameProject+"/"+actualStage+"/"+numberCapsule+".com";
    }

    /**
     * gets the name of the creator
     * @return name of the creator
     */
    public String getNameCreator(){
        return worker.getName();
    }

    /**
     * gets a keyword and compares it with the ones of the capsule
     * @param keyWord keyWord searched
     * @return if found : true. if not : false
     */
    public boolean hasKeyWord(String keyWord){
		if(this.keyWords.contains(keyWord) && approved==Status.ACCEPTED){
            return true;
        }
        else return false;
	}
}