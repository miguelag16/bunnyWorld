package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
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
        List<String> bookNameList = new ArrayList<String>();
        for(Book b : MainActivity.booksMap.values()){
            bookNameList.add(b.getName());
        }
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
        List<String> bookNameList = new ArrayList<String>();
        for(Book b : MainActivity.booksMap.values()){
            bookNameList.add(b.getName());
        }
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, bookNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_books.setAdapter(listAdapter);

        existing_books.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String book_name = existing_books.getItemAtPosition(i).toString();

                Book selected = MainActivity.chooseBookFromList(book_name);

                if(selected != null) {
                    // Set as current book
                    CurBookSingleton.getInstance().setCurrentBook(selected);

                    Toast.makeText(getApplicationContext(), selected.getName() + " started", Toast.LENGTH_SHORT).show();

                    // Goto ChooseOrCreatePage
                    Intent intent = new Intent(getApplicationContext(), ChooseOrCreatePage.class);
                    startActivity(intent);
                } else Toast.makeText(getApplicationContext(), "Error: no selection made", Toast.LENGTH_SHORT).show();
            }
        });

        existing_books.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String book_name = existing_books.getItemAtPosition(i).toString();

                Book selected = MainActivity.chooseBookFromList(book_name);

                if(selected != null) {
                    Toast.makeText(getApplicationContext(), "Deleted \"" + book_name + "\"" , Toast.LENGTH_SHORT).show();
                    MainActivity.booksMap.remove(selected.getIndex());

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else Toast.makeText(getApplicationContext(), "Error: no selection made", Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }

    public void createBook (View view) {
        // Create a new book and add it to booksList
        Book.numBooks = (Book.numBooks > MainActivity.booksMap.size()) ? Book.numBooks : MainActivity.booksMap.size();
        Book book = new Book("");
        MainActivity.booksMap.put(book.getIndex(), book);

        // Set as current book
        CurBookSingleton.getInstance().setCurrentBook(book);
        // Goto ChooseOrCreatePage
        Intent intent = new Intent(this, ChooseOrCreatePage.class);
        startActivity(intent);
    }

    public void saveBooks (View view) {
        try {
            FileStorage.store(this.getApplicationContext(), MainActivity.booksMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(ChooseOrCreateBook.this, "Books Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
