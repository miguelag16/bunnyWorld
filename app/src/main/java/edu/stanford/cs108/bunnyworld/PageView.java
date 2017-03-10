package edu.stanford.cs108.bunnyworld;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by miguelgarcia on 3/10/17.
 */

public class PageView extends View {

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Page cp = CurBookSingleton.getInstance().getCurrentBook().getCurrentPage();
        cp.draw(canvas);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
