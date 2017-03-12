package edu.stanford.cs108.bunnyworld;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dominic on 3/7/2017.
 */


public class Script implements Serializable {

    private ArrayList<String> clauses;


    private static final String ONCLICK = "on click";
    private static final String ONENTER = "on enter";
    private static final String ONDROP = "on drop";







    public Script(){
        clauses = new ArrayList<String>();
    }

    public void addGotoAction(String page_name){
        clauses.add("goto");
        clauses.add(page_name);
    }

    public void addPlaySoundAction(String sound_name){
        clauses.add("play");
        clauses.add(sound_name);
    }

    public void addHideAction(String shape_name){
        clauses.add("hide");
        clauses.add(shape_name);
    }

    public void addShowAction(String shape_name){
        clauses.add("show");
        clauses.add(shape_name);
    }

    //parses through all of the clauses entered for the shape and returns list of commands that need to
    //be executed given the trigger type
    //leave dropName as the empty string if it is a enter or click trigger
    public ArrayList<String> getClauses(String Trigger, String dropName){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < clauses.size(); i++){
            if(clauses.get(i).equals(Trigger)){
                if(Trigger.equals(ONDROP) && !clauses.get(i + 1).equals(dropName)){//only applies to drop triggers
                    continue;//increments i goes to top of for loop
                }
                while(!clauses.get(i).equals(";")){//adds all of the commands in the given clause
                    result.add(clauses.get(i));
                    i++;
                }
                return result;//result will always have even number of elements if configured properly
            }                 //it will always have a command directly followed by a shapename or a soundfile name
        }
        return null;//if null is returned, no actions are required for the trigger
    }

    public void addOnClickTrigger(){
        clauses.add(ONCLICK);
    }

    public void addEnterClickTrigger(){
        clauses.add(ONENTER);
    }

    public void addDropClickTrigger(String nameOfShapeDropped){
        clauses.add(ONDROP);
        clauses.add(nameOfShapeDropped);
    }

    //adds semicolon to end of current list of commands to signal the clause is finished
    public void EndCurrentClause(){
        clauses.add(";");
    }






}
