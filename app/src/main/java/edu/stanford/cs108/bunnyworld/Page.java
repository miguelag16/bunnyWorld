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

    private static int pageCount = 0;

    protected String name;
    private Canvas canvas;
    public ArrayList<Shape> shapeList;

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

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void addShape(Shape shape){
        this.shapeList.add(shape);
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
        if(x >= p.getLeft() && x <= p.getLeft() + Shape.WIDTH) {
            System.out.println(Float.toString(p.getLeft()) + "LR" + Float.toString(p.getLeft() + Shape.WIDTH));
            System.out.println(Float.toString(p.getTop()) + "TB" + Float.toString(p.getTop() + Shape.HEIGHT));
            if (y >= p.getTop() && y <= p.getTop() + Shape.HEIGHT) {
                System.out.println(Float.toString(p.getTop()) + "TB" + Float.toString(p.getTop() + Shape.HEIGHT));
                return true;
            }
        }
        return false;
    }



    public Canvas getCanvas() {
        return this.canvas;
    }



    public int numShapes() {
        return shapeList.size();
    }



//    public ArrayList<Shape> getShapes(){//don't need shapelist is now public
//        return shapeList;
//    }


}



























