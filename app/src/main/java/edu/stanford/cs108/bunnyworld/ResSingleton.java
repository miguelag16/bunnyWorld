package edu.stanford.cs108.bunnyworld;

import android.content.Context;

/**
 * Created by miguelgarcia on 3/5/17.
 */
public class ResSingleton {
    private static ResSingleton ourInstance = new ResSingleton();

    public static ResSingleton getInstance() {
        return ourInstance;
    }

    private ResSingleton() {}

    protected Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
