package edu.stanford.cs108.bunnyworld;

import android.graphics.Paint;

import java.io.Serializable;

/**
 * Created by Stan on 3/16/17.
 */

public class PaintSerializable extends Paint implements Serializable {

    public PaintSerializable(int flags) {
        super(flags);
    }
}
