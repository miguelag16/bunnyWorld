package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dominic on 3/12/2017.
 */

public class ScriptCreator extends AppCompatActivity{

    private Script script;
    private String trigger;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("script", script.getScript());
        System.out.println("scriptsize= " + intent.getStringArrayListExtra("script").size());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_creator);
        script = new Script();


        Spinner spinner = (Spinner) findViewById(R.id.actionListSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                getObjectsList(selectedItemView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //nothing
            }

        });





    }

    public void AddCommands(View view){
        String shapeToBeDropped = "";
        if(trigger.equals(Script.ONDROP)){
            Spinner s = (Spinner) findViewById(R.id.onDropShapeName);
            shapeToBeDropped = s.getSelectedItem().toString();
        }
        Spinner actionSpinner = (Spinner) findViewById(R.id.actionListSpinner);
        String action = actionSpinner.getSelectedItem().toString();
        Spinner directObjectSpinner = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
        String directObject = directObjectSpinner.getSelectedItem().toString();
        if(trigger.equals(Script.ONDROP)){//shapetobedropped is display name, but script needs to have actual name added
            script.addDropClickTrigger(shapeToBeDropped);
        }
        else if(trigger.equals(Script.ONCLICK)){
            script.addOnClickTrigger();
        }
        else if(trigger.equals(Script.ONENTER)){
            script.addOnEnterTrigger();
        }

        if(action.equals(Script.HIDE)){//directObject is display name, but script needs to have actual name added
            script.addHideAction(directObject, trigger);
        }
        else if(action.equals(Script.SHOW)){//directObject is display name, but script needs to have actual name added
            script.addShowAction(directObject, trigger);
        }
        else if(action.equals(Script.PLAY)){
            script.addPlaySoundAction(directObject, trigger);
        }
        else if(action.equals(Script.GOTO)){//directObject is display name of page, but script needs to have actual name added
            script.addGotoAction(directObject, trigger);
        }
        //after Taking all the info, need to clear the data and toast to tell them they can add another or go back to page editor
        ClearData(view);
        Toast toast = Toast.makeText(
                getApplicationContext(),
                "Command added. Add more\ncommands or use back\nbutton to go back\nto add more shapes",
                Toast.LENGTH_LONG);
        toast.show();
        //System.out.println(script);

    }

    public String getRealShapeName(String ShapeDisplayName){
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        for(Page p : book.getAllPages()){
            for(Shape s : p.getAllShapes()){
                if(s.getName().equals(ShapeDisplayName)){
                    return s.getRealName();
                }
            }
        }
        while(true){
            System.out.println("big error");
        }
        //return "";

    }

//    public String getRealPageName(String PageDisplayName){
//        Book book = CurBookSingleton.getInstance().getCurrentBook();
//        for(Page p : book.getAllPages()){
//            if(p.getName() == PageDisplayName){
//                    return s.getRealName();
//            }
//        }
//        while(true){
//            System.out.println("big error");
//        }
//        //return "";
//
//    }




    public void ClearData(View view){
        RadioButton box = (RadioButton) findViewById(R.id.onClickCheckBox);
        if(box.isChecked()){
            box.toggle();
        }
        box = (RadioButton) findViewById(R.id.onDropCheckBox);
        if(box.isChecked()){
            box.toggle();
        }
        box = (RadioButton) findViewById(R.id.onEnterCheckBox);
        if(box.isChecked()){
            box.toggle();
        }
        TextView tv = (TextView) findViewById(R.id.InstructionsforOnDropShape);
        tv.setVisibility(View.GONE);
        Spinner sp = (Spinner) findViewById(R.id.onDropShapeName);
        sp.setVisibility(View.GONE);
        tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.GONE);
        sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.GONE);
        tv = (TextView) findViewById(R.id.ListofItemsToPerformActionOnInstructions);
        tv.setVisibility(View.GONE);
        sp = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
        sp.setVisibility(View.GONE);
        Button b = (Button) findViewById(R.id.AddActionsButton);
        b.setVisibility(View.GONE);
    }

    public void onClickType(View view){
        ClearData(view);
        trigger = Script.ONCLICK;
        TextView tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);

    }

    public void onEnterType(View view){
        ClearData(view);
        trigger = Script.ONENTER;
        TextView tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);
    }

    public void onDropType(View view){
        ClearData(view);
        trigger = Script.ONDROP;
        TextView tv = (TextView) findViewById(R.id.InstructionsforOnDropShape);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.onDropShapeName);
        sp.setVisibility(View.VISIBLE);
        tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);
        SetUpShapesSpinnerOnDrop(view);

    }

    public void getObjectsList(View view){
        TextView tv = (TextView) findViewById(R.id.ListofItemsToPerformActionOnInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
        sp.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.AddActionsButton);
        b.setVisibility(View.VISIBLE);

        Spinner ActionToPerform = (Spinner) findViewById(R.id.actionListSpinner);
        String action = ActionToPerform.getSelectedItem().toString();

        if(action.equals(Script.SHOW) || action.equals(Script.HIDE)){
            ArrayList<String> shapeNames = getAllShapeNames();
            Spinner spinner = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
            String[] names = shapeNames.toArray(new String[shapeNames.size()]);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(ScriptCreator.this,android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
        else if(action.equals(Script.PLAY)){
            Spinner spinner = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.audio_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
        else if(action.equals(Script.GOTO)){
            ArrayList<String> pageNames = getAllPageNames();
            Spinner spinner = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
            String[] names = pageNames.toArray(new String[pageNames.size()]);
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(ScriptCreator.this,android.R.layout.simple_spinner_item, names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }






    }

    public void SetUpActionsSpinner(View view){
        Spinner spinner = (Spinner) findViewById(R.id.actionListSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actions_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    //sets the spinner to display list of all shape names
    public void SetUpShapesSpinnerOnDrop(View view){
        ArrayList<String> shapeNames = getAllShapeNames();
        Spinner spinner = (Spinner) findViewById(R.id.onDropShapeName);
        String[] names = shapeNames.toArray(new String[shapeNames.size()]);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(ScriptCreator.this,android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }


    public ArrayList<String> getAllShapeNames(){
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        ArrayList<String> names = new ArrayList<String>();
        //adds the name of every shape from every page into the names list
        for(Page p: book.getAllPages()) {
            for (Shape j : p.getAllShapes())
                names.add(j.getName());
        }

        names.add("CURRENT SHAPE");
//        for(String i : book.pagesMap.keySet()){
//            for(Shape j : book.pagesMap.get(i).shapeList){
//                names.add(j.getName());
//            }
//        }
        return names;
    }


    public ArrayList<String> getAllPageNames(){
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        ArrayList<String> names = new ArrayList<String>();
        for(Page p: book.getAllPages())
            names.add(p.getName());
//        for(String i : book.pagesMap.keySet()){
//            names.add(book.pagesMap.get(i).getName());
//        }
        return names;
    }

}







