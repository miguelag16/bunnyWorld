package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by miguelgarcia on 3/4/17.
 */

public class ExampleView extends View {

    Page p;

    private void init() {
        //Shape s = new Shape(p, false, "dolan", "", false, false);
//        Log.d(TAG, "init: ");
//        Shape s = new Shape(p, false, "dolan", "", false, false, new Point(20, 20, 500, 500));
//        p.addShape(s);
//        s = new Shape(p, false, "", "Dominic is the best", false, false, new Point(20, 40, 100000, 100000));
//        p.addShape(s);
//        s.draw(20, 20, 500, 500);
//        s.draw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setCanvas(canvas);
        init();
        p.Draw(canvas);

//        canvas.drawBitmap(dolan.getBitmap(),
//                null,
//                new RectF(0,0,canvas.getHeight(),canvas.getWidth()),
//                null);
    }

    public ExampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //p = new Page(context, attrs);
        p = new Page("Page1");
    }
}

// Stan’s First Commit
//i see your commit