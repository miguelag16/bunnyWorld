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

        ResSingleton rs = ResSingleton.getInstance();
        rs.setContext(this.getApplicationContext());

        setContentView(R.layout.activity_main);
    }

    public void play() {
    }

    public void edit(View view) {
        Intent intent = new Intent(this, Book.class);
        startActivity(intent);
    }

}
