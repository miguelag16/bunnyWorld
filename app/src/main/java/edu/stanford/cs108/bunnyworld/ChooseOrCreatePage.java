package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_page);

        TextView tv = (TextView) findViewById(R.id.bookName);
        tv.setText(CurBookSingleton.getInstance().getCurrentBook().getName());

        // Find the ListView resource
        existing_pages = (ListView) findViewById(R.id.existing_pages);

        // Create and populate a List of book names

        // ** WANT TO REPLACE WITH CurBookSingleton.getInstance().getCurrentBook().allPages.keySet() WHEN ALLPAGES IS A LINKED HASHMAP
        List<String> pageNameList = new ArrayList<String>();

        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, pageNameList);

        // Set the ArrayAdapter as the ListView's adapter
        existing_pages.setAdapter(listAdapter);
    }

    public void addPage(View view) {
        CurBookSingleton cb = CurBookSingleton.getInstance();

        Page newPage = new Page("");
        cb.getCurrentBook().addPage(newPage);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

}
