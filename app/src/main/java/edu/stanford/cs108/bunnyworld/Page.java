package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Page {// extends View { I am pretty sure that Page does not need to extend view, just as shape does not need to

    private static int pageCount = 1;//pretty sure this needs to start at zero if incremented in the constructor, dom to miguel

    protected String name;
    private Canvas canvas;
    public ArrayList<Shape> shapeList;

//    public Page(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        this.name = "page" + Integer.toString(pageCount);
//        this.pageCount++;
//        shapeList = new ArrayList<Shape>();
//
//    }


    //leave name as empty string for a default name
    public Page(String name) {
        if(name.isEmpty()){
            this.name = "page" + Integer.toString(pageCount);
        }
        else{
            this.name = name;
        }
        this.pageCount++;
        shapeList = new ArrayList<Shape>();
    }




//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        this.canvas = canvas;
//        for(int i = shapeList.size() -1; i >= 0; i--){
//            shapeList.get(i).draw();
//        }
//    }


    public void Draw(Canvas canvas) {
        this.canvas = canvas;
        for(int i = shapeList.size() -1; i >= 0; i--){
            shapeList.get(i).draw();
        }
    }





    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int numShapes() {
        return shapeList.size();
    }


    //need to be able to add shapes to a page probably from the soon to be book class
    public void addShape(Shape shape){
        this.shapeList.add(shape);
    }

//    public ArrayList<Shape> getShapes(){//don't need shapelist is now public
//        return shapeList;
//    }


}



























