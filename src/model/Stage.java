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

	/**
	 * constructor of Stage class
	 * @param endDate Calendar that represents the ending date of the project
	 */
	public Stage(Calendar endDate){
        this.endDate = (Calendar) endDate.clone();

        capsules=new Capsule[50];
        this.madeCapsules=0;
	}

	/**
	 * constructor of Stage class
	 * @return the ending date
	 */
    public Calendar getEndDate(){
        return endDate;
    }

	/**
	 * sets the real end date
	 */
    public void setRealEndDate(){
        this.realEndDate=Calendar.getInstance();
    }

	/**
	 * gets the real end date
	 * @return the real ending date
	 */
    public Calendar getRealEndDate(){
        return realEndDate;
    }

	/**
	 * Registers a capsule
	 * @param situation String that represents the situation
	 * @param type int that represents the type of the capsule
	 * @param nameCreator name of the creator of the capsule
	 * @param positionCreator The position of the creator of the capsule.
	 * @param lesson the lesson that creator got.
	 * @param keywords The keywords which represents the most important topics of the capsule.
	 */
    public void registerCapsule(String situation, int type, String name, String positionCreator, String lesson, ArrayList<String> keywords){
        this.capsules[this.madeCapsules] =new Capsule(situation, type, name, positionCreator, lesson, keywords);
        this.madeCapsules= this.madeCapsules+1;
    }

	/**
	 * gets the total of made capsules
	 * @return total of made capsules
	 */
    public int getMadeCapsules(){
        return madeCapsules;
    }

	/**
	 * accepts the first capsule that is not accepted
	 * @param decision 1. decline capsule, 2. accept capsule
     * @param name name of the project
     * @param actualStage the actual stage of the project
	 */
    public void acceptCapsule(int decision, String name, int actualStage){
		for(int i=0; i<madeCapsules;i++){
			if(capsules[i].getAproved()<1){
				capsules[i].setAproved(decision, name, actualStage, i);
			}
		}
	}

	/**
	 * search the first capsule that wasn't checked yet
	 * @return The text of the capsule or "" if it was not found
	 */
    public String findCapsule(){
        for(int i=0; i<madeCapsules; i++){
            if(capsules[i].getAproved()<1){
                return capsules[i].getCapsule();
            }
        }
        return "";
    }

	/**
	 * gets an ArrayList of all capsules in a certain stage
	 * @return ArrayList of capsules
	 */
    public ArrayList<String> findGroupOfLessons() {
		ArrayList<String> allCapsules = new ArrayList<String>();
        for(int i=0; i<madeCapsules; i++){
            allCapsules.add(capsules[i].getCapsule());
        }
		return allCapsules;
	}

	/**
	 * shows if a user with the given name wrote a capsule in this project
	 * @param name name of the worker
	 * @return an String showing if the worker made or did not made a capsule
	 */
    public boolean showIfWorkerCreatedCapsule(String name){
        boolean found = false;
		for(int i=0; i<madeCapsules && !found; i++){
			if((capsules[i].getNameCreator()).equalsIgnoreCase(name)){
				found=true;
			}
		}
		return found;
    }

	/**
	 * gets an ArrayList of capsules by a given keyword
	 * @param word keyword that has to be in the ArrayList of capsules
	 * @return a ArrayList of strings representing the found capsules
	 */
    public ArrayList<String> searchWord(String word){
		ArrayList<String> foundCapsules = new ArrayList<String>();

		for(int i=0; i<madeCapsules; i++){

			if(capsules[i].hasKeyWord(word)){
                foundCapsules.add(capsules[i].getCapsule());
            }

		}

		return foundCapsules;
	}
}