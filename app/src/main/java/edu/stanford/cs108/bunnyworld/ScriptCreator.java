package edu.stanford.cs108.bunnyworld;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by dominic on 3/12/2017.
 */

public class ScriptCreator extends AppCompatActivity {

    private Script script;
    private String trigger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.script_creator);
        script = new Script();
    }

    public void AddCommands(View view){

        //after Taking all the info, need to clear the data and toast to tell them they can add another or go back to page editor
        ClearData(view);
    }

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
        Button b = (Button) findViewById(R.id.SelectItemButton);
        b.setVisibility(View.GONE);
        tv = (TextView) findViewById(R.id.ListofItemsToPerformActionOnInstructions);
        tv.setVisibility(View.GONE);
        sp = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
        sp.setVisibility(View.GONE);
        b = (Button) findViewById(R.id.AddActionsButton);
        b.setVisibility(View.GONE);
    }

    public void onClickType(View view){
        ClearData(view);
        trigger = "on click";
        TextView tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.SelectItemButton);
        b.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);

    }

    public void onEnterType(View view){
        ClearData(view);
        trigger = "on enter";
        TextView tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.SelectItemButton);
        b.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);
    }

    public void onDropType(View view){
        ClearData(view);
        trigger = "on drop";
        TextView tv = (TextView) findViewById(R.id.InstructionsforOnDropShape);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.onDropShapeName);
        sp.setVisibility(View.VISIBLE);
        tv = (TextView) findViewById(R.id.actionListSpinnerInstructions);
        tv.setVisibility(View.VISIBLE);
        sp = (Spinner) findViewById(R.id.actionListSpinner);
        sp.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.SelectItemButton);
        b.setVisibility(View.VISIBLE);
        SetUpActionsSpinner(view);

    }

    public void getObjectsList(View view){
        TextView tv = (TextView) findViewById(R.id.ListofItemsToPerformActionOnInstructions);
        tv.setVisibility(View.VISIBLE);
        Spinner sp = (Spinner) findViewById(R.id.ListofItemsToPerformActionOn);
        sp.setVisibility(View.VISIBLE);
        Button b = (Button) findViewById(R.id.AddActionsButton);
        b.setVisibility(View.VISIBLE);
    }

    public void SetUpActionsSpinner(View view){
        Spinner spinner = (Spinner) findViewById(R.id.actionListSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actions_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }







}












