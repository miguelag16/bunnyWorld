package edu.stanford.cs108.bunnyworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    CurBookSingleton cbs = CurBookSingleton.getInstance();
    private ListView existing_pages;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        // Find the ListView resource
        existing_pages = (ListView) findViewById(R.id.existing_pages);
        // Create and populate a List of book names
        List<String> pageNameList = cbs.getCurrentBook().getPageNames();
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, pageNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_pages.setAdapter(listAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_or_create_page);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        TextView tv = (TextView) findViewById(R.id.bookName);
        tv.setText(cbs.getCurrentBook().getName());

        // Find the ListView resource
        existing_pages = (ListView) findViewById(R.id.existing_pages);
        // Create and populate a List of book names
        List<String> pageNameList = cbs.getCurrentBook().getPageNames();
        // Create ArrayAdapter using the bookNameList
        listAdapter = new ArrayAdapter<String>(this, R.layout.listview_template, pageNameList);
        // Set the ArrayAdapter as the ListView's adapter
        existing_pages.setAdapter(listAdapter);


        existing_pages.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String pageName = existing_pages.getItemAtPosition(i).toString();

                // Set as current page. Might store this in a singleton instead
                Book cur = cbs.getCurrentBook();
                cbs.setCurrentPage(cur.getPageByIndex(i));

                // Go to PageCreator
                Intent intent = new Intent(getApplicationContext(), PageCreator.class);
                startActivity(intent);
            }
        });

        existing_pages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pageName = existing_pages.getItemAtPosition(i).toString();
                Book b = cbs.getCurrentBook();
                Page p = b.getPageByIndex(i);

                if(p.isFirstPage())
                    Toast.makeText(getApplicationContext(), "You cannot delete the first page of a book" , Toast.LENGTH_SHORT).show();
                else {
                    b.removePage(p);
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Deleted page \"" + pageName + "\"", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    public void addPage(View view) {
        Book book = cbs.getCurrentBook();
        Page newPage = new Page("", book);
        book.addPage(newPage);
        cbs.setCurrentPage(newPage);

        Intent intent = new Intent(this, PageCreator.class);
        startActivity(intent);
    }

    public void updateBook(View view) {
        EditText book_name_view = (EditText) findViewById(R.id.bookName);
        String book_name = book_name_view.getText().toString();

        for(Book b: MainActivity.booksMap.values()){
            if(b.getName().equals(book_name) && !book_name.equals(cbs.getCurrentBook().getName())){
                book_name_view.setText(cbs.getCurrentBook().getName());
                Toast.makeText(this, "Name already exists, \nchoose unique book names", Toast.LENGTH_LONG).show();
                return;
            }
        }

        cbs.getCurrentBook().setName(book_name);
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // Goto ChooseOrCreatePage
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ChooseOrCreateBook.class);
        startActivity(intent);
    }

}
