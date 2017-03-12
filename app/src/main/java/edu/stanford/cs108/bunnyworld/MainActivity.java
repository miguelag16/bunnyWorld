package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.io.IOException;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    // LinkedHashMap of books
    public static LinkedHashMap<String, Book> booksMap = new LinkedHashMap<String, Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use to access files in shapeCreator
        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());

        // *** FOR TESTING PURPOSES ***
        booksMap.put("Book1", new Book("Book1"));
        booksMap.put("Book2", new Book("Book2"));
        booksMap.put("Book3", new Book("Book3"));
        booksMap.put("Book4", new Book("Book4"));
        try {
            FileStorage.store(booksMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // *** FOR TESTING PURPOSES ***

        // Load existing books from file
        try {
            booksMap = FileStorage.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void play(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

}
