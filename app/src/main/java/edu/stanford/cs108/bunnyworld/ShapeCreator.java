package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class ShapeCreator extends AppCompatActivity {

    public void addShapeToPage(View view) {
        Page cp = CurBookSingleton.getInstance().getCurrentBook().getCurrentPage();

        System.out.println("Here");
        String resText = ((Spinner) findViewById(R.id.sc_spinner)).getSelectedItem().toString();

        resText = resText.substring(0, resText.indexOf('.'));
        System.out.println(resText);
        String drawableText = ((EditText) findViewById(R.id.sc_text)).getText().toString();
        System.out.println(drawableText);
        boolean isHidden = ((RadioButton) findViewById(R.id.sc_hidden)).isChecked();
        boolean isMovable = ((RadioButton) findViewById(R.id.sc_movable)).isChecked();

        Point p = new Point(0, 0, 50, 50);

        Shape x = new Shape(cp, resText, drawableText, false, isHidden, isMovable, p);
        cp.addShape(x);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_creator);

        Spinner spinner = (Spinner) findViewById(R.id.sc_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.shapes_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ArrayList<String> x = new ArrayList<String>();
        x.add("miguel");
        x.add("dom");
        x.add("stan");
        x.add("garcia");
        x.add("abbondanzo");
        x.add("chernavsky");
        x.add("barcelona");

        ListView list = (ListView) findViewById(R.id.script_list);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, x);

        list.setAdapter(itemsAdapter);
    }
}
