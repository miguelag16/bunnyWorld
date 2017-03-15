package edu.stanford.cs108.bunnyworld;

import android.graphics.Canvas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by dominic on 3/7/2017.
 */


public class Book implements Serializable {

    private static int numBooks = 0;
    private Page currentPage;   // The page that needs to be drawn
    private Page firstPage;     //the page that gameplay mode will start on


    public LinkedHashMap<String, Page> pagesMap;    // Shapes need to be able to access other shapes on different pages
    private boolean isEditorMode;
    private Possessions possessions;

    private String bookName;

    public Book(String name) {
        pagesMap = new LinkedHashMap<String, Page>();
        possessions = new Possessions(this);
        numBooks++;

        if(name.isEmpty()) bookName = "Book" + Integer.toString(numBooks);
        else bookName = name;

        // Automatically add a default page when a new book is created
        Page page = new Page("", this);
        this.firstPage = page; //the automatically added page will always be the first one
                               //please allow for some way of changing the name of a page
        pagesMap.put(page.name, page);
    }

    public String getName(){
        return bookName;
    }

    public Page getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(Page p){
        currentPage = p;
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


    /*
     * Should be called by a button in editor mode.
     * Should create a new page with the name from some text field.
     * Then set current page equal to it and allow the user to add shapes
     */
    public void addPage(Page page) {
        this.pagesMap.put(page.name, page);
    }

    public void removePage(Page page) {
        System.out.println("xxxxxxxxxxxxxxxxxx");
        System.out.println("Size: " + pagesMap.size());
        this.pagesMap.remove(page.name);
        System.out.println("Size: " + pagesMap.size());
        System.out.println("xxxxxxxxxxxxxxxxxx");

    }

    public Page getPage(String name) {
        return pagesMap.get(name);
    }






    public class Possessions{

        private ArrayList<Shape> inventory;
        private Book book;  // The book it belongs to

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
            for(int i = 0; i < book.pagesMap.size(); i++){//looks thorugh all pages and all shapes for one it needs to hide
                for(int j = 0; j < book.pagesMap.get(i).shapeList.size(); j++){
                    if(book.pagesMap.get(i).shapeList.get(j).getName().equals(s.getName())){
                        book.pagesMap.get(i).shapeList.remove(j);
                        return;
                    }
                }
            }

        }
    }

}
