package edu.stanford.cs108.bunnyworld;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by miguelgarcia on 3/5/17.
 */

public class Shape implements Serializable {

    private static final float DEFAULT_WIDTH = 200.0f;
    private static final float DEFAULT_HEIGHT = 200.0f;
    private static final float DEFAULT_TEXT_SIZE = 50.0f;

    private float width = DEFAULT_WIDTH;
    private float height = DEFAULT_HEIGHT;
    private float textSize = DEFAULT_TEXT_SIZE;

    private boolean isMovable = false;
    private boolean isHidden = false;
    private boolean inPossessions = false;

    private String shapeName;
    private String filename;
    private String wordArt;

    private Point point = new Point(0, 0);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //a shape's script needs to be able to be changed by other classes in editor mode
    public Script script = new Script();

    public Shape(String filename, String wordArt) {
        this.shapeName = filename + "-" + wordArt;
        this.filename = filename;
        this.wordArt = wordArt;
    }

    /*
        Copy constructor, useful for undo
     */
    public Shape(Page p, Shape s) {
        this.shapeName = s.getName();
        this.filename = s.getFilename();
        this.wordArt = s.getWordArt();

        this.width = s.getWidth();
        this.height = s.getHeight();
        this.textSize = s.getTextSize();

        this.isMovable = s.isMovable();
        this.isHidden = s.isHidden();
        this.inPossessions = s.inPossessions();

        this.script = new Script(s);
    }

    String getName() {
        return this.shapeName;
    }
    void setName(String newName) {
        this.shapeName = newName;
    }

    String getFilename() {
        return this.filename;
    }
    String getWordArt() {
        return this.wordArt;
    }

    float getWidth(){ return this.width; }
    void setWidth(float f){ this.width = f; }

    float getHeight(){ return this.height; }
    void setHeight(float f){ this.height = f; }

    Point getLocation() {return this.point; }
    void setLocation(float x, float y) {
        this.point.setLeft(x);
        this.point.setTop(y);
    }

    float getTextSize(){
        return this.textSize;
    }
    void setTextSize(float f){
        this.textSize = f;
    }

    boolean inPossessions() {
        return this.inPossessions;
    }
    void setInPossessions(boolean b) { this.inPossessions = b;}

    boolean isMovable(){
        return this.isMovable;
    }
    void setIsMovable(boolean b) { this.isMovable = b;}

    boolean isHidden(){
        return this.isHidden;
    }
    void setIsHidden(boolean b) { this.isHidden = b;}

    public Script getScript() { return this.script; }

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
            if (!wordArt.isEmpty()){
                paint.setTextSize(textSize);
                canvas.drawText(wordArt, point.getLeft(), point.getTop() + textSize, paint);//text is drawn starting at lower left corner
            }
            else if (!filename.isEmpty()) {
                ResSingleton rs = ResSingleton.getInstance();
                int fileID = rs.getContext()
                        .getResources().getIdentifier(filename, "drawable", rs.getContext().getPackageName());
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

    //leave drop name as the empty string if it is not a drop event.
    public void enactScript(String TriggerEvent, String dropName){
        System.out.println("enact scripts called");
        ArrayList<String> commands = script.getClauses(TriggerEvent, dropName);
        System.out.println(commands.size());
        System.out.println(script);

        System.out.println("commands printed below");
        for(String i : commands){
            System.out.println(i);
        }
        if(commands.size() == 0){
            return;
        }
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        for(int i = 0; i < commands.size(); i = i + 2){
            if(commands.get(i).equals(Script.HIDE) || commands.get(i).equals(Script.SHOW)){
                boolean flag = true;
                if(commands.get(i).equals(Script.SHOW)){//enables me to have one loop to handle hide and show
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
            else if(commands.get(i).equals(Script.PLAY)){
                System.out.println("tried to play thriller aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                ResSingleton resSingleton = ResSingleton.getInstance();
                MediaPlayer mp = MediaPlayer.create(resSingleton.getContext(),R.raw.thriller);
                mp.start();
            }
            else if(commands.get(i).equals(Script.GOTO)){
                //experimental but I think it will work
                //may need some way to signify to book or page view that is needs to be redrawn
                book.setCurrentPage(book.pagesMap.get(commands.get(i + 1)));
            }
            else{

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
