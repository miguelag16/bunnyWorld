package edu.stanford.cs108.bunnyworld;

import java.io.Serializable;

/**
 * Created by dominic on 3/7/2017.
 */

public class Point implements Serializable {
    private float left;
    private float top;

    public Point(float left, float top){
        this.left = left;
        this.top = top;
    }

    public Point(Point p) {
        this.left = p.getLeft();
        this.top = p.getTop();
    }

    public float getLeft(){
        return left;
    }
    public void setLeft(float f) {this.left = f;}

    public float getTop(){
        return top;
    }
    public void setTop(float f) {this.top = f;}

}
