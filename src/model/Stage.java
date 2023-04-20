package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.text.ParseException;

public class Stage{

    private Calendar endDate;

    private Capsule[] capsules;
    private int madeCapsules;

    private Calendar realEndDate;

	public Stage(Calendar endDate){
        this.endDate = (Calendar) endDate.clone();

        capsules=new Capsule[50];
        this.madeCapsules=0;
	}

    public Calendar getEndDate(){
        return endDate;
    }

    public void setRealEndDate(){
        this.realEndDate=Calendar.getInstance();
    }

    public Calendar getRealEndDate(){
        return realEndDate;
    }

    public void registerCapsule(String situation, int type, String name, String positionCreator, String lesson, ArrayList<String> keywords){
        this.capsules[this.madeCapsules] =new Capsule(situation, type, name, positionCreator, lesson, keywords);
        this.madeCapsules= this.madeCapsules+1;
    }

    public int getMadeCapsules(){
        return madeCapsules;
    }

    public void acceptCapsule(int decision, String name, int actualStage){
		for(int i=0; i<madeCapsules;i++){
			if(capsules[i].getAproved()<1){
				capsules[i].setAproved(decision, name, actualStage, i);
			}
		}
	}

    public String findCapsule(){
        for(int i=0; i<madeCapsules; i++){
            if(capsules[i].getAproved()<1){
                return capsules[i].getCapsule();
            }
        }
        return "";
    }

    public ArrayList<String> findGroupOfLessons() {
		ArrayList<String> allCapsules = new ArrayList<String>();
        for(int i=0; i<madeCapsules; i++){
            allCapsules.add(capsules[i].getCapsule());
        }
		return allCapsules;
	}
}