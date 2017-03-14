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

    protected String name;
    ArrayList<Shape> shapeList;
    private final boolean isFirstPage;

    public Page(String name, Book book) {
        this.isFirstPage = (book.pagesMap.size() == 0);

        this.name = (name.isEmpty())? "Page " + Integer.toString(book.pagesMap.size() + 1): name;

        shapeList = new ArrayList<Shape>();
    }

    /*
        Copy constructor, useful for undo
     */
    public Page(Page page) {
        this.isFirstPage = page.isFirstPage();

        this.name = page.getName();

        shapeList = page.shapeListCopy();
    }

    public String getName(){return this.name;}
    boolean isFirstPage() {return this.isFirstPage; }

    void addShape(Shape shape){
        this.shapeList.add(shape);
    }
    void removeShape(Shape shape) {
        shapeList.remove(shape);
    }

    private ArrayList<Shape> shapeListCopy() {
        ArrayList<Shape> copy = new ArrayList<Shape>();

        for(Shape s: this.shapeList)
            copy.add(new Shape(s));

        return copy;
    }

    public int numShapes() {
        return shapeList.size();
    }


    /*
        Methods for drawing a page and modifying its shapes.
     */

    public void draw(Canvas canvas) {
        for(Shape i : shapeList){
            i.draw(canvas);
        }
    }

    Shape findSelectedShape(float x, float y) {
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
