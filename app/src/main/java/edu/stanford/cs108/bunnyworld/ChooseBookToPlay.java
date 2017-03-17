package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChooseBookToPlay extends AppCompatActivity {

    private ListView books_to_play;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_book_to_play);

        // Find the ListView resource
        books_to_play = (ListView) findViewById(R.id.books_to_play);

        // Create and populate a List of book names
        List<String> bookNameList = new ArrayList<String>();
        for(Book b : MainActivity.booksMap.values()){
            bookNameList.add(b.getName());
        }

        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, bookNameList);
        // Set the ArrayAdapter as the ListView's adapter
        books_to_play.setAdapter(listAdapter);

        books_to_play.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String book_name = books_to_play.getItemAtPosition(i).toString();

                Book selected = MainActivity.chooseBookFromList(book_name);

                if(selected != null) {
                    // Set as current book
                    CurBookSingleton.getInstance().setCurrentBook(selected);
                    CurBookSingleton.getInstance().setCurrentPage(selected.getPageByIndex(0));

                    Toast.makeText(getApplicationContext(), selected.getName() + " started", Toast.LENGTH_SHORT).show();

                    // Goto ChooseOrCreatePage
                    Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                    startActivity(intent);
                } else Toast.makeText(getApplicationContext(), "Error: no selection made", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
