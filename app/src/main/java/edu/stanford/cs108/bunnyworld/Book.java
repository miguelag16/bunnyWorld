package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by dominic on 3/7/2017.
 */


public class Book extends View {

    private Page currentPage;//this is the only page that needs to be drawn, can easily switch to any page in the list
    public ArrayList<Page> allPages; //shapes need to be able to access other shapes on different pages
    private boolean isEditorMode;
    private Possessions possessions;





    public class Possessions{

        //would add a get shapesInInventory function, but I don't see why this would ever be needed with
        //the add, remove and contains functions.
        private ArrayList<Shape> inventory;
        private Book book; //this is the book it belongs to.

        public Possessions(Book book){
            this.book = book;
            inventory = new ArrayList<Shape>();
        }

        public void removeShape(Shape s){inventory.remove(s);}

        public boolean containsShape(Shape s){return inventory.contains(s);}

        public void Draw(Canvas canvas) {
            for(Shape i : inventory){
                i.draw(canvas);
            }
        }

        public void addShape(Shape s){
            inventory.add(s);
            Book book = CurBookSingleton.getInstance().getCurrentBook();
            //since it has been placed in inventory,  below loop removes it from page it used to be on
            //assumes all shape names are unique, need to add this error checking when getting android data
            for(int i = 0; i < book.allPages.size(); i++){//looks thorugh all pages and all shapes for one it needs to hide
                for(int j = 0; j < book.allPages.get(i).shapeList.size(); j++){
                    if(book.allPages.get(i).shapeList.get(j).getName().equals(s.getName())){
                        book.allPages.get(i).shapeList.remove(j);
                        return;
                    }
                }
            }

        }
    }



    public Possessions getPossessions(){
        return possessions;
    }

    public boolean isEditorMode(){
        return this.isEditorMode;
    }

    public void setEditorMode(boolean b){
        this.isEditorMode = b;
    }

    private void init() {

        // We need to fix draw so that we can pass the file extension to draw, image vs audio
        Shape s = new Shape(currentPage, "dolan", "", false, false, false, new Point(20, 20, 500, 500));
        currentPage.addShape(s);
//        s = new Shape(currentPage, "", "stan is the wurrrrst", false, false, false, new Point(20, 40, 100000, 100000));
//        currentPage.addShape(s);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        currentPage.setCanvas(canvas);
//        init();
        currentPage.Draw(canvas);
        possessions.Draw(canvas);
    }

    public Book(Context context, AttributeSet attrs) {
        super(context, attrs);
        allPages = new ArrayList<Page>();
        //if database is empty create new empty page else load it from database
        //will also be different depending on whether it is in editor or game mode
        //doesn't make sense to have a book view without at least one page
        currentPage = new Page("Page1");
        possessions = new Possessions(this);
    }


    public Page getCurrentPage(){
        return currentPage;
    }


    public void setCurrentPage(Page p){
        this.currentPage = p;
    }

    //should be called by a button in editor mode
    //should create a new page with the name from some text field
    //then set current page equal to it and allow the user to add shapes
    public void addPage(Page page){
        this.setCurrentPage(page);
        this.allPages.add(page);
    }


    //should be called by a button in editor mode
    //should create a new shape on the currently being edited page
    //should get all the information for the shape from checkboxes and textfields
    public void addShape(){
        //do stuff to currentPage
    }

}












