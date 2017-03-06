package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Page extends View {

    private static int pageCount = 1;

    protected String name;
    private Canvas canvas;
    ArrayList<Shape> shapeList;

    public Page(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.name = "page" + Integer.toString(pageCount);
        this.pageCount++;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        this.canvas = canvas;
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


}
