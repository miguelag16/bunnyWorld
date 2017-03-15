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

    CurBookSingleton cbs = CurBookSingleton.getInstance();
    edu.stanford.cs108.bunnyworld.PageView pv;

    private Page cp;
    Shape backup = null;
    Shape cs = null;

    public void addShape(View view) {
        Intent intent = new Intent(this, ShapeCreator.class);
        startActivity(intent);
    }

    public void undo(View view) {
        if(cbs.backupExists()){
            cbs.restorePreviousPage();
            pv.reDraw();
        }
    }

    public void save(View view) {
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    public void deleteShape(View view) {
        cbs.makeBackupPage();
        pv.deleteShape();
    }

    public void updateShape(View view) {
        cbs.makeBackupPage();

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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_creator);

        pv = (edu.stanford.cs108.bunnyworld.PageView)
                findViewById(R.id.pc_pageView);

        cp = cbs.getCurrentPage();
        ((TextView)findViewById(R.id.pc_pageName)).setText(cp.name);
    }
}
