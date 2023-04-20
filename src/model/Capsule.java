package model;

import java.util.ArrayList;

public class Capsule{

	private String id;
    private String situation;
    private Type typeCapsule;
    private String nameCreator;
    private String positionCreator;
    private String lesson;

    private Status approved;
    private String link;

    private ArrayList<String> keyWords;

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
        this.nameCreator=name;
        this.positionCreator=positionCreator;
        this.lesson=lesson;
        this.approved=Status.STAND_BY;

        this.link="";

        this.keyWords= new ArrayList<String>();
        this.keyWords.addAll(keyWords);
    }

    public String getCapsule(){
    String msg = "Del colaborador " + this.nameCreator + ":"; 
    msg = msg + "\n" + this.lesson + "\n" + this.situation;
    return msg;
    }

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

    public void setAproved(int decision, String nameProject, int actualStage, int numberCapsule){
        if(decision==1){
            approved=Status.NOT_ACCEPTED;
        }
        else{
            approved=Status.ACCEPTED;
        }
        this.link="www.GreenSQA/Capsules/"+nameProject+"/"+actualStage+"/"+numberCapsule+".com";
    }
}