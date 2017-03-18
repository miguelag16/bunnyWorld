package edu.stanford.cs108.bunnyworld;


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

    private static int NUMBER_OF_SHAPES = 0;

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
    private String displayName;
    private String filename;
    private String wordArt;

    private Point point = new Point(0, 0);
    private PaintSerializable paint = new PaintSerializable(Paint.ANTI_ALIAS_FLAG); //Sharpens text

    //A shape's script needs to be able to be changed by other classes in editor mode
    public Script script = new Script();

    public Shape(String filename, String wordArt) {
        this.shapeName = filename + "-" + NUMBER_OF_SHAPES + wordArt;
        NUMBER_OF_SHAPES++;
        this.displayName = this.shapeName;
        this.filename = filename;
        this.wordArt = wordArt;
    }

    /*
        Copy constructor, useful for undo
     */
    public Shape(Shape s) {
        this.shapeName = s.getRealName();
        this.displayName = this.shapeName;
        this.filename = s.getFilename();
        this.wordArt = s.getWordArt();

        this.width = s.getWidth();
        this.height = s.getHeight();
        this.textSize = s.getTextSize();

        this.isMovable = s.isMovable();
        this.isHidden = s.isHidden();
        this.inPossessions = s.inPossessions();

        this.point = new Point(s.getLocation());
        this.script = new Script(s);
    }

    public String getName() {
        return this.displayName;
    }
    void setName(String newName) {
        this.displayName = newName;
    }

    public String getRealName(){return this.shapeName;}



    private String getFilename() {
        return this.filename;
    }
    private String getWordArt() {
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
    void setInPossessions(boolean b) {
        this.inPossessions = b;
        if(b)
            isMovable = b;
    }

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


    public void draw(Canvas canvas) {
         //need to fix hidden based on whether it is gameplay or editor mode
        System.out.println(filename +"dominic");
        if (!isHidden || CurBookSingleton.getInstance().getCurrentBook().isEditorMode()) {
            if (!wordArt.isEmpty()){
                paint.setTextSize(textSize);
                canvas.drawText(wordArt, point.getLeft(), point.getTop() + textSize, paint);//text is drawn starting at lower left corner
            }
            else if (!filename.isEmpty() && !filename.equals("(None)")) {
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
        System.out.println("enact scripts called\n" + this.script.toString());

        boolean flag;

        ArrayList<String> commands = script.getClauses(TriggerEvent, dropName);
        System.out.println(commands.size());
        System.out.println(script);

        System.out.println("commands printed below");
        for(String i : commands){
            System.out.println("a");
            System.out.println(i);
        }
        if(commands.size() == 0){
            return;
        }
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        for(int i = 0; i < commands.size(); i = i + 2){
//            System.out.println(commands.get(i) + " " + commands.get(i).equals(Script.HIDE));
            if(commands.get(i).equals(Script.HIDE) || commands.get(i).equals(Script.SHOW)){
                flag = true;
                if(commands.get(i).equals(Script.SHOW)){//enables me to have one loop to handle hide and show
                    flag = false;                  //versus two identical loops except the set value
                }

                // Find the shape to hide.
                for(Page p: book.getAllPages()){
                    for(Shape s: p.getAllShapes()){
                        if(s.getName().equals(getRealShapeName(commands.get(i+1)))) {
                            s.setIsHidden(flag);
                        }
                    }
                }

            }
            else if(commands.get(i).equals(Script.PLAY)){
                ResSingleton resSingleton = ResSingleton.getInstance();
                String file = commands.get(i+1).substring(0, commands.get(i+1).indexOf('.'));
                System.out.println("tried to play " + file);
                int fileID = resSingleton.getContext()
                        .getResources().getIdentifier(file, "raw", resSingleton.getContext().getPackageName());
                System.out.println("just before the mp is created");
                MediaPlayer mp = MediaPlayer.create(resSingleton.getContext(), fileID);
                System.out.println("mp.create called");
                mp.start();
                System.out.println("mp.start() called");
            }
            else if(commands.get(i).equals(Script.GOTO)){
                for(Page p : book.getAllPages()){
                    if(p.getName().equals(commands.get(i + 1))){
                        // Update possessions for next page.
                        CurBookSingleton cbs = CurBookSingleton.getInstance();
                        ArrayList<Shape> possessionsToPass = cbs.getCurrentPage().possessions;
                        cbs.setCurrentPage(p);
                        ArrayList<Shape> newShapeList = cbs.getCurrentPage().shapeList;
                        for(Shape s: possessionsToPass){
                            boolean contained = false;
                            for(Shape ns: newShapeList){
                                if(s.getRealName().equals(ns.getRealName()))
                                    contained = true;
                            }
                            if(!contained) cbs.getCurrentPage().addShape(s);
                        }
                        break;
                    }
                }
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

    public String getRealShapeName(String ShapeDisplayName){
        Book book = CurBookSingleton.getInstance().getCurrentBook();
        for(Page p : book.getAllPages()){
            for(Shape s : p.getAllShapes()){
                if(s.getName().equals(ShapeDisplayName)){
                    return s.getRealName();
                }
            }
        }
        while(true){
            System.out.println("big error");
        }
        //return "";

    }



}
