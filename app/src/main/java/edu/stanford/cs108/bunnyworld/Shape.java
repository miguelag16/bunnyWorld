package edu.stanford.cs108.bunnyworld;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Shape implements Serializable {

    protected static final float DEFAULT_WIDTH = 200.0f;
    protected static final float DEFAULT_HEIGHT = 200.0f;

    private float width;
    private float height;

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
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        this.resText = resText;
        this.drawableText = drawableText;
        this.isHidden = isHidden;
        this.isMovable = isMovable;

        this.page = p;
        this.possessions = inPossessions;
        this.script = new Script();
        this.point = point;
    }

    public float getWidth(){ return this.width; }
    public void setWidth(float f){ this.width = f; }

    public float getHeight(){ return this.height; }
    public void setHeight(float f){ this.height = f; }

    public Point getLocation() {return this.point; }
    public void setLocation(float x, float y) {
        this.point.setLeft(x);
        this.point.setTop(y);
    }

    private void highlightShape(Canvas canvas) {
        RectF highlight = new RectF(point.getLeft(), point.getTop(),
                point.getLeft() + width, point.getTop() + height);

        Paint goldPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        goldPaint.setColor(Color.YELLOW);
        goldPaint.setStrokeWidth(15.0f);
        canvas.drawRect(highlight, goldPaint);
    }


    //needs to take in a Canvas object instead of getting it exclusivley from its page because it could be the possessions trying to draw it.
    public void draw(Canvas canvas) {
         //need to fix hidden based on whether it is gameplay or editor mode
        if (!isHidden || CurBookSingleton.getInstance().getCurrentBook().isEditorMode()) {
            if (!drawableText.isEmpty()){
                paint.setTextSize(textSize);
                canvas.drawText(drawableText, point.getLeft(), point.getTop() + textSize, paint);//text is drawn starting at lower left corner
            }
            else if (!resText.isEmpty()) {
                ResSingleton rs = ResSingleton.getInstance();
                int fileID = rs.getContext()
                        .getResources().getIdentifier(resText, "drawable", rs.getContext().getPackageName());
                BitmapDrawable x =
                        (BitmapDrawable) rs.getContext().getResources().getDrawable(fileID);
                canvas.drawBitmap(
                        x.getBitmap(), null, new RectF(point.getLeft(), point.getTop(),
                                point.getLeft() + width, point.getTop() + height), null);
            } else {
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setColor(Color.LTGRAY);
                RectF r = new RectF(point.getLeft(), point.getTop(),
                        point.getLeft() + width, point.getTop() + height);
                canvas.drawRect(r, paint);
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
        if(commands.size() == 0){
            return;
        }
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        for(int i = 0; i < commands.size(); i = i + 2){
            if(commands.get(i).equals("hide") || commands.get(i).equals("show")){
                boolean flag = true;
                if(commands.get(i).equals("show")){//enables me to have one loop to handle hide and show
                    flag = false;                  //versus two identical loops except the set value
                }
                for(int j = 0; j < book.pagesMap.size(); j++){//looks thorugh all pages and all shapes for one it needs to hide
                    for(int k = 0; k < book.pagesMap.get(j).shapeList.size(); k++){
                        if(book.pagesMap.get(j).shapeList.get(k).equals(commands.get(i + 1))){
                            book.pagesMap.get(j).shapeList.get(k).setIsHidden(flag);
                        }
                    }
                }
            }
            else if(commands.get(i).equals("play")){
                //Someone please add code here to play designated sound file
            }
            else{
                //experimental but I think it will work
                //int pageIndex = book.pagesMap.indexOf(commands.get(i + 1));
                //book.setCurrentPage(book.pagesMap.get(pageIndex));
            }
        }

    }

    //I think the below two classes will enable me to use hashmaps/sets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return shapeName != null ? shapeName.equals(shape.shapeName) : shape.shapeName == null;

    }

    @Override
    public int hashCode() {
        return shapeName != null ? shapeName.hashCode() : 0;
    }

    //    private boolean isImage(String resText) {
//        String[]
//    }
//
//    private boolean isAudio(String resText) {
//
//    }

}
