package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class ShapeCreator extends AppCompatActivity {

    public Book book;

    public void addShape(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shape_creator);

        CurBookSingleton cb = CurBookSingleton.getInstance();
        book = cb.getCurrentBook();
    }
}
