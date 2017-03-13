package edu.stanford.cs108.bunnyworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static edu.stanford.cs108.bunnyworld.R.id.existing_books;
import static edu.stanford.cs108.bunnyworld.R.id.existing_pages;

/**
 * Created by miguelgarcia on 3/8/17.
 */

public class ChooseOrCreatePage extends AppCompatActivity {

    private ListView existing_pages;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onResume() {
        super.onResume();

        // Find the ListView resource
        existing_pages = (ListView) findViewById(R.id.existing_pages);
        // Create and populate a List of book names
        List<String> pageNameList = new ArrayList<String>(CurBookSingleton.getInstance().getCurrentBook().pagesMap.keySet());
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, pageNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_pages.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_page);

        TextView tv = (TextView) findViewById(R.id.bookName);
        tv.setText(CurBookSingleton.getInstance().getCurrentBook().getName());

        // Find the ListView resource
        existing_pages = (ListView) findViewById(R.id.existing_pages);
        // Create and populate a List of book names
        List<String> pageNameList = new ArrayList<String>(CurBookSingleton.getInstance().getCurrentBook().pagesMap.keySet());
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, pageNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_pages.setAdapter(listAdapter);


        existing_pages.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pageName = existing_pages.getItemAtPosition(i).toString();

                // Set as current page. Might store this in a singleton instead
                Book cur = CurBookSingleton.getInstance().getCurrentBook();
                cur.setCurrentPage(cur.getPage(pageName));

                // Go to PageCreator
                Intent intent = new Intent(getApplicationContext(), PageCreator.class);
                startActivity(intent);
            }
        });
    }

    public void addPage(View view) {
        Book book = CurBookSingleton.getInstance().getCurrentBook();

        Page newPage = new Page("", book);
        book.addPage(newPage);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

    public void closeBook(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

}
