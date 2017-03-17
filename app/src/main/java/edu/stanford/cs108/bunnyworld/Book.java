package edu.stanford.cs108.bunnyworld;

import android.graphics.Canvas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by dominic on 3/7/2017.
 */


public class Book implements Serializable {

    private static int numBooks = 0;
    private Page currentPage;   // The page that needs to be drawn
    private Page firstPage;     //the page that gameplay mode will start on

    public LinkedHashMap<Integer, Page> pagesMap;    // Shapes need to be able to access other shapes on different pages
    private boolean isEditorMode;
//    private Possessions possessions;

    private static final int index = numBooks;
    private String displayName;

    public Book(String name) {
        pagesMap = new LinkedHashMap<Integer, Page>();
//        possessions = new Possessions(this);
        this.numBooks++;

        if(name.isEmpty()) this.displayName = "Book " + Integer.toString(this.numBooks);
        else this.displayName = name;

        // Automatically add a default page when a new book is created
        this.firstPage = new Page("", this);
        this.addPage(this.firstPage);
    }

    public int getIndex() { return this.index; }
    public String getName(){
        return this.displayName;
    }
    public Page getFirstPage() {return this.firstPage; }


    // Delete both of these
    public Page getCurrentPage(){
        return currentPage;
    }
    public void setCurrentPage(Page p){
        currentPage = p;
    }

//    public Possessions getPossessions(){
//        return possessions;
//    }

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
        this.pagesMap.put(page.getIndex(), page);
    }

    public void removePage(Page page) {
        System.out.println("xxxxxxxxxxxxxxxxxx");
        System.out.println("Size: " + pagesMap.size());
        this.pagesMap.remove(page.getIndex());
        System.out.println("Size: " + pagesMap.size());
        System.out.println("xxxxxxxxxxxxxxxxxx");

    }

    public Page getPageByIndex(Integer index) {
        return pagesMap.get(index);
    }
    public List<String> getPageNames() {
        List<String> names = new ArrayList<>();
        for(Page p : this.pagesMap.values()) {
            names.add(p.getName());
        }
        return names;
    }

    public Collection<Page> getAllPages(){
        return this.pagesMap.values();
    }


//    public class Possessions implements Serializable {
//
//        private ArrayList<Shape> inventory;
//        private Book book;  // The book it belongs to
//
//        public Possessions(Book book){
//            this.book = book;
//            inventory = new ArrayList<Shape>();
//        }
//
//        public void removeShape(Shape s){inventory.remove(s);}
//
//        public boolean containsShape(Shape s){return inventory.contains(s);}
//
//        public void Draw(Canvas canvas) {
//            for(Shape i : inventory){
//                i.draw(canvas);
//            }
//        }
//
//        public void addShape(Shape s){
//            inventory.add(s);
//            Book book = CurBookSingleton.getInstance().getCurrentBook();
//            //since it has been placed in inventory,  below loop removes it from page it used to be on
//            //assumes all shape names are unique, need to add this error checking when getting android data
//            for(int i = 0; i < book.pagesMap.size(); i++){//looks thorugh all pages and all shapes for one it needs to hide
//                for(int j = 0; j < book.pagesMap.get(i).shapeList.size(); j++){
//                    if(book.pagesMap.get(i).shapeList.get(j).getName().equals(s.getName())){
//                        book.pagesMap.get(i).shapeList.remove(j);
//                        return;
//                    }
//                }
//            }
//
//        }
//    }

}
