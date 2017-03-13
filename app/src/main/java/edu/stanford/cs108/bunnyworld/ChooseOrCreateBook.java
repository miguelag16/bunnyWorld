package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseOrCreateBook extends AppCompatActivity {

    private ListView existing_books;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        // Find the ListView resource
        existing_books = (ListView) findViewById(R.id.existing_books);

        // Create and populate a List of book names
        List<String> bookNameList = new ArrayList<String>(MainActivity.booksMap.keySet());
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, bookNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_books.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_book);

        // Find the ListView resource
        existing_books = (ListView) findViewById(R.id.existing_books);

        // Create and populate a List of book names
        List<String> bookNameList = new ArrayList<String>(MainActivity.booksMap.keySet());
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, bookNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_books.setAdapter(listAdapter);

        existing_books.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book selected = MainActivity.booksMap.get(existing_books.getItemAtPosition(i));

                // Set as current book
                CurBookSingleton.getInstance().setCurrentBook(selected);

                // Goto ChooseOrCreatePage
                Intent intent = new Intent(getApplicationContext(), ChooseOrCreatePage.class);
                startActivity(intent);
            }
        });

    }

    public void createBook (View view) {
        // Create a new book and add it to booksList
        Book book = new Book("");
        MainActivity.booksMap.put(book.getName(), book);

        // Set as current book
        CurBookSingleton.getInstance().setCurrentBook(book);
        // Goto ChooseOrCreatePage
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }
}
