package edu.stanford.cs108.bunnyworld;

/**
 * Created by dominic on 3/7/2017.
 */

public class Point{
    private float left;
    private float top;
    private float right;
    private float bottom;

    public Point(float left, float top, float right, float bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public float getLeft(){
        return left;
    }

    public float getTop(){
        return top;
    }

    public float getRight(){
        return right;
    }

    public float getBottom(){
        return bottom;
    }
}
