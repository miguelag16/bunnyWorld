package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/10/17.
 */

public class PageView extends View {

    private Page cp = null;
    private Shape cs = null;


    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cp = CurBookSingleton.getInstance().getCurrentBook().getCurrentPage();
        cp.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x, y;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                cs = cp.findSelectedShape(x, y);

                if(cs != null) System.out.println(cs.getName());
                else System.out.println("null shape on action down");

                break;
            case MotionEvent.ACTION_MOVE:
                if(cs != null){
                    x = event.getX();
                    y = event.getY();
                    cs.setLocation(x, y);
                    System.out.println("Reset location of " + cs.getName());
//                    invalidate();
                }
                System.out.println("null shape on action down");
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }

        return true;
    }
}
