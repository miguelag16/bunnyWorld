package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class ShapeCreator extends AppCompatActivity {

    private Script ScriptForAddedShape = new Script();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("MACRO KEY 2 cauz im soooo cool");
        if(requestCode == 1) {
            System.out.println("stan missed his butt");
            System.out.println(resultCode);
            if(resultCode == RESULT_OK) {
                System.out.println("looooooooooooooolz");
                Spinner spinner = (Spinner) findViewById(R.id.sc_spinner);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.shapes_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);


                ArrayList<String> scriptStrings = data.getStringArrayListExtra("script");


                if (scriptStrings == null) {
                    System.out.println("ScriptStrings is NULL aAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    return;
                }
                System.out.println("scriptStrings " + scriptStrings.size());

                ScriptForAddedShape.setScript(data.getStringArrayListExtra("script"));


                ArrayList<String> x = new ArrayList<String>();
                System.out.println("this is passed through the intent:");
                System.out.println(ScriptForAddedShape.toString());

                //x.add(ScriptForAddedShape.toString());

                String line = "";
                for(int i = 0; i < ScriptForAddedShape.getScript().size(); i++){
                    if(i != 0 && (ScriptForAddedShape.getScript().get(i).equals(Script.ONCLICK) ||
                            ScriptForAddedShape.getScript().get(i).equals(Script.ONENTER) || ScriptForAddedShape.getScript().get(i).equals(Script.ONDROP))){
                        x.add(line);
                        line = ScriptForAddedShape.getScript().get(i) + " ";
                    }
                    else{
                        line += ScriptForAddedShape.getScript().get(i) +" ";
                    }
                }
                x.add(line);

                ListView list = (ListView) findViewById(R.id.script_list);
                ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, x);
                list.setAdapter(itemsAdapter);
            }
        }
    }

    public void addShapeToPage(View view) {
        CurBookSingleton cbs = CurBookSingleton.getInstance();
        cbs.makeBackupPage();
        Page cp = cbs.getCurrentPage();

        String filename = ((Spinner) findViewById(R.id.sc_spinner)).getSelectedItem().toString();
        filename = filename.substring(0, filename.indexOf('.'));
        String wordArt = ((EditText) findViewById(R.id.sc_text)).getText().toString();

        Shape s = new Shape(filename, wordArt);
        s.setIsHidden(((CheckBox) findViewById(R.id.sc_hidden)).isChecked());
        s.setIsMovable(((CheckBox) findViewById(R.id.sc_movable)).isChecked());
        s.script.setScript(ScriptForAddedShape.getScript());//I know this is a bit wonky

        cp.addShape(s);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_creator);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Spinner spinner = (Spinner) findViewById(R.id.sc_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shapes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void addScriptToShape(View view){
        Intent intent = new Intent(this, ScriptCreator.class);
        startActivityForResult(intent, 1);
    }


}
