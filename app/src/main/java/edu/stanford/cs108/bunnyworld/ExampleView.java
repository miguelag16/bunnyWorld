package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by miguelgarcia on 3/4/17.
 */

public class ExampleView extends View {

    Page p;

    private void init() {
        Shape s = new Shape(p, false, "dolan", "");
        s.draw(20, 20, 500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setCanvas(canvas);
        init();
//        canvas.drawBitmap(dolan.getBitmap(),
//                null,
//                new RectF(0,0,canvas.getHeight(),canvas.getWidth()),
//                null);
    }

    public ExampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        p = new Page(context, attrs);
    }
}

// Stan’s First Commit
//i see your commit