package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class PageCreator extends AppCompatActivity {

    edu.stanford.cs108.bunnyworld.PageView pv;
    Shape current = null;

    public void addShape(View view) {
        Intent intent = new Intent(this, ShapeCreator.class);
        startActivity(intent);
    }

    public void undo(View view) {
//        LinearLayout x = (LinearLayout) findViewById(R.id.pc_buttonRow);
//        x.setVisibility(View.GONE);
//
//        LinearLayout y = (LinearLayout) findViewById(R.id.pc_editLL);
//        y.setVisibility(View.VISIBLE);
    }

    public void save(View view) {
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    public void deleteShape(View view) {
        pv.deleteShape();
    }

    public void updateShape(View view) {
        current = pv.getPVCurrentShape();

        EditText n = (EditText) findViewById(R.id.pc_nameET);
        current.setName(n.getText().toString());

        EditText w = (EditText) findViewById(R.id.pc_widthET);
        current.setWidth(Float.parseFloat(w.getText().toString()));

        EditText h = (EditText) findViewById(R.id.pc_heightET);
        current.setHeight(Float.parseFloat(h.getText().toString()));

        LinearLayout ell = (LinearLayout) findViewById(R.id.pc_editLL);
        ell.setVisibility(View.GONE);

        LinearLayout br = (LinearLayout) findViewById(R.id.pc_buttonRow);
        br.setVisibility(View.VISIBLE);

//        pv.resetPVCurrentShape();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_creator);

        pv = (edu.stanford.cs108.bunnyworld.PageView)
                findViewById(R.id.pc_pageView);

        CurBookSingleton cb = CurBookSingleton.getInstance();
        Page cp = cb.getCurrentBook().getCurrentPage();
        ((TextView)findViewById(R.id.pc_pageName)).setText(cp.name);
    }
}
