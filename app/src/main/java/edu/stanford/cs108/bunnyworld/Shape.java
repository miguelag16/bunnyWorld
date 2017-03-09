package edu.stanford.cs108.bunnyworld;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Shape {

    private Context context;

    private float textSize = 50.0f;
    private boolean isMovable;
    private boolean isHidden;
    private Page page;
    private String shapeName;
    private String resText;
    private String drawableText;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Point point;

    //a shape's script needs to be able to be changed by other classes in editor mode
    public Script script;


    private boolean possessions;


    public Shape(Page p, String resText, String drawableText, boolean inPossessions,
                 boolean isHidden, boolean isMovable, Point point) {
        this.shapeName = p.name + "/" + resText + "-" + drawableText;
        this.resText = resText;
        this.drawableText = drawableText;
        this.isHidden = isHidden;
        this.isMovable = isMovable;

        this.page = p;
        this.possessions = inPossessions;
        this.script = new Script();
        this.point = point;
    }

    public void draw() {
         //need to fix hidden based on whether it is gameplay or editor mode
        if (!isHidden) {
            if (!drawableText.isEmpty()){
                paint.setTextSize(textSize);
                page.getCanvas().drawText(drawableText, point.getLeft(), point.getTop() + textSize, paint);//text is drawn starting at lower left corner
            }
            else if (!resText.isEmpty()) {
                ResSingleton rs = ResSingleton.getInstance();
                int fileID = rs.getContext()
                        .getResources().getIdentifier(resText, "drawable", rs.getContext().getPackageName());
                BitmapDrawable x =
                        (BitmapDrawable) rs.getContext().getResources().getDrawable(fileID);
                page.getCanvas().drawBitmap(
                        x.getBitmap(), null, new RectF(point.getLeft(), point.getTop(),
                                point.getRight(), point.getBottom()), null);
            } else {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.LTGRAY);
                RectF r = new RectF(point.getLeft(), point.getTop(),
                        point.getRight(), point.getBottom());
                page.getCanvas().drawRect(r, paint);
            }
        }
    }

    public boolean inPossessions() {
        return possessions;
    }

    public String getName() {
        return shapeName;
    }

    public void setName(String newName) {
        this.shapeName = newName;
    }

    public void setTextSize(float f){
        textSize = f;
    }


    //shapes can have clauses in them that when executed, hide other shapes
    public void setIsHidden(boolean isHidden){
        this.isHidden = isHidden;
    }

    //leave drop name as the empty string if it is not a drop event.
    public void enactScript(String TriggerEvent, String dropName){
        ArrayList<String> commands = script.getClauses(TriggerEvent, dropName);
    }





}
