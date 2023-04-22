package model;

public class Worker{

    private String name;
    private String rol;
    
    /**
     * Constructor of worker class, the worker who makes capsules
     * @param name name of the worker
     * @param rol position of the worker in the project
     */
    public Worker(String name, String rol){
        this.name=name;
        this.rol=rol;
    }

    /**
     * sets the name of the worker
     * @param name new name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * sets the position of the worker
     * @param rol new position
     */
    public void setRol(String rol){
        this.rol=rol;
    }

    /**
     * gets the name of the worker
     * @return the name of the worker
     */
    public String getName(){
        return name;
    }

    /**
     * gets the position of the worker
     * @return the position of the worker
     */
    public String getRol(){
        return rol;
    }
}