package edu.stanford.cs108.bunnyworld;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //just testing out github pushes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DO NOT DELETE: we need this to access files in shapeCreator
        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());
    }

    public void play(View view) {
//        Intent intent = new Intent(this, PlayActivity.class);
//        intent.putExtra("IS_EDITOR", false);
//        startActivity(intent);
    }

    public void edit(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("IS_EDITOR", true);
        startActivity(intent);
    }

}
