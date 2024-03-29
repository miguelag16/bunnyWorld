package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/10/17.
 */

public class PageView extends View {

    CurBookSingleton cbs = CurBookSingleton.getInstance();

    private Page cp = null;
    private Shape cs = null;

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Shape getPVCurrentShape() {return this.cs; }

    public void deleteShape() {
        cp.removeShape(cs);
        hideEditXML();
        invalidate();
    }

    public void reDrawPage() {
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cp = cbs.getCurrentPage();
        cp.draw(canvas);
        ((TextView)((View)getParent()).findViewById(R.id.pc_numShapes)).setText("Shapes: " + this.cbs.getCurrentPage().numShapes() +
                "\nPossessions: " + this.cbs.getCurrentPage().possessions.size());
//        cbs.setCurrentPossessions(cp.possessions); do this on play mode
    }

    private int pastEvent = -1; //Sentinel value = -1
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x, y;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                cs = cp.findSelectedShape(x, y);

                if(cs != null) {
                    System.out.println(cs.getName());
                    setupEditXML();
                }
                else {
                    System.out.println("null shape on action down");
                    hideEditXML();
                    cs = null;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(cs != null){
                    //We only want to backup the page before the series of ACTION_MOVE events
                    if(pastEvent != MotionEvent.ACTION_MOVE) cbs.makeBackupPage();
                    x = event.getX();
                    y = event.getY();
                    cs.setLocation(x, y);
                    System.out.println("Reset location of " + cs.getName());
                }
                System.out.println("null shape on action move");
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }

        pastEvent = event.getAction();
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
