package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class PageCreator extends AppCompatActivity {

    CurBookSingleton cbs = CurBookSingleton.getInstance();
    edu.stanford.cs108.bunnyworld.PageView pv;

    private final Page original = new Page(this.cbs.getCurrentPage());

    public void addShape(View view) {
        Intent intent = new Intent(this, ShapeCreator.class);
        startActivity(intent);
    }

    public void undo(View view) {
        if(this.cbs.backupExists()){
            this.cbs.restorePreviousPage();
        }
        ((TextView)findViewById(R.id.pc_numShapes)).setText("Shapes: " + this.cbs.getCurrentPage().numShapes());
        view.invalidate();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        this.cbs.getCurrentBook().removePage(this.cbs.getCurrentPage());
//        this.cbs.getCurrentBook().addPage(this.original);
        Toast.makeText(this, "Changes discarded", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    // Modifications to page are already saved as the current page is modified in place. All we have
    // to do is prevent the user from undoing saved changes.
    public void save(View view) {
        this.cbs.resetBackupPage();
        this.cbs.resetCurrentPage();
        Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    public void deleteShape(View view) {
        this.cbs.makeBackupPage();
        this.pv.deleteShape();
        ((TextView)findViewById(R.id.pc_numShapes)).setText("Shapes: " + this.cbs.getCurrentPage().numShapes());
        view.invalidate();
    }

    public void updateShape(View view) {
        this.cbs.makeBackupPage();

        EditText n = (EditText) findViewById(R.id.pc_nameET);
        this.pv.getPVCurrentShape().setName(n.getText().toString());

        EditText w = (EditText) findViewById(R.id.pc_widthET);
        this.pv.getPVCurrentShape().setWidth(Float.parseFloat(w.getText().toString()));

        EditText h = (EditText) findViewById(R.id.pc_heightET);
        this.pv.getPVCurrentShape().setHeight(Float.parseFloat(h.getText().toString()));

        LinearLayout ell = (LinearLayout) findViewById(R.id.pc_editLL);
        ell.setVisibility(View.GONE);

        LinearLayout br = (LinearLayout) findViewById(R.id.pc_buttonRow);
        br.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_creator);

        this.pv = (edu.stanford.cs108.bunnyworld.PageView)
                findViewById(R.id.pc_pageView);

        ((EditText)findViewById(R.id.pc_pageName)).setText(this.cbs.getCurrentPage().name);
        ((TextView)findViewById(R.id.pc_numShapes)).setText("Shapes: " + this.cbs.getCurrentPage().numShapes());
    }
}
