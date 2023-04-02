package model;

public class Capsule{

	private String id;
    private String situation;
    private int type;
    private String nameCreator;
    private String positionCreator;
    private String lesson;

    private int approved;

    private String[] keyWords;

    /*public Capsule(){

        this.situation="vacio";
        this.type=0;
        this.nameCreator="vacio";
        this.positionCreator="";
        this.lesson="";
        this.approved=0;

    }

	public void setCapsule(String situation, int type, String name, String positionCreator, String lesson){

        this.situation=situation;
        this.type=type;
        this.nameCreator=name;
        this.positionCreator=positionCreator;
        this.lesson=lesson;
        System.out.println(nameCreator + "name");
	}*/

    public Capsule(String situation, int type, String name, String positionCreator, String lesson){

        this.situation=situation;
        this.type=type;
        this.nameCreator=name;
        this.positionCreator=positionCreator;
        this.lesson=lesson;
        System.out.println(nameCreator + "name");
        this.approved=0;

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