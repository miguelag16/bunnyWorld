package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChooseOrCreateBook extends AppCompatActivity {

    // HashMap of books
    private HashMap<String, Book> booksMap;
    private ListView existing_books_listview;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_book);

        // Find the ListView resource
        existing_books_listview = (ListView) findViewById(R.id.existing_books);

        // Create and populate a List of book names
        List<String> bookNameList = new ArrayList<String>(booksMap.keySet());

        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.books_listview, bookNameList);

        // Set the ArrayAdapter as the ListView's adapter
        existing_books_listview.setAdapter(listAdapter);

    }

    public void createBook (View view) {
        // Create a new book and add it to booksList
        Book book = new Book("");
        booksMap.put(book.getName(), book);
        // Set as current book
        CurBookSingleton.getInstance().setCurrentBook(book);
        // Goto BookActivity
        Intent intent = new Intent(this, BookActivity.class);
        startActivity(intent);
    }
}
