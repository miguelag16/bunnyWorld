package edu.stanford.cs108.bunnyworld;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dominic on 3/7/2017.
 */


public class Script implements Serializable {

    private ArrayList<String> clauses;


    public static final String ONCLICK = "on click";
    public static final String ONENTER = "on enter";
    public static final String ONDROP = "on drop";
    public static final String GOTO = "goto";
    public static final String SHOW = "show";
    public static final String HIDE = "hide";
    public static final String PLAY = "play";


    public Script(){
        clauses = new ArrayList<String>();
    }

    /*
        Copy constructor, useful for undo
     */
    public Script(Shape s) {
        this.clauses = s.getScript().copyScript();
    }


    //essentially returns the private variable stored in the given shape
    //used in scriptcreator to pass a string through an intent, then set Script can be used
    //to recreate the Script
    public ArrayList<String> getScript(){
        return clauses;
    }

    public void setScript(ArrayList<String> s){
        this.clauses = s;
    }

    public ArrayList<String> copyScript() {
        ArrayList<String> copy = new ArrayList<String>();
        for(String s: this.clauses)
            copy.add(s);

        return copy;
    }

    //adds the goto action to to the commands executed for the given trigger
    public void addGotoAction(String page_name, String trigger){
        int index = clauses.indexOf(trigger);
        if(trigger.equals(Script.ONDROP)){
            index++;
        }
        clauses.add(index + 1, Script.GOTO);
        clauses.add(index + 2, page_name);
    }

    //adds the play action to to the commands executed for the given trigger
    public void addPlaySoundAction(String sound_name, String trigger){
        int index = clauses.indexOf(trigger);
        if(trigger.equals(Script.ONDROP)){
            index++;
        }
        clauses.add(index + 1, Script.PLAY);
        clauses.add(index + 2, sound_name);
    }

    //adds the hide action to to the commands executed for the given trigger
    public void addHideAction(String shape_name, String trigger){
        int index = clauses.indexOf(trigger);
        if(trigger.equals(Script.ONDROP)){
            index++;
        }
        clauses.add(index + 1, Script.HIDE);
        clauses.add(index + 2, shape_name);
    }

    //adds the show action to to the commands executed for the given trigger
    public void addShowAction(String shape_name, String trigger){
        int index = clauses.indexOf(trigger);
        if(trigger.equals(Script.ONDROP)){
            index++;
        }
        clauses.add(index + 1, Script.SHOW);
        clauses.add(index + 2, shape_name);
    }

    //parses through all of the clauses entered for the shape and returns list of commands that need to
    //be executed given the trigger type
    //leave dropName as the empty string if it is a enter or click trigger
    public ArrayList<String> getClauses(String Trigger, String dropName){
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < clauses.size(); i++){
            System.out.println(clauses.get(i));
            if(clauses.get(i).equals(Trigger)){
                if(Trigger.equals(ONDROP) && !clauses.get(i + 1).equals(dropName)){//only applies to drop triggers
                    continue;//increments i goes to top of for loop
                }
                i++;
                while(i < clauses.size() && !clauses.get(i).equals(ONCLICK)
                && !clauses.get(i).equals(ONENTER) && !clauses.get(i).equals(ONDROP)) {//adds all of the commands in the given clause
                    result.add(clauses.get(i));
                    i++;
                }
                return result;//result will always have even number of elements if configured properly
            }                 //it will always have a command directly followed by a shapename or a soundfile name
        }
        return result;//result will have zero elements in this case
    }

    public void addOnClickTrigger() {
        if (clauses.contains(ONCLICK)) {
            return;
        }
        clauses.add(ONCLICK);
    }

    public void addOnEnterTrigger(){
        if (clauses.contains(ONENTER)) {
            return;
        }
        clauses.add(ONENTER);
    }

    public void addDropClickTrigger(String nameOfShapeDropped){
        if (clauses.contains(ONDROP)) {
            return;
        }
        clauses.add(ONDROP);
        clauses.add(nameOfShapeDropped);
    }

    //SemiColon is kind of pointless in current implementation of Script.
    //getClauses returns everything inbetween triggers and script creator currently does not
    //have the ability to add semicolons between triggers.
    //also trying to add actions to the same trigger will append the actions to that type of trigger.
    //scripts will not let you have two triggers of the same type.
    //basically this function is never used and has no need to exist
    public void EndCurrentClause(){
        clauses.add(";");
    }


    @Override
    public String toString() {
        String result = "";
        for(String i : clauses){
            result += i + '\n';
        }
        return result;
    }

}
