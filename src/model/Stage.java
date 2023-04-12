package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

    public void setEndDate(Calendar startDate, int durationMonths){
        this.endDate=(Calendar) startDate.clone();
        endDate.add(Calendar.MONTH, durationMonths);
        this.realEndDate=(Calendar) realEndDate.clone();
    }

    public Calendar getEndDate(){
        return endDate;
    }

    public Calendar getRealEndDate(){
        return realEndDate;
    }

    public void registerCapsule(String situation, int type, String name, String positionCreator, String lesson){
        this.capsules[(this.madeCapsules)] =new Capsule(situation, type, name, positionCreator, lesson);
        this.madeCapsules= this.madeCapsules+1;
    }

    public int getMadeCapsules(){
        return madeCapsules;
    }

    public void acceptCapsule(int decision){
		for(int i=0; i<madeCapsules;i++){
			if(capsules[i].getAproved()<1){
				capsules[i].setAproved(decision);
			}
		}
	}

    public String findCapsule(){
        System.out.println("con 0" + capsules[0].getCapsule());
        for(int i=0; i<madeCapsules; i++){
            System.out.println(capsules[i].getCapsule());
            if(capsules[i].getAproved()<1){
                System.out.println(i);
                System.out.println("stage" + capsules[i].getCapsule());
                return capsules[i].getCapsule();
            }
        }
        return "";
    }
}