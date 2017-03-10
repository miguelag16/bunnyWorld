package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class BookActivity extends AppCompatActivity {

    public Book book;

//    public void addShape(View view) {
//
//    }
//
    public void addPage(View view) {
        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }
//
//    public void gotoPage(View view) {
//
//    }
//
//    public void finishEdit(View view) {
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main);

        book = (edu.stanford.cs108.bunnyworld.Book) findViewById(R.id.Book);

        CurBookSingleton cb = CurBookSingleton.getInstance();
        cb.setCurrentBook(book);
        cb.getCurrentBook().setEditorMode(getIntent().getBooleanExtra("IS_EDITOR", true));
    }
}
