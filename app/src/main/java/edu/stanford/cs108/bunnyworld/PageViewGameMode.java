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

    private Page cp = null;
    private Shape cs = null;

    public PageViewGameMode(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Page temp = CurBookSingleton.getInstance().getCurrentBook().getPageByIndex(0);
//        Page temp = CurBookSingleton.getInstance().getCurrentPage();
//        if(cp != temp){//actually do want to compare the literal addresses
//            System.out.println("cp does not equal temp");
//            cp = temp;
//            for(Shape i : cp.shapeList){
//                System.out.println("enacting script on " + i.getName());
//                i.enactScript(Script.ONENTER, "");
//            }
//        }
        
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
                    cs.enactScript(Script.ONCLICK, "");
                    invalidate();
                    //setupEditXML();
                }
                else {
                    System.out.println("null shape on action down");
                    //hideEditXML();
                    cs = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(cs != null){
                    if(cs.isMovable()){
                        x = event.getX();
                        y = event.getY();
                        Shape temp = cp.findSelectedShape(x, y);
                        if(temp != null){
                            temp.enactScript(Script.ONDROP, cs.getRealName());
                        }
                        cs.setLocation(x, y);
                    }
                    System.out.println("Reset location of " + cs.getName());
                }
                System.out.println("null shape on action move");
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }

        return true;
    }

}

