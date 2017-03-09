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

public class Page {

    private static int pageCount = 0;

    protected String name;
    private Canvas canvas;
    public ArrayList<Shape> shapeList;

    //Leave name as empty string for a default name
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

    public void Draw(Canvas canvas) {
        this.canvas = canvas;
        for(int i = shapeList.size() -1; i >= 0; i--){
            shapeList.get(i).draw(canvas);
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

    public void addShape(Shape shape){
        this.shapeList.add(shape);
    }

//    public ArrayList<Shape> getShapes(){//don't need shapelist is now public
//        return shapeList;
//    }


}



























