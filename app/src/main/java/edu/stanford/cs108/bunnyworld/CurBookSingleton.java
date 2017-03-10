package edu.stanford.cs108.bunnyworld;

/**
 * Created by miguelgarcia on 3/8/17.
 */

class CurBookSingleton {
    private static final CurBookSingleton ourInstance = new CurBookSingleton();

    static CurBookSingleton getInstance() {
        return ourInstance;
    }

    private CurBookSingleton() {
    }

    protected Book curBook;
    public Book getCurrentBook() {
        return curBook;
    }
    public void setCurrentBook(Book b) {
        this.curBook = b;
    }


//    protected Shape curShape;
//    public void setCurrentShape(Shape s) {this.curShape = s; }
//    public Shape getCurrentShape(Shape s) {
//        return this.curShape;
//    }

}
