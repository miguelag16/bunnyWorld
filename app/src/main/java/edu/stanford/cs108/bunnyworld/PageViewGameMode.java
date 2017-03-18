package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by domin on 3/13/2017.
 */

public class PageViewGameMode extends View {

    private CurBookSingleton cbs = CurBookSingleton.getInstance();
    private Page cp = null;
    private Shape cs = null;

    public PageViewGameMode(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Page temp = cbs.getCurrentPage();
        //this.cp = cbs.getCurrentPage();
        if(temp != this.cp){
            this.cp = temp;
            for(Shape i : cp.shapeList){
                System.out.println("enacting script on " + i.getName());
                i.enactScript(Script.ONENTER, "");
            }
        }

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

                if(cs != null) {
                    System.out.println("onclick will be called for" + cs.getName());
                    cs.enactScript(Script.ONCLICK, "");
                    invalidate();
                }
                else {
                    cs = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(cs != null){
                    if(cs.isMovable()){
                        x = event.getX();
                        y = event.getY();
                        Shape temp = cp.findSelectedShape(x, y);
                        cs.setLocation(x, y);
                    }
                    System.out.println("Reset location of " + cs.getName());
                }
                break;
            case MotionEvent.ACTION_UP:
                cs = cp.findSelectedShape(event.getX(), event.getY());
                Shape temp = cp.findSelectedShapeForOnDrop(event.getX(), event.getY(), cs);
                if(temp != null){
                    System.out.println(cs.getRealName() +  " " + temp.getRealName());
                    cs.enactScript(Script.ONDROP, temp.getRealName());
                }
                invalidate();
                break;
        }

        return true;
    }

}

