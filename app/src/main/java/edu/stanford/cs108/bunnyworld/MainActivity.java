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
    private Book testingBook; //testing around to get gameplay to work

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use to access files in shapeCreator
        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());

        // *** FOR TESTING PURPOSES ***
        testingBook = new  Book("Book1");
        Page samplePage = new Page("", testingBook);
        Point p = new Point(0, 0);
        Shape sampleShape = new Shape("dolan", "");
        sampleShape.script.addOnEnterTrigger();
        sampleShape.script.addPlaySoundAction("thriller", Script.ONENTER);
        samplePage.addShape(sampleShape);
        testingBook.addPage(samplePage);
        booksMap.put("Book1", testingBook);
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
        CurBookSingleton b = CurBookSingleton.getInstance();
        b.setCurrentBook(testingBook);
        b.getCurrentBook().setEditorMode(false);
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, ChooseOrCreateBook.class);
        startActivity(intent);
    }

}
