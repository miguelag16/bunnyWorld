package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Path;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import static java.security.AccessController.getContext;

/**
 * Created by Stan on 3/10/17.
 */

public class FileStorage {

    static String filename = "savedBooks.ser";

    public static void store(Context context, LinkedHashMap<String, Book> booksMap) throws IOException {

        File path = context.getFilesDir();
        String fileString = path.toString() + "/" + filename;
        File file = new File(fileString);

        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(booksMap);
            out.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static LinkedHashMap<String, Book> load(Context context) throws IOException, ClassNotFoundException {

        File path = context.getFilesDir();
        String file = path.toString() + "/" + filename;

        LinkedHashMap<String, Book> loadedBooksMap = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            loadedBooksMap = (LinkedHashMap<String, Book>)in.readObject();
            in.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return loadedBooksMap;
    }

}
