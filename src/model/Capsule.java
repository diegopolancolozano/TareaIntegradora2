package model;

import java.util.ArrayList;

public class Capsule{

	private String id;
    private String situation;
    private type typeCapsule;
    private String nameCreator;
    private String positionCreator;
    private String lesson;

    private int approved;

    private ArrayList<String> keyWords;

    public Capsule(String situation, int typee, String name, String positionCreator, String lesson, ArrayList<String> keyWords){

        this.situation=situation;
        switch(typee){
            case 1:
                typeCapsule=type.TECNICO;
                break;
            case 2:
                typeCapsule=type.GESTION;
                break;
            case 3:
                typeCapsule=type.DOMINIO;
                break;
            case 4:
                typeCapsule=type.EXPERIENCIAS;
                break;
        }
        this.nameCreator=name;
        this.positionCreator=positionCreator;
        this.lesson=lesson;
        this.approved=0;

        this.keyWords= new ArrayList<String>();
        this.keyWords.addAll(keyWords);
    }

    public String getCapsule(){
    String msg = "Del colaborador " + this.nameCreator + ":"; 
    msg = msg + "\n" + this.lesson;
    return msg;
    }

    public int getAproved(){
        return this.approved;
    }

    public void setAproved(int decision){
        this.approved=decision;
    }
}