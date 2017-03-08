package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.StringTokenizer;

import static android.content.ContentValues.TAG;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Shape {

    private Context context;

    private float textSize = 50.0f;
    private boolean isMoveable;
    private boolean isHidden;
    private Page page;
    private String shapeName;
    private String imageName;
    private String drawableText;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Point point;

    //a shape's script needs to be able to be changed by other classes in editor mode
    public Script script;


    private boolean possessions;



    public Shape(Page p, boolean inPossessions, String imageName,
                 String drawableText, boolean isHidden, boolean isMoveable, Point point) {
        this.shapeName = p.name + "/" + imageName;
        this.imageName = imageName;
        this.drawableText = drawableText;
        this.isHidden = isHidden;
        this.isMoveable = isMoveable;

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
            else if (!imageName.isEmpty()) {
                ResSingleton rs = ResSingleton.getInstance();
//            rs.getContext().getResources().getIdentifier()
                System.out.println(rs.getContext().getPackageName());
                int fileID = rs.getContext()
                        .getResources().getIdentifier(imageName, "drawable", rs.getContext().getPackageName());
                System.out.println(fileID);
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
    public void setisHidden(boolean isHidden){
        this.isHidden = isHidden;
    }

    //leave drop name as the empty string if it is not a drop event.
    public void enactScript(String TriggerEvent, String dropName){
        ArrayList<String> commands = script.getClauses(TriggerEvent, dropName);
    }




}
