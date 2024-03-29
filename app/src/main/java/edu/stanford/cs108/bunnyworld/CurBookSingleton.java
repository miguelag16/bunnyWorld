package edu.stanford.cs108.bunnyworld;

import java.util.ArrayList;

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

    protected Book curBook; //initialize to null
    public Book getCurrentBook() {
        return curBook;
    }
    public void setCurrentBook(Book b) {
        this.curBook = b;
    }

    protected Page curPage = null;
    public void setCurrentPage(Page p) {
        this.curPage = p;
        this.original = new Page(p);
    }
    public Page getCurrentPage() {return this.curPage; }
    public void resetCurrentPage() {this.curPage = null;}

    protected Page original = null;
    public Page getOriginalPage() { return this.original; }

    protected Page backup = null;
    public boolean backupExists() {return backup != null; }
    public void makeBackupPage() { this.backup = new Page(curPage); }
    public void restorePreviousPage() {
        this.curPage = this.backup;
        this.curBook.removePage(this.curPage);
        this.curBook.addPage(this.curPage);
        this.backup = null;
    }
    public void resetBackupPage() {this.backup = null; }

    public ArrayList<Shape> currentPossessions = new ArrayList<Shape>();
    public ArrayList<Shape> getCurrentPossessions() {return this.currentPossessions; }
    public void setCurrentPossessions(ArrayList<Shape> cpos) {this.currentPossessions = cpos; }

}
