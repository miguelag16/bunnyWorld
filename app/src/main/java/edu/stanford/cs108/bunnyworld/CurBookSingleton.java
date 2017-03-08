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

    protected Book currentBook;

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book b) {
        this.currentBook = b;
    }
}
