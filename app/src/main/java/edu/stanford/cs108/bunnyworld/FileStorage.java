package edu.stanford.cs108.bunnyworld;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Stan on 3/10/17.
 */

public class FileStorage {

    public static void store(ArrayList<Book> booksList) throws IOException {
        // Clear savedBooks.bin file
        PrintWriter pw = new PrintWriter("savedBooks.bin");
        pw.close();

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("savedBooks.bin"));
        os.writeObject(booksList);
        os.close();
    }

    public static ArrayList<Book> load() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("savedBooks.bin"));
        ArrayList<Book> booksList = (ArrayList<Book>) is.readObject();
        is.close();
        return booksList;
    }

}
