package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Shape {

    private Context context;

    private Page page;
    private String shapeName;
    private String imageName;
    private String drawableText;
    private Paint paint = new Paint();

    private boolean possessions;

    public Shape(Page p, boolean inPossessions, String imageName, String drawableText) {
        this.shapeName = p.name + "/" + imageName;
        this.imageName = imageName;
        this.drawableText = drawableText;

        this.page = p;
        this.possessions = inPossessions;
    }

    public void draw(int x1, int y1, int x2, int y2) {
        if (!drawableText.isEmpty())
            page.getCanvas().drawText(drawableText, x1, y1, paint);
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
            RectF r = new RectF(x1, y1, x2, y2);
            page.getCanvas().drawRect(r, paint);
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


}
