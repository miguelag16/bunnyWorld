package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use to access files in shapeCreator
        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());

        // Load existing books from file
        ArrayList<Book> loadedBookList = new ArrayList<Book>();
        try {
            loadedBookList = FileStorage.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Add books to booksMap
        for (Book b : loadedBookList) {
            ChooseOrCreateBook.booksMap.put(b.getName(), b);
        }

    }

    public void play(View view) {
//        Intent intent = new Intent(this, PlayActivity.class);
//        intent.putExtra("IS_EDITOR", false);
//        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
//        intent.putExtra("IS_EDITOR", true);
        startActivity(intent);
    }

}
