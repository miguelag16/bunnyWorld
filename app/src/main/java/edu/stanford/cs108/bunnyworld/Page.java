package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Page implements Serializable {

    private static int pageCount;
    protected String name;
    private Canvas canvas;
    public ArrayList<Shape> shapeList;

    public Page(String name, Book book) {
        if(name.isEmpty()){
            this.name = "Page" + Integer.toString(book.pagesMap.size() + 1);
        }
        else{
            this.name = name;
        }
        shapeList = new ArrayList<Shape>();
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void addShape(Shape shape){
        this.shapeList.add(shape);
    }
    public void removeShape(Shape shape) {
        shapeList.remove(shape);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public int numShapes() {
        return shapeList.size();
    }

    public void draw(Canvas canvas) {
        this.canvas = canvas;
        for(Shape i : shapeList){
            i.draw(canvas);
        }
    }

    public Shape findSelectedShape(float x, float y) {
        for(int i = shapeList.size() - 1; i >= 0; i--){
            Shape cur = shapeList.get(i);
            System.out.println(cur.getName());
            if(selected(cur, x, y)) return cur;
        }
        return null;
    }

    private boolean selected(Shape s, float x, float y) {
        System.out.println(Float.toString(x) + "XY" + Float.toString(y));
        Point p = s.getLocation();
        if(x >= p.getLeft() && x <= p.getLeft() + s.getWidth()) {
            System.out.println(Float.toString(p.getLeft()) + "LR" + Float.toString(p.getLeft() + s.getWidth()));
            System.out.println(Float.toString(p.getTop()) + "TB" + Float.toString(p.getTop() + s.getHeight()));
            if (y >= p.getTop() && y <= p.getTop() + s.getHeight()) {
                System.out.println(Float.toString(p.getTop()) + "TB" + Float.toString(p.getTop() + s.getHeight()));
                return true;
            }
        }
        return false;
    }

}
