package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import java.util.LinkedHashMap;


public class MainActivity extends AppCompatActivity {

    // LinkedHashMap of books
    public static LinkedHashMap<Integer, Book> booksMap = new LinkedHashMap<Integer, Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ON CREAAAATEEEEEEEEEEEEEEEEEEEEEEEEEEE");
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

        //If file not empty, load the existing books into booksMap.
        if(loadedBooksMap != null)
            booksMap = loadedBooksMap;

    }

    public void play(View view) {
        Intent intent = new Intent(this, ChooseBookToPlay.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

    public static Book chooseBookFromList(String book_name){
        for(Book b : MainActivity.booksMap.values()){
            if(b.getName().equals(book_name)){
                return b;
            }
        }
        return null;
    }
}
