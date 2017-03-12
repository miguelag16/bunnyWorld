package edu.stanford.cs108.bunnyworld;

import java.io.Serializable;

/**
 * Created by dominic on 3/7/2017.
 */

public class Point implements Serializable {
    private float left;
    private float top;
    private float right;
    private float bottom;

    public Point(float left, float top){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public float getLeft(){
        return left;
    }
    public void setLeft(float f) {this.left = f;}

    public float getTop(){
        return top;
    }
    public void setTop(float f) {this.top = f;}

//    public float getRight(){
//        return right;
//    }
//    public void setRight(float f) {this.right = f;}
//
//    public float getBottom(){
//        return bottom;
//    }
//    public void setBottom(float f) {this.bottom = f;}
}
