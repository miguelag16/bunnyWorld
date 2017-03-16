package edu.stanford.cs108.bunnyworld;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Page implements Serializable {

    private final int index;
    protected String displayName;
    ArrayList<Shape> shapeList;
    private final boolean isFirstPage;

    public Page(String name, Book book) {
        this.index = book.pagesMap.size();

        this.isFirstPage = (book.pagesMap.size() == 0);

        this.displayName = (name.isEmpty())? "Page " + Integer.toString(this.index + 1): name;

        shapeList = new ArrayList<Shape>();
    }

    /*
        Copy constructor, useful for undo
     */
    public Page(Page page) {
        this.index = page.getIndex();

        this.isFirstPage = page.isFirstPage();

        this.displayName = page.getName();

        shapeList = page.shapeListCopy();
    }

    public int getIndex() { return this.index; }
    public String getName(){return this.displayName;}
    boolean isFirstPage() {return this.isFirstPage; }

    void addShape(Shape shape){
        this.shapeList.add(shape);
    }
    void removeShape(Shape shape) {
        shapeList.remove(shape);
    }
    public ArrayList<Shape> getAllShapes() {
        return this.shapeList;
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

    private static final Paint possessionsLine = new Paint();
    private static final float possessionsHeight = 500.0f;
    public void draw(Canvas canvas) {
        for(Shape i : shapeList){
            i.draw(canvas);
        }
        possessionsLine.setStrokeWidth(7.0f);
        float height = canvas.getHeight();
        canvas.drawLine(0, height - possessionsHeight, canvas.getWidth(), height - possessionsHeight, possessionsLine);
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
