package edu.stanford.cs108.bunnyworld;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Stan on 3/10/17.
 */

public class FileStorage {

    public static void main (String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Page> list = new ArrayList<Page>();
        list.add(new Page("one"));
        list.add(new Page("two"));
        list.add(new Page("three"));
        store(list);
        ArrayList<Page> loadedList = load();
        System.out.println(loadedList.size());
        System.out.print(loadedList.get(0).name);
    }

    public static void store(ArrayList<Page> pages) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("savedBooks.bin"));
        os.writeObject(pages);
        os.close();
    }

    public static ArrayList<Page> load() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("savedBooks.bin"));
        ArrayList<Page> p = (ArrayList<Page>) is.readObject();
        is.close();
        return p;
    }

}
