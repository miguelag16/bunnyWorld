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
    private Page cp;
    Shape cs = null;

    public void addShape(View view) {
        Intent intent = new Intent(this, ShapeCreator.class);
        startActivity(intent);
    }

    public void undo(View view) {
        if(cs != null){
            // needs some work, inspect removeShape, figure out unique naming of shapes
            cp.removeShape(cs);
            cp.addShape(cs);
            cs = null;
            pv.reDraw();
        }
    }

    public void save(View view) {
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    public void deleteShape(View view) {
        cs = pv.getPVCurrentShape();
        pv.deleteShape();
    }

    public void updateShape(View view) {
        cs = new Shape(pv.getPVCurrentShape());

        EditText n = (EditText) findViewById(R.id.pc_nameET);
        pv.getPVCurrentShape().setName(n.getText().toString());

        EditText w = (EditText) findViewById(R.id.pc_widthET);
        pv.getPVCurrentShape().setWidth(Float.parseFloat(w.getText().toString()));

        EditText h = (EditText) findViewById(R.id.pc_heightET);
        pv.getPVCurrentShape().setHeight(Float.parseFloat(h.getText().toString()));

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
        cp = cb.getCurrentBook().getCurrentPage();
        ((TextView)findViewById(R.id.pc_pageName)).setText(cp.name);
    }
}
