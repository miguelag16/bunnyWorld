package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Path;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

import static java.security.AccessController.getContext;

/**
 * Created by Stan on 3/10/17.
 */

public class FileStorage {

    static String filename = "savedbooks.ser";

    public static void store(Context context, LinkedHashMap<Integer, Book> booksMap) throws IOException {

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

    public static LinkedHashMap<Integer, Book> load(Context context) throws IOException, ClassNotFoundException {


        if (MainActivity.booksMap.size() == 0) {
            // Get a books map that contains the default world from the raw directory
            InputStream rawStream = context.getResources().openRawResource(R.raw.finaldefaultbook);
            LinkedHashMap<Integer, Book> defaultBooksMap = null;
            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(rawStream);
                defaultBooksMap = (LinkedHashMap<Integer, Book>)in.readObject();
                in.close();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            return defaultBooksMap;
        }

        // Get a books map that contains the default world from the raw directory
        InputStream rawStream = context.getResources().openRawResource(R.raw.finaldefaultbook);
        LinkedHashMap<Integer, Book> defaultBooksMap = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(rawStream);
            defaultBooksMap = (LinkedHashMap<Integer, Book>)in.readObject();
            in.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        // Get all the other worlds stored locally on the emulator
        File path = context.getFilesDir();
        String file = path.toString() + "/" + filename;
        LinkedHashMap<Integer, Book> loadedBooksMap = null;
        FileInputStream fis2 = null;
        ObjectInputStream in2 = null;
        try {
            fis2 = new FileInputStream(file);
            in2 = new ObjectInputStream(fis2);
            loadedBooksMap = (LinkedHashMap<Integer, Book>)in2.readObject();
            in2.close();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }


        // Combine default world stored in raw directory with other worlds stored on emulator
        LinkedHashMap<Integer, Book> completeBooksMap = new LinkedHashMap<Integer, Book>();
        completeBooksMap.putAll(defaultBooksMap);
        completeBooksMap.putAll(loadedBooksMap);
        return completeBooksMap;

    }

}

