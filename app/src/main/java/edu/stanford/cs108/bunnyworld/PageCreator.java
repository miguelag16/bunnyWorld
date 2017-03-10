package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class PageCreator extends AppCompatActivity {

    public Book book;

    public void addShape(View view) {
        Intent intent = new Intent(this, ShapeCreator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_creator);

        CurBookSingleton cb = CurBookSingleton.getInstance();
        book = cb.getCurrentBook();
        Page cp = book.getCurrentPage();

        TextView tv =  (TextView)findViewById(R.id.pc_pageName);
        tv.setText(cp.name);

        cp.Draw(book.getCurrentPage().getCanvas());
    }
}
