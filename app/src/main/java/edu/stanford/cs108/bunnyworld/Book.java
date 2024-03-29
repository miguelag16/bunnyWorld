package edu.stanford.cs108.bunnyworld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by dominic on 3/7/2017.
 */


public class Book implements Serializable {

    public static int numBooks = 0;

    public LinkedHashMap<Integer, Page> pagesMap; // Shapes need to be able to access other shapes on different pages
    private boolean isEditorMode;

    private final int index = numBooks;
    private String displayName;

    public Book(String name) {
        pagesMap = new LinkedHashMap<Integer, Page>();
        this.numBooks++;

        if(name.isEmpty()) this.displayName = "Book " + Integer.toString(this.numBooks);
        else this.displayName = name;

        // Automatically add the first page when a new book is created
        this.addPage(new Page("", this));
    }

    public int getIndex() { return this.index; }
    public String getName(){
        return this.displayName;
    }
    public void setName(String name) { this.displayName = name; }


    public boolean isEditorMode(){
        return this.isEditorMode;
    }

    public void setEditorMode(boolean b){
        this.isEditorMode = b;
    }


    public void addPage(Page page) {
        this.pagesMap.put(page.getIndex(), page);
    }
    public void removePage(Page page) {
        this.pagesMap.remove(page.getIndex());
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



    //Code for possessions
    //Grab the shapes in posessions once the page is saved, store them in a hashmap by name,
    // storing only one shape per key (unique name)
    private ArrayList<Shape> possessions;

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
