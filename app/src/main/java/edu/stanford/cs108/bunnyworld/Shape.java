package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

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


    private boolean possessions;

    public Shape(Page p, boolean inPossessions, String imageName,
                 String drawableText, boolean isHidden, boolean isMoveable) {
        this.shapeName = p.name + "/" + imageName;
        this.imageName = imageName;
        this.drawableText = drawableText;
        this.isHidden = isHidden;
        this.isMoveable = isMoveable;

        this.page = p;
        this.possessions = inPossessions;
    }

    public void draw(int x1, int y1, int x2, int y2) {
         //need to fix hidden based on whether it is gameplay or editor mode


        if (!isHidden) {
            if (!drawableText.isEmpty()){
                paint.setTextSize(textSize);
                page.getCanvas().drawText(drawableText, x1, y1 + textSize, paint);//text is drawn starting at lower left corner
            }
            else if (!imageName.isEmpty()) {
                ResSingleton rs = ResSingleton.getInstance();
//            rs.getContext().getResources().getIdentifier()
                System.out.println("MIGUEL");
                System.out.println(rs.getContext().getPackageName());
                System.out.println("MIGUEL");
                int fileID = rs.getContext()
                        .getResources().getIdentifier(imageName, "drawable", rs.getContext().getPackageName());
                System.out.println(fileID);
                BitmapDrawable x =
                        (BitmapDrawable) rs.getContext().getResources().getDrawable(fileID);
                page.getCanvas().drawBitmap(
                        x.getBitmap(), null, new RectF(x1, y1, x2, y2), null);
            } else {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.LTGRAY);
                RectF r = new RectF(x1, y1, x2, y2);
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




}
