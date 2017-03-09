package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by domin on 3/9/2017.
 */


//I think this needs some serious edits. I believe it needs to extend view and be able to draw itself
//this shouldn't be too hard, just loop through and call draw on every shape.

public class Possessions extends View{

    //would add a get shapesInInventory function, but I don't see why this would ever be needed with
    //the add, remove and contains functions.
    private ArrayList<Shape> inventory;

    public Possessions(Context context, AttributeSet attrs){
        super(context, attrs);
        inventory = new ArrayList<Shape>();
        //inventory = new HashSet<Shape>();
    }
    //public Possessions(){
      //  inventory = new ArrayList<Shape>();
    //}

    public void removeShape(Shape s){inventory.remove(s);}

    public boolean containsShape(Shape s){return inventory.contains(s);}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = inventory.size() - 1; i >= 0; i--){
            inventory.get(i).draw(canvas);
        }
    }

    public void addShape(Shape s){
        inventory.add(s);
        Book book = CurBookSingleton.getInstance().getCurrentBook();

        //since it has been placed in inventory,  below loop removes it from page it used to be on
        //assumes all shape names are unique, need to add this error checking when getting android data

        for(int i = 0; i < book.allPages.size(); i++){//looks thorugh all pages and all shapes for one it needs to hide
            for(int j = 0; j < book.allPages.get(i).shapeList.size(); j++){
                if(book.allPages.get(i).shapeList.get(j).getName().equals(s.getName())){
                    book.allPages.get(i).shapeList.remove(j);
                    return;
                }
            }
        }

    }
}
