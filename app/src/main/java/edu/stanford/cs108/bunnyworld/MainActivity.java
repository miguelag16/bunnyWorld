package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import java.util.LinkedHashMap;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    // LinkedHashMap of books
    public static LinkedHashMap<Integer, Book> booksMap = new LinkedHashMap<Integer, Book>();

//    public static LinkedHashMap<Integer, Book> loadedBooksMap = new LinkedHashMap<Integer, Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mp = MediaPlayer.create(this,R.raw.theme);
        mp.start();

        // Use to access files in shapeCreator
        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());


        // Load existing books from file
        LinkedHashMap<Integer, Book> loadedBooksMap = null;
        try {
            loadedBooksMap  = FileStorage.load(this.getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(loadedBooksMap != null)
            booksMap = loadedBooksMap;

    }

    public void play(View view) {
        Intent intent = new Intent(this, PossessionsGrid.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

}
