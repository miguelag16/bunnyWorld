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
}
