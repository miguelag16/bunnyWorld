package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class BookActivity extends AppCompatActivity {

    public void addPage(View view) {
        CurBookSingleton cb = CurBookSingleton.getInstance();

        Page newPage = new Page("");
        cb.getCurrentBook().addPage(newPage);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main);

        Book book = new Book("");
        CurBookSingleton cb = CurBookSingleton.getInstance();
        cb.setCurrentBook(book);
        cb.getCurrentBook().setEditorMode(getIntent().getBooleanExtra("IS_EDITOR", true));

        TextView tv = (TextView) findViewById(R.id.ba_bookName);
        tv.setText(book.getName());
    }
}
