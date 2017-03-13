package edu.stanford.cs108.bunnyworld;

/**
 * Created by domin on 3/13/2017.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/10/17.
 */

public class PageViewGameMode extends View {

    private Page cp = null;
    private Shape cs = null;
    private Canvas c = null; //Best variable name I have ever seen in my life

    public PageViewGameMode(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Shape getPVCurrentShape() {return this.cs; }
    public void deleteShape() {
        cp.removeShape(cs);
        //hideEditXML();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c = canvas;
        Page temp = CurBookSingleton.getInstance().getCurrentBook().getCurrentPage();
        if(cp != temp){//actually do want to compare the literal addresses
            System.out.println("cp does not equal temp");
            cp = temp;
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
                    x = event.getX();
                    y = event.getY();
                    if(cs.isMovable()){
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


    private void setupEditXML() {
        View parent = (View) getParent();

        LinearLayout g = (LinearLayout) parent.findViewById(R.id.pc_buttonRow);
        g.setVisibility(View.GONE);

        LinearLayout v = (LinearLayout) parent.findViewById(R.id.pc_editLL);
        v.setVisibility(View.VISIBLE);

        EditText n = (EditText) parent.findViewById(R.id.pc_nameET);
        n.setText(cs.getName());

        EditText w = (EditText) parent.findViewById(R.id.pc_widthET);
        w.setText(String.valueOf(cs.getWidth()));

        EditText h = (EditText) parent.findViewById(R.id.pc_heightET);
        h.setText(String.valueOf(cs.getHeight()));
    }

    private void hideEditXML() {
        View parent = (View) getParent();

        LinearLayout g = (LinearLayout) parent.findViewById(R.id.pc_buttonRow);
        g.setVisibility(View.VISIBLE);

        LinearLayout v = (LinearLayout) parent.findViewById(R.id.pc_editLL);
        v.setVisibility(View.GONE);
    }

}

