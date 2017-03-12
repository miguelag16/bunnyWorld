package edu.stanford.cs108.bunnyworld;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;

/**
 * Created by Stan on 3/10/17.
 */

public class FileStorage {

    public static void store(LinkedHashMap<String, Book> booksMap) throws IOException {
        // Clear savedBooks.bin file
        PrintWriter pw = new PrintWriter("savedBooks.bin");
        pw.close();

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("savedBooks.bin"));
        os.writeObject(booksMap);
        os.close();
    }

    public static LinkedHashMap<String, Book> load() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("savedBooks.bin"));
        LinkedHashMap<String, Book> booksMap = (LinkedHashMap<String, Book>) is.readObject();
        is.close();
        return booksMap;
    }

}
