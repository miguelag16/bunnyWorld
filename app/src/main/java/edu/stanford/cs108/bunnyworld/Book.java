package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by dominic on 3/7/2017.
 */


public class Book extends View implements Parcelable{

    private Page currentPage;//this is the only page that needs to be drawn, can easily switch to any page in the list
    public ArrayList<Page> allPages; //shapes need to be able to access other shapes on different pages

    private void init() {
        Shape s = new Shape(currentPage, false, "dolan", "", false, false, new Point(20, 20, 500, 500));
        currentPage.addShape(s);
        s = new Shape(currentPage, false, "", "stan is the wurrrrst", false, false, new Point(20, 40, 100000, 100000));
        currentPage.addShape(s);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        currentPage.setCanvas(canvas);
        init();
        currentPage.Draw(canvas);
    }

    public Book(Context context, AttributeSet attrs) {
        super(context, attrs);
        allPages = new ArrayList<Page>();
        //if database is empty create new empty page else load it from database
        //will also be different depending on whether it is in editor or game mode
        //doesn't make sense to have a book view without at least one page
        currentPage = new Page("Page1");
    }


    //should be called by a button in editor mode
    //should create a new page with the name from some text field
    //then set current page equal to it and allow the user to add shapes
    public void addPage(){

    }


    //should be called by a button in editor mode
    //should create a new shape on the currently being edited page
    //should get all the information for the shape from checkboxes and textfields
    public void addShape(){
        //do stuff to currentPage
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in){
            return new Book(in);
        }

        public Book[] newArray(int size){
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}












