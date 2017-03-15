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

    protected Book curBook; //initialize to null
    public Book getCurrentBook() {
        return curBook;
    }
    public void setCurrentBook(Book b) {
        this.curBook = b;
    }

    protected Page curPage = null;
    public void setCurrentPage(Page p) {this.curPage = p; }
    public Page getCurrentPage() {return this.curPage; }

    protected Page backup = null;
    public boolean backupExists() {return backup != null; }
    public void makeBackupPage() { this.backup = new Page(curPage); }
    public void restorePreviousPage() {
        this.curPage = this.backup;
        this.backup = null;
    }

}
